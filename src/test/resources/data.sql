INSERT INTO secteur (libelle) VALUES ('CHIMIE');
INSERT INTO secteur (libelle) VALUES ('PHYSIQUE');
INSERT INTO secteur (libelle) VALUES ('BIOLOGIE');

/**
INSERT INTO competence (intitule, id_secteur) VALUES ('Analyses Chimiques', (SELECT id
                                                                             FROM secteur
                                                                             WHERE libelle = 'CHIMIE'));
INSERT INTO competence (intitule, id_secteur) VALUES ('Synthèse Chimique', (SELECT id
                                                                            FROM secteur
                                                                            WHERE libelle = 'CHIMIE'));


**/