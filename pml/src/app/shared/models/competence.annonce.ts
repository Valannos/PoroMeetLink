import { Competence } from './competence';

export class CompetenceAnnonce {
  id: CompetenceAnnonceId;
  idAnnonce: number;
  competence: Competence;
  niveauRequis: number;
}

export class CompetenceAnnonceId {
  idAnnonce: number;
  idCompetence: number;
}
