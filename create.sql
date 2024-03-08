create sequence kategorie_osiagniec_seq start with 1 increment by 50;
create sequence kryteria_oceny_seq start with 1 increment by 50;
create sequence oceny_seq start with 1 increment by 50;
create sequence okres_rozliczeniowy_seq start with 1 increment by 50;
create sequence osiagniecia_seq start with 1 increment by 50;
create sequence pod_kategorie_seq start with 1 increment by 50;
create sequence pracownicy_seq start with 1 increment by 50;
create sequence pracownik_stanowiska_seq start with 1 increment by 50;
create sequence rodzaje_dzialalnosci_seq start with 1 increment by 50;
create sequence role_seq start with 1 increment by 50;
create sequence stopnie_naukowe_seq start with 1 increment by 50;
-- create table kategorie_osiagniec (id_kategoria_osiagniec bigint not null, rodzaj_dzialalnosci_id_rodzaj_dzialalnosci bigint, nazwa_kategorii varchar(255), primary key (id_kategoria_osiagniec));
-- create table kryteria_oceny (czy_na_stanowisku_kierowniczym boolean, czy_posiada_stopien_naukowy boolean, prog_ocenyzwyroznieniemdo integer, prog_ocenyzwyroznieniemnb integer, prog_pozytywnej_ocenydo integer, prog_pozytywnej_ocenynb integer, id_kryterium bigint not null, rodzaj_dzialalnosci_id_rodzaj_dzialalnosci bigint, primary key (id_kryterium));
-- create table oceny (ilosc_punktow integer, data timestamp(6), id_oceny bigint not null, wniosek_id_wniosku bigint unique, nazwa varchar(255), primary key (id_oceny));
-- create table okres_rozliczeniowy (id_okresu bigint not null, koniec timestamp(6), poczatek timestamp(6), primary key (id_okresu));
-- create table osiagniecia (czy_zatwierdzone boolean, ilosc_punktow integer, data timestamp(6), id_osiagniecia bigint not null, pod_kategoria_id_pod_kategorii bigint, wniosek_id_wniosku bigint, nazwa varchar(255), primary key (id_osiagniecia));
-- create table pod_kategorie (max_punktow integer, min_punktow integer, id_pod_kategorii bigint not null, kategoria_osiagniec_id_kategoria_osiagniec bigint, nazwa varchar(255), primary key (id_pod_kategorii));
-- create table pracownicy (id_pracownika bigint not null, id_przelozony bigint, pracownik_stanowisko_id_stanowiska bigint, rodzaj_dzialalnosci_id_rodzaj_dzialalnosci bigint, stopien_naukowy_id_stopnia_naukowego bigint, email varchar(255), imie varchar(255), nazwisko varchar(255), primary key (id_pracownika));
-- create table pracownik_stanowiska (id_stanowiska bigint not null, nazwa varchar(255), primary key (id_stanowiska));
-- create table rodzaje_dzialalnosci (id_rodzaj_dzialalnosci bigint not null, nazwa varchar(255), primary key (id_rodzaj_dzialalnosci));
-- create table role (id_roli bigint not null, nazwa varchar(255), primary key (id_roli));
-- create table stopnie_naukowe (id_stopnia_naukowego bigint not null, nazwa varchar(255), primary key (id_stopnia_naukowego));
-- create table uzytkownik (id bigserial not null, pracownik_id_pracownika bigint unique, rola_id_roli bigint not null, haslo varchar(255), login varchar(255), primary key (id));
-- create table wnioski (id_wniosku bigint not null, okres_rozliczeniowy_id_okresu bigint, pracownik_id_pracownika bigint, primary key (id_wniosku));
-- alter table if exists kategorie_osiagniec add constraint FK6rl6ruv4gl5cqvwdy411gmolq foreign key (rodzaj_dzialalnosci_id_rodzaj_dzialalnosci) references rodzaje_dzialalnosci;
-- alter table if exists kryteria_oceny add constraint FKjwgelpwbfqi2n8lkl8r6cpiy foreign key (rodzaj_dzialalnosci_id_rodzaj_dzialalnosci) references rodzaje_dzialalnosci;
-- alter table if exists oceny add constraint FKblfmw30k4q0rgwj37r1221odt foreign key (wniosek_id_wniosku) references wnioski;
-- alter table if exists osiagniecia add constraint FK10m0jwlfnsi8x7nbdddsibjpm foreign key (pod_kategoria_id_pod_kategorii) references pod_kategorie;
-- alter table if exists osiagniecia add constraint FKn0s94uq8dnw6yjqtp2pin2qy7 foreign key (wniosek_id_wniosku) references wnioski;
-- alter table if exists pod_kategorie add constraint FK4g84to79c1x7c6owv1qe7m26x foreign key (kategoria_osiagniec_id_kategoria_osiagniec) references kategorie_osiagniec;
-- alter table if exists pracownicy add constraint FKsobhrx984btqvoq2qt8ogq1r foreign key (pracownik_stanowisko_id_stanowiska) references pracownik_stanowiska;
-- alter table if exists pracownicy add constraint FKrpadw0b4bh6he6fufm6cn1hhv foreign key (id_przelozony) references pracownicy;
-- alter table if exists pracownicy add constraint FKo5v8u4btp7shrfmcg4uys0pfi foreign key (rodzaj_dzialalnosci_id_rodzaj_dzialalnosci) references rodzaje_dzialalnosci;
-- alter table if exists pracownicy add constraint FKf6y3ubcn5h5cbq10ucw0vl4nb foreign key (stopien_naukowy_id_stopnia_naukowego) references stopnie_naukowe;
-- alter table if exists uzytkownik add constraint FK3jm3g7cck2wbmr6ym7c4sedr8 foreign key (pracownik_id_pracownika) references pracownicy;
-- alter table if exists uzytkownik add constraint FKmsrm21mb0kjaggsthxagrmuga foreign key (rola_id_roli) references role;
-- alter table if exists wnioski add constraint FKn40k5x920p9p5e8fnfymogson foreign key (okres_rozliczeniowy_id_okresu) references okres_rozliczeniowy;
-- alter table if exists wnioski add constraint FKmrf5ritjwcqo1xckuka93s2qu foreign key (pracownik_id_pracownika) references pracownicy;
