export class Role {
  name: String;
  id: number;
  constructor(name: String) {
    this.name = name;
  }
}

export enum RoleEnum {
  AdministrateurSite = 'ROLE_ADMINISTRATEUR_SITE',
  AdministrateurCompte = 'ROLE_ADMINISTRATEUR_COMPTE',
  Gerant = 'ROLE_GERANT',
  Demarcheur = 'ROLE_DEMARCHEUR',
  Candidat = 'ROLE_CANDIDAT',
  SuperControleur = 'ROLE_SUPER_CONTROLEUR',
  SuperRapporteur = 'ROLE_SUPER_RAPPORTEUR',
  Recruteur = 'ROLE_RECRUTEUR'
}
