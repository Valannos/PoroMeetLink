import { Injectable, Injector } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpSentEvent,
  HttpHeaderResponse,
  HttpProgressEvent,
  HttpResponse,
  HttpUserEvent,
  HttpErrorResponse,
  HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { TokenStorage } from './token.storage';
import { JwtToken } from '../models/JwtToken';
import { AuthenticationService } from './authentication.service';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private inj: Injector, private router: Router) {
    this.auth = this.inj.get(AuthenticationService);
    this.store = this.inj.get(TokenStorage);
  }
  private isUpdating = false;
  private auth: AuthenticationService;
  private store: TokenStorage;

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
    if (this.store.isLogged()) {
      const token = this.store.getCurrentToken().token;
      req = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
      });
    } else {
      console.log('no token detected');
    }
    return next.handle(req).pipe(
      tap(
        (event: HttpEvent<any>) => {
          if (event instanceof HttpResponse) {
            if (!(req.url === this.auth.refreshUrl)) {
              try {
                const nowPlusOneMinute = moment().add({
                  minute: 2
                });
                if (!this.store.checkJwtExpirationDate(nowPlusOneMinute) && !this.isUpdating) {
                  console.log('refreshing token');
                  this.refreshToken();
                }
              } catch (e) {}
            }
          }
        },
        (err: any) => {
          if (err instanceof HttpErrorResponse) {
            if (err.status === 401) {
              console.error('disconnection due to error', err);
              this.router.navigateByUrl('/');
            }
          }
        }
      )
    );
  }
  private refreshToken() {
    this.isUpdating = true;
    this.auth.refreshToken(this.store.getCurrentToken().token).subscribe((jwt: JwtToken) => {
      console.log('refreshing token OK');
      this.store.replaceToken(jwt);
      this.isUpdating = false;
    });
  }
}
