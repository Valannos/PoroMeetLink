import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { TypeDiplome } from '../models/type.diplome';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class TypeDiplomeService extends AbstractService<TypeDiplome> {

constructor(protected http: HttpClient) {
  super(http, 'http://localhost:8080/api/type_diplome');
}

}
