export class UrlUtils {

  public static getBaseURL(): string {

    const URL = 'http://127.0.0.1';
    const PORT = '8080';
    const PREFIXE = '/poromeetlink';

    return URL + ':' + PORT + PREFIXE;
  }
}
