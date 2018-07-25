import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BaseService } from './interface.service';
import { CompetenceAnnonce, CompetenceAnnonceId } from '../models/competence.annonce';
import { Observable } from 'rxjs';

@Injectable()
export class CompetenceAnnonceService implements BaseService<CompetenceAnnonce, CompetenceAnnonceId> {
    constructor(private httpClient: HttpClient) { }

    private url = 'http://localhost:8080/api/competence_annonce/';
    private headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    getAll(): Observable<CompetenceAnnonce[]> {
      return this.httpClient.get<CompetenceAnnonce[]>(this.url, {
        responseType: 'json'
      });
    }
    getById(id: CompetenceAnnonceId): Observable<CompetenceAnnonce> {
      return this.httpClient.get<CompetenceAnnonce>(this.url + id.idAnnonce + '/' + id.idCompetence, {
        responseType: 'json'
      });
    }
    post(entity: CompetenceAnnonce): Observable<CompetenceAnnonce> {
      const body: string = JSON.stringify(entity);
      return this.httpClient.post<CompetenceAnnonce>(this.url, body, {
        responseType: 'json',
        headers: this.headers
      });
    }
    delete(id: CompetenceAnnonceId): Observable<Boolean> {
      return this.httpClient.delete<Boolean>(this.url + id.idAnnonce + '/' + id.idCompetence);
    }
    update(entity: CompetenceAnnonce): Observable<CompetenceAnnonce> {
      const body: string = JSON.stringify(entity);
      return this.httpClient.put<CompetenceAnnonce>(this.url, body, {
        responseType: 'json',
        headers: this.headers
      });
    }

}
