import { Component, OnInit} from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Utilisateur } from '../shared/models/utilisateur';
import { Router} from '@angular/router';
import { TokenStorage } from '../shared/services/token.storage';
import { UtilisateurService } from '../shared/services/utilisateur.service';

/**
 *
 *
 * @export
 * @class ConnexionComponent
 * @implements {OnInit}
 */
@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {
  constructor(
    private auth: AuthenticationService,
    private router: Router,
    private storage: TokenStorage  ) {}

  user: Utilisateur;
  isAlertVisible: Boolean = false;

  ngOnInit() {

    this.storage.clearSession();
    this.auth.clearSession();
    console.log('session cleared');
    this.user = new Utilisateur();
  }

  /**
   * Effectue une requête de token d'authentification à partir des credentials fournis.
   * @param user objet à transférer au service pour effectuer la requête.
   */
  onValide(): void {
    this.storage.clearSession();
    let authOk: Boolean = true;
    this.auth.attemptAuthentication(this.user).subscribe(
      data => {
        this.storage.saveToken(data);
        this.router.navigate(['home']);
      },
      error => {
        authOk = false;
        this.isAlertVisible = true;
      }
    );
  }
}
