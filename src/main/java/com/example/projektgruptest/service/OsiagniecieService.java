package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.PracownikDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.repo.OsiagniecieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OsiagniecieService {
    private final OsiagniecieRepo osiagniecieRepo;
    private final PracownikService pracownikService;
    private final PodKategorieService podKategorieService;
    private final WniosekService wniosekService;
    public Osiagniecie getOsiagniecie(long id) {
        return osiagniecieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Osiagniecie o podanym id nie zostało znalezione: " + id));
    }
    public List<Osiagniecie> getOsiagnieciaPracownika(long id) {
        return osiagniecieRepo.findByWniosekPracownikIdPracownika(id);
    }
    public List<Osiagniecie> getOsiagnieciaPodwladnych(long id) {
        List<Osiagniecie> resultList = new ArrayList<>();

        for (PracownikDTO pracownik : pracownikService.getPracownicyPrzelozonego(id)) {
            resultList.addAll(getOsiagnieciaPracownika(pracownik.getIdPracownika()));
        }

        return resultList;
    }
    public List<Osiagniecie> getOsiagnieciaWniosku(long id) {
        return osiagniecieRepo.findByWniosekIdWniosku(id);
    }
    public void addOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        Osiagniecie osiagniecie = buildOsiagniecie(osiagniecieDTO);
        osiagniecieRepo.save(osiagniecie);
    }
    public void editOsiagniecie(OsiagniecieDTO osiagniecieDTO,long id) {
        Osiagniecie osiagniecie = osiagniecieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Osiagniecie o podanym id nie zostało znalezione: " + id));

        modifyOsiagniecie(osiagniecie, osiagniecieDTO);

        osiagniecieRepo.save(osiagniecie);
    }
    public void approveOsiagniecie(long id) {
        Osiagniecie osiagniecie = osiagniecieRepo.getReferenceById(id);
        osiagniecie.setCzyZatwierdzone(true);
        osiagniecieRepo.save(osiagniecie);
    }
    public void deleteOsiagniecie(long id) {
        Osiagniecie osiagniecie = osiagniecieRepo.getReferenceById(id);
        osiagniecieRepo.delete(osiagniecie);
    }
    public boolean canModify(long idPracownika, long idOsagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsagniecia);

        return getOsiagnieciaPracownika(idPracownika).contains(osiagniecie) ||
                getOsiagnieciaPodwladnych(idPracownika).contains(osiagniecie);
    }
    public boolean canApprove(long idPracownika, long idOsiagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsiagniecia);

        return getOsiagnieciaPodwladnych(idPracownika).contains(osiagniecie);
    }
    public OsiagniecieDTO convertToDTO(Osiagniecie osiagniecie) {
        return OsiagniecieDTO.builder()
                .idOsiagniecia(osiagniecie.getIdOsiagniecia())
                .nazwa(osiagniecie.getNazwa())
                .iloscPunktow(osiagniecie.getIloscPunktow())
                .data(osiagniecie.getData())
                .czyZatwierdzone(osiagniecie.getCzyZatwierdzone())
                .idWniosku(osiagniecie.getWniosek().getIdWniosku())
                .podKategoriaNazwa(osiagniecie.getPodKategoria().getNazwa())
                .build();
    }
    public List<OsiagniecieDTO> convertListToDTO(List<Osiagniecie> osiagniecieList) {
        return osiagniecieList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private Osiagniecie buildOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        return Osiagniecie.builder()
                .czyZatwierdzone(false)
                .podKategoria(podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa()))
                .wniosek(wniosekService.getWniosek(osiagniecieDTO.getIdWniosku()))
                .data(osiagniecieDTO.getData())
                .nazwa(osiagniecieDTO.getNazwa())
                .iloscPunktow(osiagniecieDTO.getIloscPunktow())
                .build();
    }
    private void modifyOsiagniecie(Osiagniecie osiagniecie, OsiagniecieDTO osiagniecieDTO) {
        osiagniecie.setNazwa(osiagniecieDTO.getNazwa());
        osiagniecie.setPodKategoria(podKategorieService.getPodkategoria(
                osiagniecieDTO.getPodKategoriaNazwa()));
        osiagniecie.setWniosek(wniosekService.getWniosek(osiagniecieDTO.getIdWniosku()));
        osiagniecie.setData(osiagniecieDTO.getData());
        osiagniecie.setIloscPunktow(osiagniecieDTO.getIloscPunktow());
    }
}
