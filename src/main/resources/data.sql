
-- DROP TABLE grupa,kryteria_oceny,oceny,osiagniecia,pod_kategorie,pracownicy,pracownik_stanowiska,role,stopnie_naukowe,uzytkownik;

-- ROLE
INSERT INTO "role" (id, nazwa) VALUES (nextval('role_seq'), 'ADMIN');
INSERT INTO "role" (id, nazwa) VALUES (nextval('role_seq'), 'KOMISJA');
INSERT INTO "role" (id, nazwa) VALUES (nextval('role_seq'), 'PRACOWNIK');




--GRUPA
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'ZWYKLYPRACOWNIK');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'DN');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'OIN');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'OWI');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'BRPM');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'BWM');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'DJK');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'WYDZIALY');  --ROZPISAĆ NA POSZCZEGÓLNE WYDZIAŁY
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'JEDN.OGOLNOUCZ.');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'DSP');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'SJO');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'CSSDiR');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'DJK');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'BON');
INSERT INTO "grupa" (id, nazwa)
VALUES (nextval('grupa_seq'), 'SWFiS');

-- STOPIEŃ NAUKOWY
INSERT INTO "stopnie_naukowe" (id, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'MAGISTER');
INSERT INTO "stopnie_naukowe" (id, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'DOKTOR');
INSERT INTO "stopnie_naukowe" (id, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'DOKTOR HABILITOWANY');
INSERT INTO "stopnie_naukowe" (id, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'PROFESOR');

-- KRYTERIA OCENY
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (true, true, 60, 250, 60, 40, nextval('kryteria_oceny_seq'), 1);

INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (false, true, 50, 250, 50, 50, nextval('kryteria_oceny_seq'), 1);

INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (false, false, 50, 150, 40, 40, nextval('kryteria_oceny_seq'), 1);

INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (true, true, 300, 0, 100, 0, nextval('kryteria_oceny_seq'), 1);

INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (false, true, 300, 0, 100, 0, nextval('kryteria_oceny_seq'), 1);

INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym, czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo, prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo, prog_pozytywnej_ocenynb, id_kryterium,
                              grupa_id)
VALUES (false, false, 200, 0, 80, 0, nextval('kryteria_oceny_seq'), 1);

-- STANOWISKA
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'DZIEKAN');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'PRODZIEKAN');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'WYKŁADOWCA');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'PRACOWNIK ADMINISTRACYJNY');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'BIBLJOTEKARZ');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'ADIUNKT');
INSERT INTO "pracownik_stanowiska" (id, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'KIEROWNIK BADAŃ');

-- PRACOWNIK
INSERT INTO "pracownicy" (id,grupa_id, pracownik_stanowisko_id,stopien_naukowy_id, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'),
       (SELECT id FROM "grupa" WHERE nazwa = 'WYDZIALY'),
       (SELECT id FROM "pracownik_stanowiska" WHERE nazwa = 'DZIEKAN'),
       (SELECT id FROM "stopnie_naukowe" WHERE nazwa = 'PROFESOR'),
       'zenonWolski@pb.edu.pl', 'ZENON', 'WOLSKI';

INSERT INTO "pracownicy" (id,grupa_id, przelozony, pracownik_stanowisko_id,stopien_naukowy_id, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'),
       (SELECT id FROM "grupa" WHERE nazwa = 'ZWYKLYPRACOWNIK'),
       1,
       (SELECT id FROM "pracownik_stanowiska" WHERE nazwa = 'WYKŁADOWCA'),
       (SELECT id FROM "stopnie_naukowe" WHERE nazwa = 'DOKTOR'),
       'janKowalski@pb.edu.pl', 'JAN', 'KOWALSKI';

INSERT INTO "pracownicy" (id,grupa_id, przelozony, pracownik_stanowisko_id,stopien_naukowy_id, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'),
       (SELECT id FROM "grupa" WHERE nazwa = 'BRPM'),
       1,
       (SELECT id FROM "pracownik_stanowiska" WHERE nazwa = 'PRACOWNIK ADMINISTRACYJNY'),
       (SELECT id FROM "stopnie_naukowe" WHERE nazwa = 'MAGISTER'),
       'michalWojcicki@pb.edu.pl', 'MICHAŁ', 'WÓJCICKI';

INSERT INTO "pracownicy" (id,grupa_id, przelozony, pracownik_stanowisko_id, stopien_naukowy_id, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'),
       (SELECT id FROM "grupa" WHERE nazwa = 'ZWYKLYPRACOWNIK'),
       1,
       (SELECT id FROM "pracownik_stanowiska" WHERE nazwa = 'KIEROWNIK BADAŃ'),
       (SELECT id FROM "stopnie_naukowe" WHERE nazwa = 'DOKTOR'),
       'krzysztofAdamczyk@pb.edu.pl', 'KRZYSZTOF', 'ADAMCZYK';


--PodKategorie
INSERT INTO "pod_kategorie" (max_punktow, min_punktow, id_pod_kategorii, nazwa, grupa_id)
SELECT 160, 160, nextval('pod_kategorie_seq'), 'Uzyskanie tytułu profesora',
       (SELECT id FROM "grupa" WHERE nazwa = 'WYDZIALY');

INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,nazwa,grupa_id)
SELECT 120,120,nextval('pod_kategorie_seq'),'Uzyskanie stopnia doktora habilitowanego',
    (SELECT id FROM "grupa" WHERE nazwa = 'WYDZIALY');

INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,nazwa,grupa_id)
SELECT 60,60,nextval('pod_kategorie_seq'),'Uzyskanie stopnia doktora',
    (SELECT id FROM "grupa" WHERE nazwa = 'BRPM');

INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,nazwa,grupa_id)
SELECT 30,10,nextval('pod_kategorie_seq'),'Publikacje naukowe',
    (SELECT id FROM "grupa" WHERE nazwa = 'BRPM');

INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,nazwa,grupa_id)
SELECT 20,10,nextval('pod_kategorie_seq'),'Patenty na wynalazki, prawa ochronne na wzory użytkowe i wyłączne prawa
hodowców do odmian roślin',
    (SELECT id FROM "grupa" WHERE nazwa = 'BRPM');
