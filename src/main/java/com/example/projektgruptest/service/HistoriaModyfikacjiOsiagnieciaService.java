package com.example.projektgruptest.service;

import com.example.projektgruptest.model.HistoriaModyfikacjiOsiagniecia;
import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Pracownik;
import com.example.projektgruptest.modelDTO.HistoriaModyfikacjiOsiagnieciaDTO;
import com.example.projektgruptest.repo.HistoriaModyfikacjiOsiagniecRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HistoriaModyfikacjiOsiagnieciaService {
    private final HistoriaModyfikacjiOsiagniecRepo historiaModyfikacjiOsiagniecRepo;
    public Set<HistoriaModyfikacjiOsiagnieciaDTO> convertListToDTOHistoria(Set<HistoriaModyfikacjiOsiagniecia> historiaModyfikacjiOsiagnieciaList)
    {
        return historiaModyfikacjiOsiagnieciaList.stream()
                .map(this::convertToDTOHistoria)
                .collect(Collectors.toSet());
    }
    public HistoriaModyfikacjiOsiagnieciaDTO convertToDTOHistoria(HistoriaModyfikacjiOsiagniecia historiaModyfikacjiOsiagniecia)
    {
        return HistoriaModyfikacjiOsiagnieciaDTO.builder()
                .id(historiaModyfikacjiOsiagniecia.getId())
                .imie(historiaModyfikacjiOsiagniecia.getImie())
                .nazwisko(historiaModyfikacjiOsiagniecia.getNazwisko())
                .idPracownika(historiaModyfikacjiOsiagniecia.getIdPracownika())
                .data(historiaModyfikacjiOsiagniecia.getData())
                .build();
    }
    public void dodajHistorieEdycji(Pracownik pracownik, Osiagniecie osiagniecie)
    {
        HistoriaModyfikacjiOsiagniecia historiaModyfikacjiOsiagniecia = HistoriaModyfikacjiOsiagniecia.builder()
                .imie(pracownik.getImie())
                .nazwisko(pracownik.getNazwisko())
                .idPracownika(pracownik.getId())
                .data(new Date())
                .osiagniecie(osiagniecie)
                .build();
        historiaModyfikacjiOsiagniecRepo.save(historiaModyfikacjiOsiagniecia);

    }
}
