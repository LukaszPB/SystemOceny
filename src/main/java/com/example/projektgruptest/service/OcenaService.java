package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.Ocena;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.modelDTO.DodawanieOcenDTO;
import com.example.projektgruptest.modelDTO.OcenaDTO;
import com.example.projektgruptest.modelDTO.OsiagniecieDTO;
import com.example.projektgruptest.repo.OcenaRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OcenaService {
    private final OcenaRepo ocenaRepo;
    private final OsiagniecieService osiagniecieService;
    private final PracownikService pracownikService;
    private final KryteriaOcenyService kryteriaOcenyService;
    public Ocena getOcena(long id) {
        return ocenaRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "Ocena o podanym id nie istnieje: " + id));
    }
    public List<Ocena> getOcenyPracownika(Long idPracownika) {
       return ocenaRepo.findByPracownik_Id(idPracownika);
    }
    public List<OsiagniecieDTO> getOsiagnieciaZOcenyUsera(Long idOceny, Long idPracownika){
        if(canUserAccessThisOcena(idOceny,idPracownika)){
            return osiagniecieService.convertListToDTO(getOsiagnieciaOceny(idOceny));
        }
        else {
            return null;
        }
    }
    public List<Osiagniecie> getOsiagnieciaOceny(Long idOceny) {
        List<Osiagniecie> listaOsiagniec = new ArrayList<>();
        Ocena ocena = getOcena(idOceny);
        for(Osiagniecie osiagniecie : osiagniecieService.getOsiagnieciaPracownika(ocena.getPracownik().getId())) {
            if(osiagniecie.getOcena() != null && idOceny.equals(osiagniecie.getOcena().getId())) {
                listaOsiagniec.add(osiagniecie);
            }
        }
        return listaOsiagniec;
    }
    public void addOceny(DodawanieOcenDTO dodawanieOcenDTO) {
        Date dataPoczatkowa = dodawanieOcenDTO.getDataPoczatkowa();
        Date dataKoncowa = dodawanieOcenDTO.getDataKoncowa();

        dodawanieOcenDTO.getPracownikDTOList()
                .stream()
                .map(pracownikDTO -> pracownikService.getPracownik(pracownikDTO.getId()))
                .filter(pracownik ->
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(dataPoczatkowa);
                    calendar.add(Calendar.MONTH, -12);
                    Date data12MiesiecyWstecz = calendar.getTime();
                    return pracownik.getDataOstatniejOceny().before(data12MiesiecyWstecz);
                })
                .forEach(pracownik -> addOcena(Ocena.builder()
                                .dataPoczatkowa(dataPoczatkowa)
                                .dataKoncowa(dataKoncowa)
                                .pracownik(pracownik)
                                .zatwierdzona(false)
                                .build()));
    }
    @Transactional
    public void addOcena(Ocena ocena) {
        ocenaRepo.save(ocena);

        osiagniecieService.przypiszOsiagnieciaOcenie(ocena);
        ocena.setWynikOceny(kryteriaOcenyService.wyliczWynikOceny(ocena));

        ocenaRepo.save(ocena);
        pracownikService.updateDataOstatniejOceny(ocena.getPracownik(),ocena.getDataKoncowa());
    }
    public Ocena buildOcena(OcenaDTO ocenaDTO) {
        return Ocena.builder()
                .wynikOceny(ocenaDTO.getWynikOceny())
                .dataPoczatkowa(ocenaDTO.getDataPoczatkowa())
                .dataKoncowa(ocenaDTO.getDataKoncowa())
                .pracownik(pracownikService.getPracownik(ocenaDTO.getIdPracownika()))
                .zatwierdzona(false)
                .build();
    }
    @Transactional
    public void zatwierdzOcene(Long id) {
        Ocena ocena = getOcena(id);
        ocena.setZatwierdzona(true);
        ocenaRepo.save(ocena);
    }
    @Transactional
    public void deleteOcena(Long id) {
        Ocena ocena = getOcena(id);
        ocenaRepo.delete(ocena);
    }
    public boolean canUserAccessThisOcena(Long idOceny,Long idPracownika){
        Ocena ocena  = getOcena(idOceny);
        return Objects.equals(ocena.getPracownik().getId(), idPracownika);
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
                .wynikOceny(ocena.getWynikOceny())
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
