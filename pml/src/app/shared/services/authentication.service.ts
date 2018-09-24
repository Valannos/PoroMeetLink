import { Injectable } from '@angular/core';
import { Observable} from 'rxjs';
import { HttpClient} from '@angular/common/http';
import { Utilisateur } from '../models/utilisateur';

import { TokenStorage } from './token.storage';
import { RoleEnum } from '../models/role';
import { UtilisateurService } from './utilisateur.service';
import { JwtToken } from '../models/JwtToken';
import { UrlUtils } from './url.service';

@Injectable()
export class AuthenticationService {
  public loginUrl = UrlUtils.getBaseURL() + '/api/login';
  public refreshUrl = UrlUtils.getBaseURL() + '/api/utilisateur/refresh';
  utilisateur: Utilisateur;
  isAdminSite: Boolean = false;
  isCandidat: Boolean = false;
  isEmploye: Boolean = false;
  isGerant: Boolean = false;
  isAdminCompte: Boolean = false;
  constructor(
    private http: HttpClient,
    private storage: TokenStorage,
    private userservice: UtilisateurService
  ) {
    this.utilisateur = new Utilisateur();
  }

  /**
   * Effectue une requête d'authentification et retourne le token obtenue.
   * @param user les identifiants à passer au serveur
   */
  public attemptAuthentication(user: Utilisateur): Observable<JwtToken> {
    const credentials: string = JSON.stringify(user);
    return this.http.post<JwtToken>(this.loginUrl, credentials, {
      responseType: 'json'
    });
  }

  public refreshToken(token: string): Observable<JwtToken> {
    return this.http.post<JwtToken>(this.refreshUrl, token, {
      responseType: 'json'
    });
  }

  /**
   * Retourne l'utilisateur courant sous forme d'Observable.
   * Si ce dernier n'est pas stocké, il sera récupéré depuis UtilisateurService.
   */
  public getCurrentUtilisateur(): Observable<Utilisateur> {
    return new Observable(observer => {
      if (!this.utilisateur.id) {
        this.userservice.getCurrentUtilisateur().subscribe(data => {
          this.setCurrentUtilisateur(data);
          observer.next(data);
        });
      } else {
        observer.next(this.utilisateur);
      }
    });
  }

  public clearSession() {
    this.utilisateur = new Utilisateur();
  }

  public setCurrentUtilisateur(_utilisateur: Utilisateur) {
    this.isAdminCompte = false;
    this.isAdminSite = false;
    this.isCandidat = false;
    this.isEmploye = false;
    this.isGerant = false;
    this.utilisateur = _utilisateur;
    this.setRights();
  }

  setRights() {

    if (this.utilisateur.roles.some(role => role.name === RoleEnum.AdministrateurSite)) {

      this.isAdminSite = true;

    }
    if (this.utilisateur.roles.some(role => role.name === RoleEnum.Candidat)) {

      this.isCandidat = true;

    }
    if (this.utilisateur.roles.some(role => role.name === RoleEnum.Gerant)) {

      this.isGerant = true;
      this.isEmploye = true;

    }
    if (this.utilisateur.roles.some(role => role.name === RoleEnum.AdministrateurCompte)) {

       this.isAdminCompte = true;
       this.isEmploye = true;
    }
    /*
    this.utilisateur.roles.forEach(role => {
      if (role.name === RoleEnum.AdministrateurSite) {
        this.isAdminSite = true;
      } else if (role.name === RoleEnum.Candidat) {
        this.isCandidat = true;
        console.log('CANDIDAT');
      } else {
        if (role.name === RoleEnum.Gerant) {
          this.isGerant = true;
          this.isEmploye = true;
          console.log('GERANT');
        }
        if (role.name === RoleEnum.AdministrateurCompte) {
          this.isAdminCompte = true;
          this.isEmploye = true;
        }
      }
    });*/
  }
}
