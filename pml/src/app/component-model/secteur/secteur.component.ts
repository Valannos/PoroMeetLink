import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { SecteurService } from '../../shared/services/secteur.service';
import { Secteur } from '../../shared/models/secteur';
import { AuthenticationService } from '../../shared/services/authentication.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { SecteurModalComponent } from './secteur-modal/secteur-modal.component';
import { Utilisateur } from '../../shared/models/utilisateur';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-secteur',
  templateUrl: './secteur.component.html',
  styleUrls: ['./secteur.component.css']
})
export class SecteurComponent implements OnInit, OnDestroy {

  getSecteurs: Subscription;
  secteurs: Secteur[];
  @Input() utilisateur: Utilisateur;
  private modalRef: NgbModalRef;

  constructor(
    private modalService: NgbModal,
    private secteurService: SecteurService,
    private auth: AuthenticationService  ) {}

  ngOnInit() {
    this.getSecteurs = this.auth.getCurrentUtilisateur().subscribe(
      user => {
        this.utilisateur = user;
        this.loadSecteurs();
      },
      err => {
        console.log('erreur lors de la récupération', err);
      }
    );
  }

  onEdit(secteur: Secteur) {
    this.modalRef = this.modalService.open(SecteurModalComponent, {
      size: 'lg'
    });
    this.modalRef.componentInstance.secteur = secteur;
    this.modalRef.componentInstance.isEdition = true;
    this.modalRef.result.then(data => {
      const index: number = this.secteurs.findIndex(sec => sec.id === data.id);
      this.secteurs[index] = data;
    });
  }
  openLg() {
    this.modalRef = this.modalService.open(SecteurModalComponent, {
      size: 'lg'
    });
    this.modalRef.componentInstance.isEdition = false;
    this.modalRef.result
      .then(sec => {
        this.secteurs.push(sec);
      })
      .catch(err => console.log(err));
  }

  isAdministrateurSite(): Boolean {
    return this.auth.isAdminSite;
  }

  loadSecteurs(): void {
    this.secteurService.getAll().subscribe(
      data => {
        this.secteurs = data;
      },
      err => {
        console.error(err);
      }
    );
  }
  deleteSecteur(id: number): void {
    this.secteurService.delete(id).subscribe(
      () => {
        const removed: Secteur = this.secteurs.find(sec => sec.id === id);
        this.secteurs.splice(this.secteurs.indexOf(removed, 0), 1);
      },
      err => {
        console.error(err);
      }
    );
  }

  ngOnDestroy(): void {
    this.getSecteurs.unsubscribe();
  }
}
