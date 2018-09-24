import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';
import { Observable } from 'rxjs';
import { Candidat } from '../models/candidat';
import { DataProvider } from './data.provider';
import { AbstractService } from './abstract.service';
import { UrlUtils } from './url.service';

@Injectable()
export class CandidatService extends AbstractService<Candidat>
  implements DataProvider<Candidat> {
  private candidat: Candidat;
  constructor(protected http: HttpClient, private auth: AuthenticationService, ) {
    super(http, UrlUtils.getBaseURL() + '/api/candidat/');
  }
  /*
  post(entity: Candidat): Observable<Candidat> {
    const body: string = JSON.stringify(entity);
    return this.http.post<Candidat>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id);
  }
  update(entity: Candidat): Observable<Candidat> {
    const body: string = JSON.stringify(entity);
    return this.http.put<Candidat>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  getAll(): Observable<Candidat[]> {
    return this.http.get<Candidat[]>(this.url, {
      responseType: 'json'
    });
  }
  getById(id: number): Observable<Candidat> {
    return this.http.get<Candidat>(this.url + id, {
      responseType: 'json'
    });
  }**/
  getData(): Observable<Candidat> {
    return new Observable(observer => {
      if (this.candidat) {
        observer.next(this.candidat);
      } else {
        this.auth.getCurrentUtilisateur().subscribe(user => {
          this.getById(user.candidatId).subscribe(cdit => {
            this.candidat = cdit;
            observer.next(this.candidat);
          });
        });
      }
    });
  }
  setData(data: Candidat) {
    this.candidat = data;
  }
}
