-- ROLE
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'ADMIN');
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'KOMISJA');
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'PRACOWNIK');

-- STOPIEŃ NAUKOWY
INSERT INTO "stopnie_naukowe" (id_stopnia_naukowego, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'MAGISTER');
INSERT INTO "stopnie_naukowe" (id_stopnia_naukowego, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'DOKTOR');
INSERT INTO "stopnie_naukowe" (id_stopnia_naukowego, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'DOKTOR HABILITOWANY');
INSERT INTO "stopnie_naukowe" (id_stopnia_naukowego, nazwa)
VALUES (nextval('stopnie_naukowe_seq'), 'PROFESOR');

-- RODZAJ DZIAŁALNOŚCI
INSERT INTO "rodzaje_dzialalnosci" (id_rodzaj_dzialalnosci, nazwa)
VALUES (nextval('rodzaje_dzialalnosci_seq'), 'NAUKOWO-BADAWCZA');
INSERT INTO "rodzaje_dzialalnosci" (id_rodzaj_dzialalnosci, nazwa)
VALUES (nextval('rodzaje_dzialalnosci_seq'), 'DYDAKTYCZNO-ORGANIZACYJNA');

-- KRYTERIA OCENY
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (true,true,60,250,60,40,nextval('kryteria_oceny_seq'),1);
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (false,true,50,250,50,50,nextval('kryteria_oceny_seq'),1);
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (false,false,50,150,40,40,nextval('kryteria_oceny_seq'),1);
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (true,true,300,0,100,0,nextval('kryteria_oceny_seq'),51);
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (false,true,300,0,100,0,nextval('kryteria_oceny_seq'),51);
INSERT INTO "kryteria_oceny" (czy_na_stanowisku_kierowniczym,czy_posiada_stopien_naukowy,
                              prog_ocenyzwyroznieniemdo,prog_ocenyzwyroznieniemnb,
                              prog_pozytywnej_ocenydo,prog_pozytywnej_ocenynb,id_kryterium,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci)
VALUES (false,false,200,0,80,0,nextval('kryteria_oceny_seq'),51);

-- STANOWISKA
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'DZIEKAN');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'PRODZIEKAN');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'WYKŁADOWCA');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'PRACOWNIK ADMINISTRACYJNY');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'BIBLJOTEKARZ');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'ADIUNKT');
INSERT INTO "pracownik_stanowiska" (id_stanowiska, nazwa)
VALUES (nextval('pracownik_stanowiska_seq'), 'KIEROWNIK BADAŃ');

