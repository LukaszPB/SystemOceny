package com.example.projektgruptest.config.security;

import com.example.projektgruptest.repo.UzytkownikRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UzytkownikRepo uzytkownikRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var uzytkownikOptional = uzytkownikRepo.findByLogin(username);

        if(uzytkownikOptional.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }

        var uzytkownik = uzytkownikOptional.get();

        return new UserWithPracownik(
                uzytkownik.getLogin(),
                uzytkownik.getHaslo(),
                Collections.singletonList(new SimpleGrantedAuthority(uzytkownik.getRola().getNazwa())),
                uzytkownik.getPracownik()
        );
    }
}
