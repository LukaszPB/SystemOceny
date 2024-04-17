package com.example.projektgruptest.service;

import com.example.projektgruptest.model.Osiagniecie;
import com.example.projektgruptest.model.Plik;
import com.example.projektgruptest.repo.PlikRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@AllArgsConstructor
public class PlikService {
    private final PlikRepo plikRepo;
    public void dodajPlik(MultipartFile plik, Osiagniecie osiagniecie) throws IOException {
        plikRepo.save(Plik.builder()
                .nazwa(plik.getName())
                .typ(plik.getContentType())
                .plik(skompresujPlik(plik.getBytes()))
                .osiagniecie(osiagniecie)
                .build());

    }
    @Transactional
    public List<byte[]> getPliki(Osiagniecie osiagniecie) {
        return plikRepo.findByOsiagniecie(osiagniecie)
                .stream()
                .map(plik->zdekompresujPlik(plik.getPlik()))
                .collect(Collectors.toList());
    }
    public static byte[] skompresujPlik(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
    public static byte[] zdekompresujPlik(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
