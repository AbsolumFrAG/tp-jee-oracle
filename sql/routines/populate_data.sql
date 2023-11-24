CREATE OR REPLACE PROCEDURE populate_data AS
BEGIN
    FOR i IN 1..20 LOOP
            INSERT INTO CATEGORIE (NOM) VALUES ('Catégorie ' || i);
        END LOOP;

    FOR i IN 1..1000 LOOP
            INSERT INTO ARTICLE (NOM, DESCRIPTION, PRIX, CATEGORIE_ID)
            VALUES ('Article ' || i, 'Description de l''article ' || i, DBMS_RANDOM.VALUE(10, 1000), MOD(i, 20) + 1);
        END LOOP;

    FOR i IN 1..4 LOOP
            INSERT INTO COMMERCIAL (NOM, EMAIL)
            VALUES ('Commercial ' || i, 'commercial' || i || '@jevendstout.com');
        END LOOP;

    INSERT INTO COMMERCIAL (NOM, EMAIL)
    VALUES ('Directeur commercial', 'directeur@jevendstout.com');
END;
/