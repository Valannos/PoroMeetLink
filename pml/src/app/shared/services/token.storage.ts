import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { JwtToken } from '../models/JwtToken';

@Injectable()
export class TokenStorage {
  private token: JwtToken;

  constructor() {
    if (localStorage.getItem('token')) {
      this.token = JSON.parse(localStorage.getItem('token'));
    }
  }

  public saveToken(token: JwtToken) {
    this.token = token;
    localStorage.setItem('token', JSON.stringify(this.token));
  }

  public replaceToken(token: JwtToken) {
    localStorage.removeItem('token');
    this.saveToken(token);
  }

  public clearSession(): boolean {
    this.token = null;
    localStorage.clear();
    return true;
  }

  public getCurrentToken(): JwtToken {
    return this.token;
  }

  /**
   * Vérifie si le token va expirer en retournant true si la date d'expiration
   * @param time permet de spécifier une data limite de comparaison, si null la comparaison se fait par rapport à maintenant.
   */
  public checkJwtExpirationDate(time?: moment.Moment): Boolean {
    const expDate = moment.utc(this.token.expirationDate);


    if (time) {
      return expDate > time;
    } else {
      return expDate > moment();
    }
  }

  public isLogged(): boolean {
    return this.token ? true : false;
  }
}
