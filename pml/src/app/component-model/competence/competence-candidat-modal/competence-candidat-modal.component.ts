import { Component, OnInit, Input } from '@angular/core';
import { Secteur } from '../../../shared/models/secteur';
import { Competence } from '../../../shared/models/competence';
import { CompetenceCandidat } from '../../../shared/models/competence.candidat';
import { SecteurService } from '../../../shared/services/secteur.service';
import { CompetenceService } from '../../../shared/services/competence.service';
import {
  FormBuilder,
  FormGroup,
  Validators} from '@angular/forms';
import { IModal } from '../../abstract/abstract.modal';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CompetenceCandidatService } from '../../../shared/services/competence.candidat.service';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { NonDuplicatedCompetenceValidator } from '../../../shared/validators/competence.validator';

@Component({
  selector: 'app-competence-candidat-modal',
  templateUrl: './competence-candidat-modal.component.html',
  styleUrls: ['./competence-candidat-modal.component.css']
})
export class CompetenceCandidatModalComponent implements OnInit, IModal {
  constructor(
    private secteurService: SecteurService,
    private competenceService: CompetenceService,
    private comptenceCandidatService: CompetenceCandidatService,
    private builder: FormBuilder,
    private modal: NgbActiveModal
  ) {}

  @Input() utilisateur: Utilisateur;
  public secteurs: Secteur[];
  public competences: Competence[] = [];
  public competenceCandidat: CompetenceCandidat;
  public idEdition: Boolean;
  public formCompCand: FormGroup;
  public valideButtonLabel: string;
  public errorOnSubmit = false;
  public defautSelection = 'Choisissez...';
  public competencesIds: Array<number>;

  ngOnInit() {
    this.initForm();
  }

  public onSelectSecteur(event) {
    this.competenceService
      .getAllBySecteur(event.target.value)
      .subscribe(comp => (this.competences = comp));
  }

  initForm(): void {
    this.secteurService.getAll().subscribe(sec => (this.secteurs = sec));
    this.competenceCandidat = new CompetenceCandidat();
    this.competenceCandidat.idCandidat = this.utilisateur.candidatId;
    this.formCompCand = this.builder.group({
      competence: [
        this.competenceCandidat.competence,
        [
          Validators.required, NonDuplicatedCompetenceValidator(this.competencesIds)
        ]
      ],
      niveau: [this.competenceCandidat.niveau, Validators],
      id: this.competenceCandidat.id
    });

    this.valideButtonLabel = 'Valider';
  }
  onSubmitForm(): void {
    this.competenceCandidat = this.formCompCand.value;
    this.competenceCandidat.idCandidat = this.utilisateur.candidatId;
    this.comptenceCandidatService.post(this.competenceCandidat).subscribe(
      cc => {
        this.modal.close(cc);
      },
      err => {
        this.errorOnSubmit = true;
      }
    );
  }
  close(): void {
    this.modal.close();
  }
}
