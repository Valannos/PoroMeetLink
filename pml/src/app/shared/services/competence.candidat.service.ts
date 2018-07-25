import { Injectable } from '@angular/core';
import { BaseService } from './interface.service';
import { Competence } from '../models/competence';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CompetenceCandidat } from '../models/competence.candidat';
import { DataProvider } from './data.provider';
import { CompetenceCandidatId } from '../models/competence.candidat.id';

@Injectable()
export class CompetenceCandidatService implements BaseService<CompetenceCandidat, CompetenceCandidatId> {

  private url = 'http://localhost:8080/api/competence_candidat/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getAll(): Observable<CompetenceCandidat[]> {
    return this.http.get<CompetenceCandidat[]>(this.url, {
      responseType: 'json'
    });
  }
  getById(id: CompetenceCandidatId): Observable<CompetenceCandidat> {
    return this.http.get<CompetenceCandidat>(this.url + id.idCandidat + '/' + id.idCompetence, {
      responseType: 'json'
    });
  }
  post(entity: CompetenceCandidat): Observable<CompetenceCandidat> {
    const body: string = JSON.stringify(entity);
    return this.http.post<CompetenceCandidat>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  delete(id: CompetenceCandidatId): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id.idCandidat + '/' + id.idCompetence);
  }
  update(entity: CompetenceCandidat): Observable<CompetenceCandidat> {
    const body: string = JSON.stringify(entity);
    return this.http.put<CompetenceCandidat>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }

  getAllByCandidat(candidatId: number) {
    return this.http.get<CompetenceCandidat[]>(this.url + 'candidat/' + candidatId, {
      headers: this.headers,
      responseType: 'json'
    });
  }
existsByCompetenceId(competenceId: number) {
  return this.http.get<Boolean>(this.url + 'competence/exists/' + competenceId, {
    headers: this.headers
  });
}

  constructor(private http: HttpClient) { }
}
