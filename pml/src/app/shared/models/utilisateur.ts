import { Candidat } from './candidat';
import { Role } from './role';

export class Utilisateur {
    id: number;
    username: string;
    password: string;
    roles: Role[];
    employeId: number;
    candidatId: number;
    dateBloquage: string;
    email: string;
    superAdmin: boolean;

}


