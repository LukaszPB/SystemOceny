-- Tworzenie tabeli Grupa
CREATE TABLE IF NOT EXISTS Grupa (
                                     id SERIAL PRIMARY KEY,
                                     nazwa VARCHAR(255)
    );

-- Tworzenie tabeli PodKategorie
CREATE TABLE IF NOT EXISTS PodKategorie (
                                            idPodKategorii SERIAL PRIMARY KEY,
                                            nazwa VARCHAR(255),
    minPunktow INTEGER,
    maxPunktow INTEGER,
    grupa_id BIGINT,
    FOREIGN KEY (grupa_id) REFERENCES Grupa(id)
    );

-- Tworzenie tabeli Osiagniecia
CREATE TABLE IF NOT EXISTS Osiagniecia (
                                           idOsiagniecia SERIAL PRIMARY KEY,
                                           nazwa VARCHAR(255),
    iloscPunktow INTEGER,
    data DATE,
    czyZatwierdzone BOOLEAN,
    podKategoria_id BIGINT,
    pracownik_id BIGINT,
    FOREIGN KEY (podKategoria_id) REFERENCES PodKategorie(idPodKategorii),
    FOREIGN KEY (pracownik_id) REFERENCES Pracownik(idPracownika)
    );

-- Tworzenie tabeli PracownikStanowisko
CREATE TABLE IF NOT EXISTS PracownikStanowisko (
                                                   idStanowiska SERIAL PRIMARY KEY,
                                                   nazwa VARCHAR(255)
    );

-- Tworzenie tabeli StopnieNaukowe
CREATE TABLE IF NOT EXISTS StopnieNaukowe (
                                              idStopniaNaukowego SERIAL PRIMARY KEY,
                                              nazwa VARCHAR(255)
    );

-- Tworzenie tabeli KryteriaOceny
CREATE TABLE IF NOT EXISTS KryteriaOceny (
                                             IdKryterium SERIAL PRIMARY KEY,
                                             czyPosiadaStopienNaukowy BOOLEAN,
                                             czyNaStanowiskuKierowniczym BOOLEAN,
                                             progPozytywnejOcenyNB INTEGER,
                                             progOcenyZWyroznieniemNB INTEGER,
                                             progPozytywnejOcenyDO INTEGER,
                                             progOcenyZWyroznieniemDO INTEGER,
                                             grupa_id BIGINT,
                                             FOREIGN KEY (grupa_id) REFERENCES Grupa(id)
    );

-- Tworzenie tabeli Oceny
CREATE TABLE IF NOT EXISTS Oceny (
                                     idOceny SERIAL PRIMARY KEY,
                                     nazwa VARCHAR(255),
    dataPoczatkowa DATE,
    dataKoncowa DATE,
    pracownik_id BIGINT,
    FOREIGN KEY (pracownik_id) REFERENCES Pracownik(idPracownika)
    );

-- Tworzenie tabeli Pracownicy
CREATE TABLE IF NOT EXISTS Pracownicy (
                                          idPracownika SERIAL PRIMARY KEY,
                                          imie VARCHAR(255),
    nazwisko VARCHAR(255),
    Email VARCHAR(255),
    przelozony_id BIGINT,
    grupa_id BIGINT,
    pracownikStanowisko_id BIGINT,
    stopienNaukowy_id BIGINT,
    FOREIGN KEY (przelozony_id) REFERENCES Pracownicy(idPracownika),
    FOREIGN KEY (grupa_id) REFERENCES Grupa(id),
    FOREIGN KEY (pracownikStanowisko_id) REFERENCES PracownikStanowisko(idStanowiska),
    FOREIGN KEY (stopienNaukowy_id) REFERENCES StopnieNaukowe(idStopniaNaukowego)
    );
