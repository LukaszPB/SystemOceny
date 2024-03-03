package com.example.projektgruptest.service;

import com.example.projektgruptest.exception.ResourceNotFoundException;
import com.example.projektgruptest.model.OkresRozliczeniowy;
import com.example.projektgruptest.model.Wniosek;
import com.example.projektgruptest.modelDTO.WniosekDTO;
import com.example.projektgruptest.repo.WniosekRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WniosekService {

    private final OkresRozliczeniowyService okresRozliczeniowyService;
    private final PracownikService pracownikService;

    private final WniosekRepo wniosekRepo;
    public Wniosek getWniosek(long id) {
        return wniosekRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Wniosek o podanym id nie zostal znaleziony: " + id));
    }

    public List<Wniosek> getWnioskiPracownika(long id) {
        return wniosekRepo.findByPracownikIdPracownika(id);
    }
    public List<WniosekDTO> convertListToDTO(List<Wniosek> wnioskiList)
    {
        return wnioskiList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public WniosekDTO convertToDTO(Wniosek wniosek)
    {
        if(wniosek.getOcena()!=null)
        {
            return WniosekDTO.builder()
                    .idWniosku(wniosek.getIdWniosku())
                    .idPracownika(wniosek.getPracownik().getIdPracownika())
                    .dataPoczatkowa(wniosek.getOkresRozliczeniowy().getPoczatek())
                    .dataKoncowa(wniosek.getOkresRozliczeniowy().getKoniec())
                    .idOceny(wniosek.getOcena().getIdOceny())
                    .listaIdOsiagniec(wniosek.getOsiagniecieSet().stream().map(osiagniecie -> osiagniecie.getIdOsiagniecia()).collect(Collectors.toList()))
                    .build();
        }
        else
        {
            return WniosekDTO.builder()
                    .idWniosku(wniosek.getIdWniosku())
                    .idPracownika(wniosek.getPracownik().getIdPracownika())
                    .dataPoczatkowa(wniosek.getOkresRozliczeniowy().getPoczatek())
                    .dataKoncowa(wniosek.getOkresRozliczeniowy().getKoniec())
                    .idOceny(null)
                    .listaIdOsiagniec(wniosek.getOsiagniecieSet().stream().map(osiagniecie -> osiagniecie.getIdOsiagniecia()).collect(Collectors.toList()))
                    .build();
        }
    }
    public void addWniosek(WniosekDTO wniosekDTO) {
        Wniosek wniosek = buildWniosek(wniosekDTO);
        wniosekRepo.save(wniosek);
    }
    private Wniosek buildWniosek(WniosekDTO wniosekDTO){
        OkresRozliczeniowy okresRozliczeniowy = OkresRozliczeniowy.builder()
                .poczatek(wniosekDTO.getDataPoczatkowa())
                .koniec(wniosekDTO.getDataKoncowa())
                .build();
        okresRozliczeniowyService.addOkresRozliczeniowy(okresRozliczeniowy);
        return Wniosek.builder()
                .osiagniecieSet(new HashSet<>())
                .okresRozliczeniowy(okresRozliczeniowy)
                .pracownik(pracownikService.getPracownik(wniosekDTO.getIdPracownika()))
                .ocena(null)
                .build();
    }
    public void deleteWniosek(Wniosek wniosek) {
        wniosekRepo.delete(wniosek);
    }
}
