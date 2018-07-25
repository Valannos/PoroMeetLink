INSERT INTO role SET name = 'ROLE_ADMINISTRATEUR_SITE';
INSERT INTO role SET name = 'ROLE_ADMINISTRATEUR_COMPTE';
INSERT INTO role SET name = 'ROLE_GERANT';
INSERT INTO role SET name = 'ROLE_DEMARCHEUR';
INSERT INTO role SET name = 'ROLE_CANDIDAT';
INSERT INTO role SET name = 'ROLE_SUPER_CONTROLEUR';
INSERT INTO role SET name = 'ROLE_SUPER_RAPPORTEUR';
INSERT INTO role SET name = 'ROLE_RECRUTEUR';

INSERT INTO utilisateur(email, is_super_admin, password, username) VALUES ('masteradmin@poromeetlink.com', 1, '$2a$10$rz1AsntI5LGVB6dk3cZ9Uu2iFeHG2HOJa8rHRq3QPoFtXvhZ9NjMq', 'masteradmin');
INSERT INTO roles_utilisateurs (id_utilisateur, id_role)
SELECT u.id, r.id FROM utilisateur u
INNER JOIN role r
ON r.name = 'ROLE_ADMINISTRATEUR_SITE'
WHERE username = 'masteradmin';