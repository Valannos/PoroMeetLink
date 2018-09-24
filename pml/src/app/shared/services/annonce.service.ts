import { Injectable } from '@angular/core';
import { Annonce } from '../models/annonce';
import { BaseService } from './interface.service';
import { Observable } from 'rxjs';
import { AbstractService } from './abstract.service';
import { HttpClient } from '@angular/common/http';
import { Employe } from '../models/employe';
import { UrlUtils } from './url.service';

@Injectable()
export class AnnonceService extends AbstractService<Annonce> {
  constructor(protected http: HttpClient) {
    super(http, UrlUtils.getBaseURL() + '/api/annonce/');
  }

  getAllByEmploye(employe: Employe): Observable<Annonce[]> {
    return this.http.get<Annonce[]>(this.url + 'by-entreprise/' + employe.entrepriseId, {
      headers: this.headers
    });
  }

  getAllValide(): Observable<Annonce[]> {
    return this.http.get<Annonce[]>(this.url + 'by-entreprise/valide', {
      headers: this.headers
    });
  }

  toggle(annonce: Annonce): Observable<Annonce> {
  return  this.http.patch<Annonce>(this.url + 'toggle/' + annonce.id, {
      headers: this.headers
    });
  }
}
