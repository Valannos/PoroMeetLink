
INSERT INTO secteur (libelle) VALUES ('DEVELOPPEMENT');

INSERT INTO competence (intitule, id_secteur) VALUES ('C#', (SELECT id
                                                                            FROM secteur
                                                                            WHERE libelle = 'DEVELOPPEMENT'));


