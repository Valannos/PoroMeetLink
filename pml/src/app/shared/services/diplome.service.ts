import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { Diplome } from '../models/diplome';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UrlUtils } from './url.service';

@Injectable()
export class DiplomeService extends AbstractService<Diplome> {
  constructor(protected http: HttpClient) {
    super(http, UrlUtils.getBaseURL() + '/api/diplome/');
  }

  public getAllByCandidat(id: number): Observable<Diplome[]> {
    return this.http.get<Diplome[]>(this.url + 'candidat/' + id, {
      headers: this.headers,
      responseType: 'json'
    });
  }
}
