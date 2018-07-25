import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../shared/services/authentication.service';
import { Router } from '@angular/router';
import { TokenStorage } from '../shared/services/token.storage';
import { UtilisateurService } from '../shared/services/utilisateur.service';
import { Utilisateur } from '../shared/models/utilisateur';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { CandidatModalComponent } from '../component-model/candidat/candidat-modale/candidat-modale.component';
import { Candidat } from '../shared/models/candidat';
@Component({
  selector: 'app-poro-nav',
  templateUrl: './poro-nav.component.html',
  styleUrls: ['./poro-nav.component.css']
})
export class PoroNavComponent implements OnInit {
  utilisateur: Utilisateur;
  constructor(
    private auth: AuthenticationService,
    private router: Router,
    private modalService: NgbModal
  ) {}
  refModale: NgbModalRef;
  openModale() {
    this.refModale = this.modalService.open(CandidatModalComponent, {
      size: 'lg'
    });
    this.refModale.componentInstance.user = this.utilisateur;
    this.refModale.componentInstance.isEdition = false;
    this.refModale.result.then((data?: Candidat) => {
      if (data) {
        this.utilisateur.candidatId = data.id;
        this.auth.setCurrentUtilisateur(this.utilisateur);
      }
    });
  }

  ngOnInit() {
    this.auth
      .getCurrentUtilisateur()
      .subscribe(user => (this.utilisateur = user));
  }

  gettingDeconnected(): void {
    this.router.navigate(['']);
  }

  isAdministrateurSite(): Boolean {
    return this.auth.isAdminSite;
  }
  isCandidat(): Boolean {
    return this.auth.isCandidat;
  }

  isAdministrateurCompteEntreprise(): Boolean {
    return this.auth.isAdminCompte;
  }

  isGerant(): Boolean {
    return this.auth.isGerant;
  }
}
