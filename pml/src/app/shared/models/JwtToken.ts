export class JwtToken {
  token: string;
  expirationDate: number;

  constructor(token: string, expirationDate: number) {
    this.token = token;
    this.expirationDate = expirationDate;
  }
}
