create table COMMERCIAL_CATEGORIES_RESPONSABLES
(
    CATEGORIES_RESPONSABLES_ID NUMBER(19) not null,
    COMMERCIAL_ID              NUMBER(19) not null,
    primary key (CATEGORIES_RESPONSABLES_ID, COMMERCIAL_ID)
)
/

create table CATEGORIE
(
    ID  NUMBER(19) generated as identity
        primary key,
    NOM VARCHAR2(255 char)
)
/

create table ARTICLE
(
    PRIX         FLOAT(53) not null,
    CATEGORIE_ID NUMBER(19)
        constraint FKQNMBF0YFA804HXCW8C9GNEB0V
            references CATEGORIE,
    ID           NUMBER(19) generated as identity
        primary key,
    DESCRIPTION  VARCHAR2(255 char),
    NOM          VARCHAR2(255 char)
)
/

create table COMMERCIAL
(
    ID    NUMBER(19) generated as identity
        primary key,
    EMAIL VARCHAR2(255 char),
    NOM   VARCHAR2(255 char)
)
/

create table DEVIS
(
    EST_VALIDE      NUMBER(1) not null
        check (est_valide in (0, 1)),
    MONTANT_TOTALHT FLOAT(53) not null,
    CLIENT_ID       NUMBER(19),
    COMMERCIAL_ID   NUMBER(19),
    DATE_CREATION   TIMESTAMP(6),
    ID              NUMBER(19) generated as identity
        primary key
)
/

create table LIGNE_DE_DEVIS
(
    PRIX_UNITAIRE FLOAT(53)  not null,
    QUANTITE      NUMBER(10) not null,
    ARTICLE_ID    NUMBER(19)
        constraint FKQK8EG74BD2BEMBXN14KN1XN6V
            references ARTICLE,
    DEVIS_ID      NUMBER(19)
        constraint FK6XFWJTJRDPHHI79A6KJQ5I8HL
            references DEVIS,
    ID            NUMBER(19) generated as identity
        primary key
)
/

create table PANIER
(
    EST_VALIDE      NUMBER(1) not null
        check (est_valide in (0, 1)),
    MONTANT_TOTALHT FLOAT(53) not null,
    CLIENT_ID       NUMBER(19),
    ID              NUMBER(19) generated as identity
        primary key
)
/

create table LIGNE_DE_PANIER
(
    PRIX_UNITAIRE FLOAT(53)  not null,
    QUANTITE      NUMBER(10) not null,
    ARTICLE_ID    NUMBER(19)
        constraint FKBO4IORI9IO1U2S7E758SKMTFJ
            references ARTICLE,
    ID            NUMBER(19) generated as identity
        primary key,
    PANIER_ID     NUMBER(19)
        constraint FK9A79XYBL883D9SMVKNDFTCFV3
            references PANIER
)
/