import { Observable } from 'rxjs';
export interface BaseService<T, U> {

  getAll(): Observable<T[]>;

  getById(id: U): Observable<T>;

  post(entity: T): Observable<T>;

  delete(id: U): Observable<Boolean>;

  update(entity: T): Observable<T>;

}
