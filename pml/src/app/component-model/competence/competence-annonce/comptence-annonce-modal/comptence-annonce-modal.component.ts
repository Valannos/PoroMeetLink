import { Component, OnInit } from '@angular/core';
import { SecteurService } from '../../../../shared/services/secteur.service';
import { CompetenceService } from '../../../../shared/services/competence.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Secteur } from '../../../../shared/models/secteur';
import { Competence } from '../../../../shared/models/competence';
import { CompetenceAnnonce } from '../../../../shared/models/competence.annonce';
import { Annonce } from '../../../../shared/models/annonce';
import { NonDuplicatedCompetenceValidator } from '../../../../shared/validators/competence.validator';
import { CompetenceAnnonceService } from '../../../../shared/services/competence.annonce.service';

@Component({
  selector: 'app-comptence-annonce-modal',
  templateUrl: './comptence-annonce-modal.component.html',
  styleUrls: ['./comptence-annonce-modal.component.css']
})
export class ComptenceAnnonceModalComponent implements OnInit {

  constructor(
    private secteurService: SecteurService,
    private competenceService: CompetenceService,
    private comptenceAnnonceService: CompetenceAnnonceService,
    private builder: FormBuilder,
    private modal: NgbActiveModal) { }

    public annonce: Annonce;
    public secteurs: Secteur[];
    public competences: Competence[] = [];
    public competenceAnnonce: CompetenceAnnonce;
    public idEdition: Boolean;
    public formAnnonce: FormGroup;
    public valideButtonLabel: string;
    public errorOnSubmit = false;
    public defautSelection = 'Choisissez...';
    public competencesIds: Array<number> = new Array<number>();

  ngOnInit() {
    this.initForm();
    if (this.annonce.competenceAnnonce) {
      this.competencesIds = this.annonce.competenceAnnonce.map((value) => value.competence.id);
    }
  }

  close(): void {
    this.modal.close();
  }

  public onSelectSecteur(event) {

    this.competenceService
      .getAllBySecteur(event.target.value)
      .subscribe(comp => (this.competences = comp));
  }

  initForm(): void {
    this.secteurService.getAll().subscribe(sec => (this.secteurs = sec));
    this.competenceAnnonce = new CompetenceAnnonce();

    this.formAnnonce = this.builder.group({
      competence: [
        this.competenceAnnonce.competence,
        [
          Validators.required, NonDuplicatedCompetenceValidator(this.competencesIds)
        ]
      ],
      niveauRequis: [this.competenceAnnonce.niveauRequis, Validators.required],
      idAnnonce: this.annonce.id,
      id: this.competenceAnnonce.id
    });

    this.valideButtonLabel = 'Valider';
  }

  onSubmitForm(): void {
    this.competenceAnnonce = this.formAnnonce.value;
    this.comptenceAnnonceService.post(this.competenceAnnonce).subscribe(
      ca => {
        this.modal.close(ca);
      },
      (err) => {
        this.errorOnSubmit = true;
        console.log(err);
      }
    );
  }

}
