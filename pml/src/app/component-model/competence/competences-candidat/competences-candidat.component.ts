import { Component, OnInit, OnDestroy } from '@angular/core';
import { CompetenceCandidat } from '../../../shared/models/competence.candidat';
import { CompetenceCandidatService } from '../../../shared/services/competence.candidat.service';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { UtilisateurService } from '../../../shared/services/utilisateur.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CompetenceCandidatModalComponent } from '../competence-candidat-modal/competence-candidat-modal.component';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-competences-candidat',
  templateUrl: './competences-candidat.component.html',
  styleUrls: ['./competences-candidat.component.css']
})
export class CompetencesCandidatComponent implements OnInit, OnDestroy {

  constructor(
    private serviceCC: CompetenceCandidatService,
    private userService: UtilisateurService,
    private ctrl: NgbModal
  ) {}

  private userSuscription: Subscription;
  refModal: NgbModalRef;
  competencesCandidat: CompetenceCandidat[];
  competenceIds: Array<number>;
  user: Utilisateur;
  isLoading = false;

  ngOnInit() {
    this.competenceIds = new Array();
    this.userSuscription = this.userService.getCurrentUtilisateur().subscribe(user => {
      this.user = user;
      this.isLoading = true;
      this.serviceCC
        .getAllByCandidat(user.candidatId)
        .subscribe(ccs => {
          this.isLoading = false;
          (this.competencesCandidat = ccs);
          ccs.forEach(cc => this.competenceIds.push(cc.competence.id));
        });
    });
  }
  addCompetence() {
    this.refModal = this.ctrl.open(CompetenceCandidatModalComponent, {
      size: 'lg'
    });
    this.refModal.result.then((cc) => {
      if (cc) {
        this.competencesCandidat.push(cc);
      }
    });
    this.refModal.componentInstance.utilisateur = this.user;
    this.refModal.componentInstance.competencesIds = this.competenceIds;
  }

  removeCompetence(event) {
    console.log(event);
    this.competencesCandidat = this.competencesCandidat.filter(cc => event.id !== cc.id);
  }

  ngOnDestroy(): void {
   this.userSuscription.unsubscribe();
  }

}
