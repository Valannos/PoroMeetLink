import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpSentEvent,
  HttpHeaderResponse,
  HttpProgressEvent,
  HttpResponse,
  HttpUserEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable, Injector } from '@angular/core';
import { TokenStorage } from './token.storage';
import { AuthenticationService } from './authentication.service';
import * as moment from 'moment';

@Injectable()
export class RefreshTokenInterceptor implements HttpInterceptor {
  constructor(private store: TokenStorage, private inj: Injector) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<
    | HttpSentEvent
    | HttpHeaderResponse
    | HttpProgressEvent
    | HttpResponse<any>
    | HttpUserEvent<any>
  > {
    console.log('Intercepté par: RefreshTokenInterceptor');
    const auth: AuthenticationService = this.inj.get(AuthenticationService);
    let cloned = req;
    return next.handle(cloned);
  }
}
