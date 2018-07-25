import { SexeEnum } from '../enumerations/sexe.enum';

export class Candidat {
    id: number;
    utilisateurId: number;
    nom: string;
    prenom: string;
    // format UTC
    dateNaissance: string;
    ville: string;
    sexe: SexeEnum;
    urlAvatar: string;

}
