import { Injectable } from '@angular/core';
import { BaseService } from './interface.service';
import { Competence } from '../models/competence';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { UrlUtils } from './url.service';

@Injectable()
export class CompetenceService implements BaseService<Competence, Number> {

  private url = UrlUtils.getBaseURL() + '/api/competence/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getAll(): Observable<Competence[]> {
    return this.http.get<Competence[]>(this.url, {
      responseType: 'json'
    });
  }
  getById(id: number): Observable<Competence> {
    return this.http.get<Competence>(this.url + id, {
      responseType: 'json'
    });
  }
  post(entity: Competence): Observable<Competence> {
    const body: string = JSON.stringify(entity);
    return this.http.post<Competence>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id);
  }
  update(entity: Competence): Observable<Competence> {
    const body: string = JSON.stringify(entity);
    return this.http.put<Competence>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  getAllBySecteur(id: Number): Observable<Competence[]> {
    return this.http.get<Competence[]>(this.url + 'bySecteur/' + id, {
      responseType: 'json'
    });
  }
  constructor(private http: HttpClient) { }
}
