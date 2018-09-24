import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Utilisateur } from '../models/utilisateur';
import { Observable } from 'rxjs';
import { BaseService } from './interface.service';
import { RoleEnum } from '../models/role';
import { UrlUtils } from './url.service';

@Injectable()
export class UtilisateurService implements BaseService<Utilisateur, number> {
  private utilisateurUrl = UrlUtils.getBaseURL() + '/api/utilisateur';
  private currentUserUrl = this.utilisateurUrl + '/current';
  private suspendUrl = '/suspend/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {}

  getCurrentUtilisateur(): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(this.currentUserUrl, {
      responseType: 'json'
    });
  }

  getById(id: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(this.currentUserUrl + '/' + id, {
      responseType: 'json'
    });
  }
  post(entity: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(this.utilisateurUrl, entity, {
      responseType: 'json'
    });
  }
  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.utilisateurUrl + '/' + id, {});
  }
  update(entity: Utilisateur): Observable<Utilisateur> {
    throw new Error('Method not implemented.');
  }

  /**
   * Effectue une requête GET pour obtenir tous les utilisateurs.
   *
   */
  getAll(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(this.utilisateurUrl, {
      responseType: 'json'
    });
  }
  suspendUtilisateur(id: number): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(
      this.utilisateurUrl + this.suspendUrl + id,
      {}
    );
  }
  public isUserEmploye(user: Utilisateur): Boolean {
    let isPresent = false;
    user.roles.forEach(r => {
      if (
        r.name === RoleEnum.AdministrateurCompte ||
        r.name === RoleEnum.Gerant
      ) {
        isPresent = true;
      }
    });
    return isPresent;
  }
  public isUserCandidat(user: Utilisateur): Boolean {
    let isPresent = false;
    user.roles.forEach(r => {
      if (r.name === RoleEnum.Candidat) {
        isPresent = true;
      }
    });
    return isPresent;
  }

  isGerant(user: Utilisateur): Boolean {
    let isPresent = false;
    user.roles.forEach(r => {
      if (r.name === RoleEnum.Gerant) {
        isPresent = true;
      }
    });
    return isPresent;
  }

  isAdministrateurCompte(user: Utilisateur): Boolean {
    let isPresent = false;
    user.roles.forEach(r => {
      if (r.name === RoleEnum.AdministrateurCompte) {
        isPresent = true;
      }
    });
    return isPresent;
  }

  isAdministrateurSite(user: Utilisateur): Boolean {
    let isPresent = false;
    user.roles.forEach(r => {
      if (r.name === RoleEnum.AdministrateurSite) {
        isPresent = true;
      }
    });
    return isPresent;
  }
}
