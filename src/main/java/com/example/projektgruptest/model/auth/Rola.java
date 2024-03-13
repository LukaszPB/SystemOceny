package com.example.projektgruptest.model.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name="Role")
@Getter
@Setter
public class Rola {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nazwa")
    private String nazwa;

    //KLUCZE OBCE
    @OneToMany(mappedBy = "rola")
    Set<Uzytkownik> uzytkownikSet;
}
