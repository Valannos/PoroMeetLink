
INSERT INTO secteur (libelle) VALUES ('DEVELOPPEMENT');
INSERT INTO secteur (libelle) VALUES ('CHIMIE');

INSERT INTO competence (intitule, id_secteur) VALUES ('C#', (SELECT id
                                                             FROM secteur
                                                             WHERE libelle = 'DEVELOPPEMENT'));

INSERT INTO competence (intitule, id_secteur) VALUES ('Java 8', (SELECT id
                                                             FROM secteur
                                                             WHERE libelle = 'DEVELOPPEMENT'));


INSERT INTO utilisateur (username, password, email) VALUES ('cand', '$2a$10$rz1AsntI5LGVB6dk3cZ9Uu2iFeHG2HOJa8rHRq3QPoFtXvhZ9NjMq' , 'candidat@cand.fr');

INSERT INTO candidat (date_naissance, nom, prenom, ville, id_utilisateur)
        VALUES ('1985-01-02 00:00:00', 'my_name', 'my_first_name', 'Brest',
              (SELECT id
              FROM utilisateur
              WHERE username = 'cand'));

INSERT INTO roles_utilisateurs (id_utilisateur, id_role)
SELECT u.id, r.id FROM utilisateur u
INNER JOIN role r
ON r.name = 'ROLE_CANDIDAT'
WHERE username = 'cand';