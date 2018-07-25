import { Secteur } from './secteur';

export class Competence {
  id: number;
  intitule: string;
  secteur: Secteur;
  isDeletable: boolean;
}
