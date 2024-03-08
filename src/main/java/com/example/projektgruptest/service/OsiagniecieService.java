package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.PermissionDeniedException;
import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.repo.OcenaRepo;
import com.example.projektgruptest.repo.OsiagniecieRepo;
import com.example.projektgruptest.repo.PracownikRepo;
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
    private final OcenaService ocenaService;
    private final OcenaRepo ocenaRepo;

    //    private final WniosekService wniosekService;
    public Osiagniecie getOsiagniecie(long id) {
        return osiagniecieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Osiagniecie o podanym id nie zostalo znalezione: " + id));
    }
    public List<Osiagniecie> getOsiagnieciaPracownika(long idPracownika) {
        return osiagniecieRepo.findByPracownik_IdPracownika(idPracownika);
    }
    public List<Osiagniecie> getOsiagnieciaPodwladnych(long idPracownika) {
        List<Osiagniecie> resultList = new ArrayList<>();

        for (Pracownik pracownik : pracownikService.getPracownicyPrzelozonego(idPracownika)) {
            resultList.addAll(getOsiagnieciaPracownika(pracownik.getIdPracownika()));
        }

        return resultList;
    }


    public void addOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        Osiagniecie osiagniecie = buildOsiagniecie(osiagniecieDTO);
        osiagniecieRepo.save(osiagniecie);
    }
    private Osiagniecie buildOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        return Osiagniecie.builder()
                .czyZatwierdzone(false)
                .podKategoria(podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa()))
                .pracownik(getPracownik(osiagniecieDTO.getIdPracownika()))
                .data(osiagniecieDTO.getData())
                .nazwa(osiagniecieDTO.getNazwa())
                .iloscPunktow(osiagniecieDTO.getIloscPunktow())
                .build();
    }
    private Pracownik getPracownik(long id){
        return pracownikService.getPracownik(id);
    }
    public void editOsiagnieciePracownik(OsiagniecieDTO osiagniecieDTO,long id) {
        Osiagniecie osiagniecie = getOsiagniecie(id);

        modifyOsiagniecie(osiagniecie, osiagniecieDTO);
        osiagniecie.setCzyZatwierdzone(false);

        osiagniecieRepo.save(osiagniecie);
    }
    public void editOsiagnieciePrzelozony(OsiagniecieDTO osiagniecieDTO,long id) {
        Osiagniecie osiagniecie = getOsiagniecie(id);

        modifyOsiagniecie(osiagniecie, osiagniecieDTO);
        osiagniecie.setCzyZatwierdzone(osiagniecieDTO.isCzyZatwierdzone());

        osiagniecieRepo.save(osiagniecie);
    }
    private void modifyOsiagniecie(Osiagniecie osiagniecie, OsiagniecieDTO osiagniecieDTO) {
        osiagniecie.setNazwa(osiagniecieDTO.getNazwa());
        osiagniecie.setPodKategoria(podKategorieService.getPodkategoria(
                osiagniecieDTO.getPodKategoriaNazwa()));
        osiagniecie.setData(osiagniecieDTO.getData());
        osiagniecie.setIloscPunktow(osiagniecieDTO.getIloscPunktow());
    }
    public void approveOsiagniecie(long id) {
        Osiagniecie osiagniecie = getOsiagniecie(id);
        osiagniecie.setCzyZatwierdzone(true);
        osiagniecieRepo.save(osiagniecie);
    }
    public void deleteOsiagniecie(long id) {
        Osiagniecie osiagniecie = getOsiagniecie(id);
        osiagniecieRepo.delete(osiagniecie);
    }
    public boolean canModifyOsiagniecie(long idPracownika, long idOsagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsagniecia);

        return getOsiagnieciaPracownika(idPracownika).contains(osiagniecie) ||
                getOsiagnieciaPodwladnych(idPracownika).contains(osiagniecie);
    }
    public boolean canApproveOsiagniecie(long idPracownika, long idOsiagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsiagniecia);

        return getOsiagnieciaPodwladnych(idPracownika).contains(osiagniecie);
    }
    public List<OsiagniecieDTO> convertListToDTO(List<Osiagniecie> osiagniecieList) {
        return osiagniecieList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public OsiagniecieDTO convertToDTO(Osiagniecie osiagniecie) {
        return OsiagniecieDTO.builder()
                .idOsiagniecia(osiagniecie.getIdOsiagniecia())
                .nazwa(osiagniecie.getNazwa())
                .iloscPunktow(osiagniecie.getIloscPunktow())
                .data(osiagniecie.getData())
                .czyZatwierdzone(osiagniecie.getCzyZatwierdzone())
                .idPracownika(osiagniecie.getPracownik().getIdPracownika())
                .podKategoriaNazwa(osiagniecie.getPodKategoria().getNazwa())
                .build();
    }

    public List<OsiagniecieDTO> podajListeOsiagniecUzytkownikaZOceny(long idOceny,long idPracownika){
        if(canUserAccessThisOcena(idOceny,idPracownika)){
            List<Osiagniecie> osiagnieciaZOceny = getOsiagnieciaPracownika(idPracownika);
            return convertListToDTO(osiagnieciaZOceny);
        }
        else {
            throw new PermissionDeniedException("You don't have permission to get this Osiagniacia from Ocena");
        }
    }
    private boolean canUserAccessThisOcena(long idOceny,long idPracownika){
        Ocena ocena  = ocenaService.getOcena(idOceny);
        return ocena.getPracownik().getIdPracownika() == idPracownika;
    }
}
