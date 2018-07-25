import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Secteur } from '../models/secteur';
import {AuthenticationService } from './authentication.service';
import { BaseService } from './interface.service';

@Injectable()
export class SecteurService implements BaseService<Secteur, number> {

  private secteurUrl = 'http://127.0.0.1:8080/api/secteur/';
  private headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient, private auth: AuthenticationService) { }


/**
 * Effectue une requête GET pour obtenir tous les secteurs.
 */
getAll(): Observable<Secteur[]> {
  return this.http.get<Secteur[]>(this.secteurUrl, {
    responseType: 'json',
  });
}
/**
 * Effectue une requête GET pour obtenir un secteur selon son identifiant.
 * @param id identifiant du secteur recherché.
 */
getById(id: number): Observable<Secteur> {
  return this.http.get<Secteur>(this.secteurUrl + id.toString, {
    responseType: 'json'
  });
}

delete(id: number): Observable<Boolean> {
  return this.http.delete<Boolean>(this.secteurUrl + id);
}

update(secteur: Secteur): Observable<Secteur> {

  const body: string = JSON.stringify(secteur);
  return this.http.put<Secteur>(this.secteurUrl, body, {
    responseType: 'json',
    headers: this.headers,
  });
}

post(secteur: Secteur): Observable<Secteur> {

  const body: string = JSON.stringify(secteur);
  return this.http.post<Secteur>(this.secteurUrl, body, {
    responseType: 'json',
    headers: this.headers

  });
}


}
