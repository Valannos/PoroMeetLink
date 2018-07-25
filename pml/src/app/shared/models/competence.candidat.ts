import { CompetenceCandidatId } from './competence.candidat.id';
import { Competence } from './competence';

export class CompetenceCandidat {
  id: CompetenceCandidatId;
  idCandidat: Number;
  competence: Competence;
  niveau: Number;
}
