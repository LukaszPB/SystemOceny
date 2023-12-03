-- ROLE
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'ADMIN');
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'KOMISJA');
INSERT INTO "role" (id_roli, nazwa) VALUES (nextval('role_seq'), 'PRACOWNIK');

-- UZYTKOWNIK
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 2137, 'ADMIN', 'ADMIN', id_roli, null FROM "role" WHERE nazwa = 'ADMIN';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 420, 'KOMISJA', 'KOMISJA', id_roli, null FROM "role" WHERE nazwa = 'KOMISJA';
INSERT INTO "uzytkownik" (id, login, haslo, rola_id_roli, pracownik_id_pracownika)
SELECT 69, 'PRACOWNIK', 'PRACOWNIK', id_roli, null FROM "role" WHERE nazwa = 'PRACOWNIK';
