import { Injectable } from '@angular/core';
import { AbstractService } from './abstract.service';
import { TypeDiplome } from '../models/type.diplome';
import { HttpClient } from '@angular/common/http';
import { UrlUtils } from './url.service';

@Injectable()
export class TypeDiplomeService extends AbstractService<TypeDiplome> {

constructor(protected http: HttpClient) {
  super(http, UrlUtils.getBaseURL() + '/api/type_diplome');
}

}