-----

-- Ocena
INSERT INTO "oceny" (data_poczatkowa,data_koncowa,id,pracownik_id,wynik_oceny,zatwierdzona)
VALUES ('2023-10-25 21:32:00','2025-12-30 21:32:00',nextval('oceny_seq'),1,'POZYTYWNA',true);

INSERT INTO "oceny" (data_poczatkowa,data_koncowa,id,pracownik_id,wynik_oceny,zatwierdzona)
VALUES ('2023-10-25 21:32:00','2025-12-30 21:32:00',nextval('oceny_seq'),51,'POZYTYWNA_Z_WYRÓŻNIENIEM',false);

INSERT INTO "oceny" (data_poczatkowa,data_koncowa,id,pracownik_id,wynik_oceny,zatwierdzona)
VALUES ('2022-10-25 21:32:00','2025-12-30 21:32:00',nextval('oceny_seq'),101,'NEGATYWNA',false);

INSERT INTO "oceny" (data_poczatkowa,data_koncowa,id,pracownik_id,wynik_oceny,zatwierdzona)
VALUES ('2023-10-25 21:32:00','2025-12-30 21:32:00',nextval('oceny_seq'),151,'POZYTYWNA',false);


--Osiągniecie
INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,20,'2023-02-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe',1);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,30,'2023-02-15 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe',1);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,15,'2023-06-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe',1);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (false,false,60,'2022-04-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie stopnia doktora'),
        51,'Uzyskanie stopnia doktora',51);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,25,'2022-05-11 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        51,'Publikacje naukowe',51);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (false,false,60,'2023-02-01 18:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie stopnia doktora'),
        101,'Uzyskanie stopnia doktora',null);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,10,'2023-01-05 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        101,'Publikacje naukowe',null);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,true,10,'2023-01-05 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        101,'Publikacje naukowe',101);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,10,'2021-01-05 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        101,'Publikacje naukowe',101);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,10,'2023-04-04 20:30:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,30,'2023-04-04 20:45:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,20,'2023-02-21 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (false,false,10,'2023-07-15 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,15,'2023-10-11 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,10,'2022-01-07 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe',151);

INSERT INTO "osiagniecia" (zatwierdzone,zarchiwizowane,ilosc_punktow,data,id,pod_kategoria_id_pod_kategorii,pracownik_id,nazwa,ocena_id)
VALUES (true,false,160,'2022-02-06 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie tytułu profesora'),
        1,'Uzyskano tytul profesora',1);


-- UZYTKOWNIK
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 2137, 'ADMIN', 'ADMIN', id, null FROM "role" WHERE nazwa = 'ADMIN';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 420, 'KOMISJA', 'KOMISJA', id, null FROM "role" WHERE nazwa = 'KOMISJA';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 69, 'PRACOWNIK', 'PRACOWNIK', id, 1 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 1000, 'PRACOWNIK1', 'PRACOWNIK', id, 51 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 2000, 'PRACOWNIK2', 'PRACOWNIK', id, 101 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id, pracownik_id)
SELECT 3000, 'PRACOWNIK3', 'PRACOWNIK', id, 151 FROM "role" WHERE nazwa = 'PRACOWNIK';
