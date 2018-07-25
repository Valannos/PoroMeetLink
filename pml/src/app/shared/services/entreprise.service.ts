import { Injectable } from '@angular/core';
import { BaseService } from './interface.service';
import { Entreprise } from '../models/entreprise';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DataProvider } from './data.provider';
import { EmployeService } from './employe.service';

@Injectable()
export class EntrepriseService
  implements BaseService<Entreprise, number>, DataProvider<Entreprise> {
  private entreprise: Entreprise;
  constructor(
    private http: HttpClient,
    private auth: AuthenticationService,
    private employeService: EmployeService
  ) {}

  private url = 'http://localhost:8080/api/entreprise/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getAll(): Observable<Entreprise[]> {
    return this.http.get<Entreprise[]>(this.url, {
      responseType: 'json'
    });
  }
  getById(id: number): Observable<Entreprise> {
    return this.http.get<Entreprise>(this.url + id, {
      responseType: 'json'
    });
  }
  getByUtilisateur(): Observable<Entreprise> {
    return this.http.get<Entreprise>(this.url + 'getByCurrentUtilisateur/', {
      responseType: 'json'
    });
  }
  post(entity: Entreprise): Observable<Entreprise> {
    const body: string = JSON.stringify(entity);
    return this.http.post<Entreprise>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id);
  }
  update(entity: Entreprise): Observable<Entreprise> {
    const body: string = JSON.stringify(entity);
    return this.http.put<Entreprise>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  getData(): Observable<Entreprise> {
    return new Observable(observer => {
      if (this.entreprise) {
        observer.next(this.entreprise);
      } else {
        this.employeService.getData().subscribe(empl =>
          this.getById(empl.entrepriseId).subscribe(ent => {
            this.entreprise = ent;
            observer.next(this.entreprise);
          })
        );
      }
    });
  }
  setData(data: Entreprise) {
    this.entreprise = data;
  }
}
