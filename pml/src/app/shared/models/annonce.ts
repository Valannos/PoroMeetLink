import { CompetenceAnnonce } from './competence.annonce';

export class Annonce {
  id: number;
  intitule: string;
  dateCreation: string;
  dateDebut: string;
  niveauExperience: string;
  dateFin: string;
  valid: boolean;
  description: string;
  competenceAnnonce: CompetenceAnnonce[];
  typeContrat: TypeContrat;
  employeId: number;
}

export enum TypeContrat {
  CDD = 'CDD',
  CDI = 'CDI',
  STAGE = 'STAGE'
}
