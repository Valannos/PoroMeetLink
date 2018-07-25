import { Observable } from 'rxjs';

export interface DataProvider<T> {
  getData(): Observable<T>;
  setData(data: T);
}
