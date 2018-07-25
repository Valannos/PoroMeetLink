import { Injectable } from '@angular/core';
import { BaseService } from './interface.service';
import { PropositionCandidature, PropositionCandidatureId } from '../models/proposition.candidature';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PropositionCandidatureService implements BaseService<PropositionCandidature, PropositionCandidatureId> {

  constructor(private httpClient: HttpClient) { }

    private url = 'http://localhost:8080/api/proposition_candidature/';
    private headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    getAll(): Observable<PropositionCandidature[]> {
      return this.httpClient.get<PropositionCandidature[]>(this.url, {
        responseType: 'json'
      });
    }
    getById(id: PropositionCandidatureId): Observable<PropositionCandidature> {
      return this.httpClient.get<PropositionCandidature>(this.url + id.idCandidat + '/' + id.idAnnonce, {
        responseType: 'json'
      });
    }
    post(entity: PropositionCandidature): Observable<PropositionCandidature> {
      const body: string = JSON.stringify(entity);
      return this.httpClient.post<PropositionCandidature>(this.url, body, {
        responseType: 'json',
        headers: this.headers
      });
    }
    delete(id: PropositionCandidatureId): Observable<Boolean> {
      return this.httpClient.delete<Boolean>(this.url + id.idCandidat + '/' + id.idAnnonce);
    }
    update(entity: PropositionCandidature): Observable<PropositionCandidature> {
      const body: string = JSON.stringify(entity);
      return this.httpClient.put<PropositionCandidature>(this.url, body, {
        responseType: 'json',
        headers: this.headers
      });
    }
}
