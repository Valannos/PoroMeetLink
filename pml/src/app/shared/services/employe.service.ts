import { Injectable } from '@angular/core';
import { BaseService } from './interface.service';
import { Employe } from '../models/employe';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { DataProvider } from './data.provider';
import { AuthenticationService } from './authentication.service';
import { UrlUtils } from './url.service';

@Injectable()
export class EmployeService
  implements BaseService<Employe, number>, DataProvider<Employe> {
  private employe: Employe;

  private url = UrlUtils.getBaseURL() + '/api/employe/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getAllByEntrepriseId(id: Number): Observable<Employe[]> {
    const _url = this.url + 'byEntreprise/' + id;
    return this.http.get<Employe[]>(_url, {
      responseType: 'json'
    });
  }

  getAll(): Observable<Employe[]> {
    return this.http.get<Employe[]>(this.url, {
      responseType: 'json'
    });
  }
  getById(id: number): Observable<Employe> {
    return this.http.get<Employe>(this.url + id, {
      responseType: 'json'
    });
  }
  post(entity: Employe): Observable<Employe> {
    const body: string = JSON.stringify(entity);
    return this.http.post<Employe>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id);
  }
  update(entity: Employe): Observable<Employe> {
    const body: string = JSON.stringify(entity);
    return this.http.put<Employe>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  getData(): Observable<Employe> {
    return new Observable(observer => {
      if (this.employe) {
        observer.next(this.employe);
      } else {
        this.auth.getCurrentUtilisateur().subscribe(user => {
          this.getById(user.employeId).subscribe(empl => {
            this.employe = empl;
            observer.next(empl);
          });
        });
      }
    });
  }
  setData(data: Employe) {
    this.employe = data;
  }
}
