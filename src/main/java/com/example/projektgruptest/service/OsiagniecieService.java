package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Grupa;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.repo.OsiagniecieRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OsiagniecieService {
    private final OsiagniecieRepo osiagniecieRepo;
    private final PracownikService pracownikService;
    private final PodKategorieService podKategorieService;
    public Osiagniecie getOsiagniecie(long id) {
        return osiagniecieRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Osiagniecie o podanym id nie istnieje: " + id));
    }
    public List<Osiagniecie> getOsiagnieciaPracownika(long idPracownika) {
        return osiagniecieRepo.findByPracownik_Id(idPracownika);
    }
    public List<Osiagniecie> getOsiagnieciaPracownika(long idPracownika, Date dataPoczatkowa, Date dataKoncowa) {
        return getOsiagnieciaPracownika(idPracownika).stream()
                .filter(osiagniecie -> osiagniecie.getData().before(dataKoncowa)
                        && osiagniecie.getData().after(dataPoczatkowa))
                .collect(Collectors.toList());
    }
    public List<Osiagniecie> getOsiagnieciaZGrupy(long idPracownika, Grupa grupa) {
        return getOsiagnieciaPracownika(idPracownika).stream()
                .filter(osiagniecie ->
                        Objects.equals(osiagniecie.getPodKategoria().getGrupa().getId(), grupa.getId()))
                .collect(Collectors.toList());
    }
    public void addOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        Osiagniecie osiagniecie = buildOsiagniecie(osiagniecieDTO);
        osiagniecieRepo.save(osiagniecie);
    }
    private Osiagniecie buildOsiagniecie(OsiagniecieDTO osiagniecieDTO) {
        return Osiagniecie.builder()
                .zatwierdzone(false)
                .zarchiwizowane(false)
                .podKategoria(podKategorieService.getPodkategoria(osiagniecieDTO.getPodKategoriaNazwa()))
                .pracownik(pracownikService.getPracownik(osiagniecieDTO.getIdPracownika()))
                .data(osiagniecieDTO.getData())
                .nazwa(osiagniecieDTO.getNazwa())
                .iloscPunktow(osiagniecieDTO.getIloscPunktow())
                .build();
    }
    public void editOsiagniecie(OsiagniecieDTO osiagniecieDTO,long idOceny, long idPracownika) {
        Osiagniecie osiagniecie = getOsiagniecie(idOceny);

        modifyOsiagniecie(osiagniecie, osiagniecieDTO);

        if(canApproveOsiagniecie(pracownikService.getPracownik(idPracownika),idOceny)) {
            osiagniecie.setZatwierdzone(osiagniecieDTO.isZatwierdzone());
        }
        else {
            osiagniecie.setZatwierdzone(false);
        }

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
        osiagniecie.setZatwierdzone(true);
        osiagniecieRepo.save(osiagniecie);
    }
    public void deleteOsiagniecie(long id) {
        Osiagniecie osiagniecie = getOsiagniecie(id);
        osiagniecieRepo.delete(osiagniecie);
    }
    @Transactional
    public void przypiszOsiagnieciaOcenie(Ocena ocena) {
        getOsiagnieciaPracownika(ocena.getPracownik().getId(),ocena.getDataPoczatkowa(),ocena.getDataKoncowa())
                .stream()
                .filter(osiagniecie -> osiagniecie.getZatwierdzone() && osiagniecie.getOcena() == null)
                .forEach(osiagniecie -> {
                    osiagniecie.setOcena(ocena);
                    osiagniecieRepo.save(osiagniecie);
                });
    }
    public boolean canModifyOsiagniecie(Pracownik pracownik, long idOsagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsagniecia);

        return !osiagniecie.getZatwierdzone() && !osiagniecie.getZarchiwizowane() &&
                Objects.equals(pracownik.getGrupa().getId(), osiagniecie.getPodKategoria().getGrupa().getId()) &&
                Objects.equals(pracownik.getId(), osiagniecie.getPracownik().getId());
    }
    public boolean canApproveOsiagniecie(Pracownik pracownik, long idOsiagniecia) {
        Osiagniecie osiagniecie = getOsiagniecie(idOsiagniecia);

        return !osiagniecie.getZatwierdzone() && !osiagniecie.getZarchiwizowane() &&
                Objects.equals(pracownik.getGrupa().getId(), osiagniecie.getPodKategoria().getGrupa().getId());
    }
    public List<OsiagniecieDTO> convertListToDTO(List<Osiagniecie> osiagniecieList) {
        return osiagniecieList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public OsiagniecieDTO convertToDTO(Osiagniecie osiagniecie) {
        return OsiagniecieDTO.builder()
                .id(osiagniecie.getId())
                .nazwa(osiagniecie.getNazwa())
                .iloscPunktow(osiagniecie.getIloscPunktow())
                .data(osiagniecie.getData())
                .zatwierdzone(osiagniecie.getZatwierdzone())
                .idPracownika(osiagniecie.getPracownik().getId())
                .podKategoriaNazwa(osiagniecie.getPodKategoria().getNazwa())
                .idOceny(osiagniecie.getOcena() != null ? osiagniecie.getOcena().getId() : null)
                .build();
    }
}
