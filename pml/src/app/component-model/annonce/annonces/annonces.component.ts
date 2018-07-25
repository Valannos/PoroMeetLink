import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { Annonce } from '../../../shared/models/annonce';
import { AnnonceService } from '../../../shared/services/annonce.service';
import { Subscription, Subject } from 'rxjs';
import { CandidatService } from '../../../shared/services/candidat.service';
import { EmployeService } from '../../../shared/services/employe.service';
import { Candidat } from '../../../shared/models/candidat';
import { Employe } from '../../../shared/models/employe';
import { NgbModalRef, NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { AnnonceModalComponent } from '../annonce-modal/annonce-modal.component';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { ComptenceAnnonceModalComponent } from '../../competence/competence-annonce/comptence-annonce-modal/comptence-annonce-modal.component';
import { CompetenceAnnonce } from '../../../shared/models/competence.annonce';
import { EventEmitter } from 'events';
import { PropositionCandidature, PropositionCandidatureId } from '../../../shared/models/proposition.candidature';
import { PropositionCandidatureService } from '../../../shared/services/proposition-candidature.service';
import { PropositionModalComponent } from '../../proposition/proposition-modal/proposition-modal.component';
import { debounceTime } from 'rxjs/operators';

@Component({
  selector: 'app-annonces',
  templateUrl: './annonces.component.html',
  styleUrls: ['./annonces.component.css']
})
export class AnnoncesComponent implements OnInit, OnDestroy {


  constructor(private auth: AuthenticationService,
    private employeService: EmployeService,
    private candidatService: CandidatService,
    private annonceService: AnnonceService,
    private propositionService: PropositionCandidatureService,
    private modale: NgbModal) { }

  public user: Utilisateur;
  public candidat: Candidat;
  public employe: Employe;
  public annonceEmployeSuscription: Subscription;
  annonces: Array<Annonce> = [];
  ref: NgbModalRef;
  public utilisateurSuscription: Subscription;
  public candidatSuscription: Subscription;
  public employeSuscription: Subscription;
  public undefinedEntreprise = true;
  public isLoading = false;
  public proposition: PropositionCandidature;
  public successMessage: string;
  private _success = new Subject<string>();

  ngOnInit() {

    this._success.subscribe((message) => this.successMessage = message);
    this._success.pipe(
      debounceTime(5000)
    ).subscribe(() => this.successMessage = null);

    this.utilisateurSuscription = this.auth.getCurrentUtilisateur().subscribe((user) => {
      this.user = user;
      if (this.isCandidat()) {
        this.isLoading = true;
        this.candidatSuscription = this.candidatService
          .getData()
          .subscribe((candidat) => {
            this.candidat = candidat;
            this.annonceService.getAllValide().subscribe((annonces) => {
              this.annonces = annonces;
              this.isLoading = false;
            });
          });
      } else if (this.isEmploye()) {
        if (this.user.employeId) {
          this.undefinedEntreprise = false;
          this.employeSuscription = this.employeService.getData().subscribe((employe) => {
            this.employe = employe;
            this.annonceEmployeSuscription = this.annonceService
              .getAllByEmploye(this.employe)
              .subscribe((annonces) => {
                this.annonces = annonces;
              });
          });
        }
      }
    });
  }

  isEmploye(): Boolean {
    return this.auth.isEmploye;
  }

  isCandidat(): Boolean {
    return this.auth.isCandidat;
  }

  ngOnDestroy(): void {

    if (this.employeSuscription) {
      this.employeSuscription.unsubscribe();
    }
    if (this.candidatSuscription) {
      this.candidatSuscription.unsubscribe();
    }
    if (this.annonceEmployeSuscription) {
      this.annonceEmployeSuscription.unsubscribe();
    }
  }
  addAnnonce() {
    this.ref = this.modale.open(AnnonceModalComponent, {
      centered: true,
      size: 'lg'
    });
    this.ref.componentInstance.isEdition = false;
    this.ref.componentInstance.employe = this.employe;
    this.ref.result.then((res: Annonce) => {
      if (res) {
        this.annonces.push(res);
      }
    });
  }

  unvalid(annonce: Annonce) {
    this.annonceService.toggle(annonce).subscribe(res => {
      const index: number = this.annonces.findIndex((ann) => ann.id === res.id);
      this.annonces[index] = res;
    });
  }

  valideAndPublish(annonce: Annonce) {
    if (annonce.competenceAnnonce.length === 0) {
      this.ref = this.modale.open(WarningAnnonceModalComponent, {
        backdrop: 'static',
        centered: true
      });
      this.ref.componentInstance.message = 'Cette annonce ne contient aucune compétence, voulez-vous la valider ?';
      this.ref.componentInstance.showCancelBtn = true;
      this.ref.result.then((value) => {
        if (value) {
          this.annonceService.toggle(annonce).subscribe(res => {
            const index: number = this.annonces.findIndex((ann) => ann.id === res.id);
            this.annonces[index] = res;
          });
        }
      });
    } else {
      this.annonceService.toggle(annonce).subscribe(res => {
        const index: number = this.annonces.findIndex((ann) => ann.id === res.id);
        this.annonces[index] = res;
      });
    }
  }

  delete(annonce: Annonce) {
    this.annonceService.delete(annonce.id).subscribe(() => {
      this.annonces = this.annonces.filter((value) => value.id !== annonce.id);
    }, (err) => console.log(err));
  }

  addCompetenceAnnonce(annonce: Annonce) {
    this.ref = this.modale.open(ComptenceAnnonceModalComponent, {
      backdrop: 'static',
      centered: true
    });
    this.ref.componentInstance.isEdition = false;
    this.ref.componentInstance.annonce = annonce;
    this.ref.result.then((res) => {
      if (res) {
        this.annonces.find(x => x.id === annonce.id).competenceAnnonce.push(res);
      }
    });
  }

  handleDeletedCompetenceAnnonce(event: CompetenceAnnonce) {
    const annonce = this.annonces.find(x => x.id === event.idAnnonce);
    annonce.competenceAnnonce = annonce.competenceAnnonce.filter(x => x.id !== event.id);

  }

  edit(annonce: Annonce) {

    this.ref = this.modale.open(AnnonceModalComponent, {
      size: 'lg',
      backdrop: 'static'
    });
    this.ref.componentInstance.isEdition = true;
    this.ref.componentInstance.annonce = annonce;
    this.ref.componentInstance.employe = this.employe;
    this.ref.result.then((res) => {
      if (res) {
        const index = this.annonces.findIndex((ann) => ann.id === annonce.id);
        this.annonces[index] = res;
      }
    });
  }

  postuler(annonce: Annonce) {

    const id: PropositionCandidatureId = new PropositionCandidatureId(this.candidat.id, annonce.id);

    this.propositionService.getById(id).subscribe((res) => {
        if (res) {

          this.ref = this.modale.open(WarningAnnonceModalComponent, {
            backdrop: 'static',
            centered: true
          });
          this.ref.componentInstance.message = 'Vous avez déjà postulé à cette annonce';
          this.ref.componentInstance.showCancelBtn = false;

        } else {
          this.ref = this.modale.open(PropositionModalComponent, {
            backdrop: 'static',
            centered: true
          });
          this.ref.componentInstance.candidat = this.candidat;
          this.ref.componentInstance.annonce = annonce;
          this.ref.componentInstance.isCandidatAuteur = true;
          this.ref.result.then((prop) => {
            if (prop) {
              this._success.next(`Vous avez postulé à l'offre ${annonce.intitule}`);
            }
          });
        }
    });
  }


}

@Component({
  selector: 'app-warning-annonce-modale',
  template: `
  <div class="modal-header">
    <h4 class="modal-title">Avertissement</h4>
  </div>
  <div class="modal-body">
    <p>{{message}}</p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-dark" (click)="confirmation(true)">OK</button>
    <button *ngIf="showCancelBtn" type="button" class="btn btn-outline-dark" (click)="confirmation(false)">Annuler</button>
  </div>
`
})
export class WarningAnnonceModalComponent {

  @Input()
  message: string;
  @Input()
  showCancelBtn: boolean;

  constructor(public activeModal: NgbActiveModal) { }
  confirmation(response: boolean) {
    this.activeModal.close(response);
  }
}
