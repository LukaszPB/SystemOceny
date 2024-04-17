package com.example.projektgruptest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Plik")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plik {
    @PrimaryKeyJoinColumn
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nazwa;
    private String typ;
    @Lob
    private byte[] plik;
    @ManyToOne
    private Osiagniecie osiagniecie;
}
