import { TypeDiplome } from './type.diplome';

export class Diplome {
  id: number;
  intitule: string;
  anneeObtention: number;
  etablissement: string;
  typeDiplome: TypeDiplome;
  diplomeEnum: StatutDiplomeEnum;
  idCandidat: number;
}

export enum StatutDiplomeEnum {
  NON_OBTENU = 'NON_OBTENU',
  EN_COURS = 'EN_COURS',
  OBTENU = 'OBTENU',
}
