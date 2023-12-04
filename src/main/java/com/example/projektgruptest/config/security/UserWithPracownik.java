package com.example.projektgruptest.config.security;

import com.example.projektgruptest.model.Pracownik;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Setter
@Getter
public class UserWithPracownik extends User {

    private Pracownik pracownik;

    public UserWithPracownik(String username, String password, Collection<? extends GrantedAuthority> authorities, Pracownik pracownik) {
        super(username, password, authorities);
        this.pracownik = pracownik;
    }
}
