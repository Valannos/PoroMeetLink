import { Pipe, PipeTransform } from '@angular/core';
import { RoleEnum } from '../models/role';

@Pipe({
  name: 'role'
})
export class RolePipe implements PipeTransform {
  transform(value: string[]): string {
    let listeRoles: string;
    value.forEach(role => {
      switch (role) {
        case RoleEnum.AdministrateurSite:
          listeRoles = 'Administrateur du Site';
          break;
        case RoleEnum.Candidat:
          listeRoles = 'Candidat';
          break;
        case RoleEnum.AdministrateurCompte:
          listeRoles = 'Administrateur d\'entreprise';
          break;
      }
    });
    return listeRoles;
  }
}
