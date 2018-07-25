import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../shared/services/authentication.service';
import { AbstractComponent } from '../abstract/abstract.component';
import { Secteur } from '../../shared/models/secteur';
import { Competence } from '../../shared/models/competence';
import { SecteurService } from '../../shared/services/secteur.service';
import { CompetenceService } from '../../shared/services/competence.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CompetenceModalComponent } from './competence-modal/competence-modal.component';
import { CompetenceCandidatService } from '../../shared/services/competence.candidat.service';

@Component({
  selector: 'app-competence',
  templateUrl: './competence.component.html',
  styleUrls: ['./competence.component.css']
})
export class CompetenceComponent implements OnInit, AbstractComponent {
  constructor(
    private auth: AuthenticationService,
    private secteurService: SecteurService,
    private competenceService: CompetenceService,
    private modalService: NgbModal,
    private ccService: CompetenceCandidatService
  ) {}
  public defautSelection = 'Choisissez...';
  secteurs: Secteur[];
  competences: Competence[] = [];
  refModale: NgbModalRef;

  isAdminSite(): Boolean {
    return this.auth.isAdminSite;
  }
  isCandidat(): Boolean {
    return this.auth.isCandidat;
  }

  ngOnInit() {
    this.secteurService.getAll().subscribe(sec => (this.secteurs = sec));
  }

  onChange(event) {
    this.competences = [];
    if (event.target.value !== this.defautSelection) {
      this.competenceService
        .getAllBySecteur(event.target.value)
        .subscribe(comps => {
          this.competences = comps;
          this.competences.forEach(comp => {
            this.ccService.existsByCompetenceId(comp.id).subscribe(res => {
              comp.isDeletable = !res;
            });
          });
        });
    }
  }

  addCompetence() {
    this.refModale = this.modalService.open(CompetenceModalComponent);
    this.refModale.componentInstance.isEdition = false;
    this.refModale.componentInstance.secteurs = this.secteurs;
    this.refModale.result.then(res => {
      if (res) {
        this.competences.push(res);
      }
    });
  }

  onEdit(comp: Competence) {
    this.refModale = this.modalService.open(CompetenceModalComponent, {
      size: 'lg'
    });
    this.refModale.componentInstance.isEdition = true;
    this.refModale.componentInstance.secteurs = this.secteurs;
    this.refModale.componentInstance.compepence = comp;
    this.refModale.result
      .then(data => {
        if (data) {
          const index: number = this.competences.findIndex(
            competence => competence.id === data.id
          );
          this.competences[index] = data;
        }
      })
      .catch(err => console.log(err));
  }
  onDelete(id: number) {
    this.competenceService.delete(id).subscribe(data => {
      if (data) {
        this.competences = this.competences.filter(
          competence => competence.id !== id
        );
      }
    });
  }
}
