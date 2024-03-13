package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.OcenaDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.repo.OcenaRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OcenaService {
    private final OcenaRepo ocenaRepo;
    private final OsiagniecieService osiagniecieService;
    private final PracownikService pracownikService;
    public Ocena getOcena(long id) {
        return ocenaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Ocena o podanym id nie istnieje: " + id));
    }
    public List<Ocena> getOcenyPracownika(long idPracownika) {
       return ocenaRepo.findByPracownik_Id(idPracownika);
    }
    public List<OsiagniecieDTO> getOsiagnieciaZOcenyUsera(long idOceny, long idPracownika){
        if(canUserAccessThisOcena(idOceny,idPracownika)){
            return osiagniecieService.convertListToDTO(getOsiagnieciaOceny(idOceny));
        }
        else {
            return null;
        }
    }
    public List<Osiagniecie> getOsiagnieciaOceny(long idOceny) {
        List<Osiagniecie> listaOsiagniec = new ArrayList<>();
        Ocena ocena = getOcena(idOceny);
        for(Osiagniecie osiagniecie : osiagniecieService.getOsiagnieciaPracownika(ocena.getPracownik().getId())) {
            if(osiagniecie.getData().before(ocena.getDataKoncowa()) &&
                    osiagniecie.getData().after(ocena.getDataPoczatkowa()) &&
                    osiagniecie.getZatwierdzone() && !osiagniecie.getZarchiwizowane()) {
                listaOsiagniec.add(osiagniecie);
            }
        }
        return listaOsiagniec;
    }
    @Transactional
    public void addOcena(OcenaDTO ocenaDTO) {
        ocenaRepo.save(buildOcena(ocenaDTO));
    }
    public Ocena buildOcena(OcenaDTO ocenaDTO) {
        return Ocena.builder()
                .nazwa(ocenaDTO.getNazwa())
                .dataPoczatkowa(ocenaDTO.getDataPoczatkowa())
                .dataKoncowa(ocenaDTO.getDataKoncowa())
                .pracownik(pracownikService.getPracownik(ocenaDTO.getIdPracownika()))
                .zatwierdzona(false)
                .build();
    }
    @Transactional
    public void editOcena(long id, OcenaDTO ocenaDTO) {
        Ocena ocena = getOcena(id);

        modifyOcena(ocena,ocenaDTO);
        ocenaRepo.save(ocena);
    }
    private void modifyOcena(Ocena ocena, OcenaDTO ocenaDTO) {
        ocena.setNazwa(ocenaDTO.getNazwa());
        ocena.setDataPoczatkowa(ocenaDTO.getDataPoczatkowa());
        ocena.setDataKoncowa(ocenaDTO.getDataKoncowa());
    }
    @Transactional
    public void zatwierdzOcene(long id) {
        Ocena ocena = getOcena(id);
        ocena.setZatwierdzona(true);
        ocenaRepo.save(ocena);

        osiagniecieService.zarchiwizujOsiagniecia(getOsiagnieciaOceny(id));
    }
    @Transactional
    public void deleteOcena(long id) {
        Ocena ocena = getOcena(id);
        ocenaRepo.delete(ocena);
    }
    public boolean canUserAccessThisOcena(long idOceny,long idPracownika){
        Ocena ocena  = getOcena(idOceny);
        return ocena.getPracownik().getId() == idPracownika;
    }
    public boolean czyZatwierdzona(long idOceny) {
        return getOcena(idOceny).getZatwierdzona();
    }
    public List<OcenaDTO> convertListToDTO(List<Ocena> ocenaList) {
        return ocenaList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public OcenaDTO convertToDTO(Ocena ocena) {
        return OcenaDTO.builder()
                .id(ocena.getId())
                .nazwa(ocena.getNazwa())
                .dataPoczatkowa(ocena.getDataPoczatkowa())
                .dataKoncowa(ocena.getDataKoncowa())
                .czyZatwierdzona(ocena.getZatwierdzona())
                .idPracownika(ocena.getPracownik().getId())
                .iloscPunktow(
                        getOsiagnieciaOceny(ocena.getId())
                        .stream()
                        .mapToInt(Osiagniecie::getIloscPunktow)
                        .sum())
                .build();
    }





}