-- PRACOWNIK
INSERT INTO "pracownicy" (id_pracownika, pracownik_stanowisko_id_stanowiska,
                          rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,
                          stopien_naukowy_id_stopnia_naukowego, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'),
       (SELECT id_stanowiska FROM "pracownik_stanowiska" WHERE nazwa = 'DZIEKAN'),
       (SELECT id_rodzaj_dzialalnosci FROM "rodzaje_dzialalnosci" WHERE nazwa = 'DYDAKTYCZNO-ORGANIZACYJNA'),
       (SELECT id_stopnia_naukowego FROM "stopnie_naukowe" WHERE nazwa = 'PROFESOR'),
       'zenonWolski@pb.edu.pl', 'ZENON', 'WOLSKI';
INSERT INTO "pracownicy" (id_pracownika, id_przelozony, pracownik_stanowisko_id_stanowiska,
                          rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,
                          stopien_naukowy_id_stopnia_naukowego, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'), 1,
       (SELECT id_stanowiska FROM "pracownik_stanowiska" WHERE nazwa = 'WYKŁADOWCA'),
       (SELECT id_rodzaj_dzialalnosci FROM "rodzaje_dzialalnosci" WHERE nazwa = 'NAUKOWO-BADAWCZA'),
       (SELECT id_stopnia_naukowego FROM "stopnie_naukowe" WHERE nazwa = 'DOKTOR'),
       'janKowalski@pb.edu.pl', 'JAN', 'KOWALSKI';
INSERT INTO "pracownicy" (id_pracownika, id_przelozony, pracownik_stanowisko_id_stanowiska,
                              rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,
                              stopien_naukowy_id_stopnia_naukowego, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'), 1,
       (SELECT id_stanowiska FROM "pracownik_stanowiska" WHERE nazwa = 'PRACOWNIK ADMINISTRACYJNY'),
       (SELECT id_rodzaj_dzialalnosci FROM "rodzaje_dzialalnosci" WHERE nazwa = 'DYDAKTYCZNO-ORGANIZACYJNA'),
       (SELECT id_stopnia_naukowego FROM "stopnie_naukowe" WHERE nazwa = 'MAGISTER'),
       'michałWójcicki@pb.edu.pl', 'MICHAŁ', 'WÓJCICKI';
INSERT INTO "pracownicy" (id_pracownika, id_przelozony, pracownik_stanowisko_id_stanowiska,
                          rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,
                          stopien_naukowy_id_stopnia_naukowego, email, imie, nazwisko)
SELECT nextval('pracownicy_seq'), 1,
       (SELECT id_stanowiska FROM "pracownik_stanowiska" WHERE nazwa = 'KIEROWNIK BADAŃ'),
       (SELECT id_rodzaj_dzialalnosci FROM "rodzaje_dzialalnosci" WHERE nazwa = 'NAUKOWO-BADAWCZA'),
       (SELECT id_stopnia_naukowego FROM "stopnie_naukowe" WHERE nazwa = 'DOKTOR'),
       'krzysztofAdamczyk@pb.edu.pl', 'KRZYSZTOF', 'ADAMCZYK';

-- Okres Rozliczeniowy
INSERT INTO "okres_rozliczeniowy" (id_okresu,koniec,poczatek) values (nextval('okres_rozliczeniowy_seq'),
        '2023-12-31 23:59:59','2023-01-01 00:00:00');
INSERT INTO "okres_rozliczeniowy" (id_okresu,koniec,poczatek) values (nextval('okres_rozliczeniowy_seq'),
        '2022-12-31 23:59:59','2022-01-01 00:00:00');

--Kategorie Osiagniec
INSERT INTO "kategorie_osiagniec" (id_kategoria_osiagniec,rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,nazwa_kategorii)
SELECT nextval('kategorie_osiagniec_seq'), id_rodzaj_dzialalnosci, 'Rozwój naukowy' FROM "rodzaje_dzialalnosci" WHERE nazwa = 'NAUKOWO-BADAWCZA';
INSERT INTO "kategorie_osiagniec" (id_kategoria_osiagniec,rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,nazwa_kategorii)
SELECT nextval('kategorie_osiagniec_seq'), id_rodzaj_dzialalnosci, 'Działalność naukowa' FROM "rodzaje_dzialalnosci" WHERE nazwa = 'NAUKOWO-BADAWCZA';
INSERT INTO "kategorie_osiagniec" (id_kategoria_osiagniec,rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,nazwa_kategorii)
SELECT nextval('kategorie_osiagniec_seq'), id_rodzaj_dzialalnosci, 'Projekty obejmujące badania naukowe i prace rozwojowe realizowane w Uczelni' FROM "rodzaje_dzialalnosci" WHERE nazwa = 'NAUKOWO-BADAWCZA';
INSERT INTO "kategorie_osiagniec" (id_kategoria_osiagniec,rodzaj_dzialalnosci_id_rodzaj_dzialalnosci,nazwa_kategorii)
SELECT nextval('kategorie_osiagniec_seq'), id_rodzaj_dzialalnosci, 'Sztuki plastyczne i konserwacja dzieł sztuki' FROM "rodzaje_dzialalnosci" WHERE nazwa = 'DYDAKTYCZNO_ORGANIZACYJNA';

--PodKategorie
INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,kategoria_osiagniec_id_kategoria_osiagniec,nazwa)
SELECT 160,160,nextval('pod_kategorie_seq'),id_kategoria_osiagniec,'Uzyskanie tytułu profesora' FROM "kategorie_osiagniec" WHERE nazwa_kategorii = 'Rozwój naukowy';
INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,kategoria_osiagniec_id_kategoria_osiagniec,nazwa)
SELECT 120,120,nextval('pod_kategorie_seq'),id_kategoria_osiagniec,'Uzyskanie stopnia doktora habilitowanego' FROM "kategorie_osiagniec" WHERE nazwa_kategorii = 'Rozwój naukowy';
INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,kategoria_osiagniec_id_kategoria_osiagniec,nazwa)
SELECT 60,60,nextval('pod_kategorie_seq'),id_kategoria_osiagniec,'Uzyskanie stopnia doktora' FROM "kategorie_osiagniec" WHERE nazwa_kategorii = 'Rozwój naukowy';
INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,kategoria_osiagniec_id_kategoria_osiagniec,nazwa)
SELECT 30,10,nextval('pod_kategorie_seq'),id_kategoria_osiagniec,'Publikacje naukowe' FROM "kategorie_osiagniec" WHERE nazwa_kategorii = 'Działalność naukowa';
INSERT INTO "pod_kategorie" (max_punktow,min_punktow,id_pod_kategorii,kategoria_osiagniec_id_kategoria_osiagniec,nazwa)
SELECT 20,10,nextval('pod_kategorie_seq'),id_kategoria_osiagniec,'Patenty na wynalazki, prawa ochronne na wzory użytkowe i wyłączne prawa
hodowców do odmian roślin' FROM "kategorie_osiagniec" WHERE nazwa_kategorii = 'Działalność naukowa';


