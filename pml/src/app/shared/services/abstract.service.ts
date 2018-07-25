
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export abstract class AbstractService<T> {
  constructor(protected http: HttpClient, protected url: string) {}
  protected headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.url, {
      responseType: 'json'
    });
  }

  post(item: T): Observable<T> {
    const body: string = JSON.stringify(item);
    return this.http.post<T>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }

  delete(id: number): Observable<Boolean> {
    return this.http.delete<Boolean>(this.url + id);
  }

  update(entity: T): Observable<T> {
    const body: string = JSON.stringify(entity);
    return this.http.put<T>(this.url, body, {
      responseType: 'json',
      headers: this.headers
    });
  }
  getById(id: number): Observable<T> {
    return this.http.get<T>(this.url + id, {
      responseType: 'json'
    });
  }

}