--Wniosek
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
        WHERE poczatek='2023-01-01 00:00:00'),
        (SELECT id_pracownika FROM "pracownicy" WHERE imie='JAN' AND nazwisko='KOWALSKI'));
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
        WHERE poczatek='2022-01-01 00:00:00'),
        (SELECT id_pracownika FROM "pracownicy" WHERE imie='JAN' AND nazwisko='KOWALSKI'));
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
    WHERE poczatek='2023-01-01 00:00:00'),
    (SELECT id_pracownika FROM "pracownicy" WHERE imie='MICHAŁ' AND nazwisko='WÓJCICKI'));
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
        WHERE poczatek='2023-01-01 00:00:00'),
        (SELECT id_pracownika FROM "pracownicy" WHERE imie='KRZYSZTOF' AND nazwisko='ADAMCZYK'));
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
        WHERE poczatek='2023-01-01 00:00:00'),
        (SELECT id_pracownika FROM "pracownicy" WHERE imie='ZENON' AND nazwisko='WOLSKI'));
INSERT INTO "wnioski" (id_wniosku,okres_rozliczeniowy_id_okresu,pracownik_id_pracownika)
VALUES (nextval('wnioski_seq'),(SELECT id_okresu FROM "okres_rozliczeniowy"
        WHERE poczatek='2022-01-01 00:00:00'),
        (SELECT id_pracownika FROM "pracownicy" WHERE imie='ZENON' AND nazwisko='WOLSKI'));


--Osiągniecie
INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,20,'2023-02-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,30,'2023-02-15 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,15,'2023-06-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        1,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (false,60,'2022-04-01 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie stopnia doktora'),
        51,'Uzyskanie stopnia doktora');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,25,'2022-05-11 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        51,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (false,60,'2023-02-01 18:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie stopnia doktora'),
        101,'Uzyskanie stopnia doktora');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,10,'2023-01-05 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        101,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,10,'2023-04-04 20:30:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,30,'2023-04-04 20:45:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,20,'2023-02-21 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        151,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (false,10,'2023-07-15 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        201,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,15,'2023-10-11 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        201,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,10,'2022-01-07 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Publikacje naukowe'),
        251,'Publikacje naukowe');

INSERT INTO "osiagniecia" (czy_zatwierdzone,ilosc_punktow,data,id_osiagniecia,pod_kategoria_id_pod_kategorii,wniosek_id_wniosku,nazwa)
VALUES (true,160,'2022-02-06 20:00:00',nextval('osiagniecia_seq'),
        (SELECT id_pod_kategorii FROM "pod_kategorie" WHERE nazwa='Uzyskanie tytułu profesora'),
        251,'Uzyskano tytul profesora');


-- Ocena
INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),1,'pozytywna');

INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),51,'pozytywna z wyróżnieniem');

-- INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
-- VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),101,'pozytywna');

INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),151,'negatywna');

INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),201,'pozytywna');

INSERT INTO "oceny" (ilosc_punktow,data,id_oceny,wniosek_id_wniosku,nazwa)
VALUES (400,'2023-10-25 21:32:00',nextval('oceny_seq'),251,'pozytywna z wyróżnieniem');

-- UZYTKOWNIK
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 2137, 'ADMIN', 'ADMIN', id_roli, null FROM "role" WHERE nazwa = 'ADMIN';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 420, 'KOMISJA', 'KOMISJA', id_roli, null FROM "role" WHERE nazwa = 'KOMISJA';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 69, 'PRACOWNIK', 'PRACOWNIK', id_roli, 1 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 1000, 'PRACOWNIK1', 'PRACOWNIK', id_roli, 51 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 2000, 'PRACOWNIK2', 'PRACOWNIK', id_roli, 101 FROM "role" WHERE nazwa = 'PRACOWNIK';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 3000, 'PRACOWNIK3', 'PRACOWNIK', id_roli, 151 FROM "role" WHERE nazwa = 'PRACOWNIK';

-- Przelozony
-- INSERT INTO "przelozeni" (id,imie,nazwisko,email)
-- VALUES (1,'ROBERT','KUBICA','ADRESEMAIL@GMAIL.COM');

