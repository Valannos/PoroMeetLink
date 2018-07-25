import { Component, OnInit, Input } from '@angular/core';
import { IModal } from '../../abstract/abstract.modal';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompetenceService } from '../../../shared/services/competence.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Competence } from '../../../shared/models/competence';
import { Secteur } from '../../../shared/models/secteur';
import { SecteurService } from '../../../shared/services/secteur.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-competence-modal',
  templateUrl: './competence-modal.component.html',
  styleUrls: ['./competence-modal.component.css']
})
export class CompetenceModalComponent implements OnInit, IModal {
  constructor(
    private builder: FormBuilder,
    private componentService: CompetenceService,
    private secteurService: SecteurService,
    private activeModal: NgbActiveModal,
    private route: Router
  ) {}

  public formCompetence: FormGroup;
  public valideButtonLabel: string;
  public isEdition: Boolean = false;
  public errorOnSubmit: Boolean = false;
  @Input() compepence: Competence;
  @Input() secteurs: Secteur[];

  initForm(): void {
    this.formCompetence = this.builder.group({
      id: [this.compepence.id],
      intitule: [this.compepence.intitule, Validators.required],
      secteur: [this.compepence.secteur, Validators.required]
    });
    this.formCompetence.valueChanges.subscribe(() => {
      this.compepence = this.formCompetence.value;
    });
  }
  onSubmitForm(): void {
    if (this.isEdition) {
      this.componentService.update(this.compepence).subscribe(data => {
        this.activeModal.close(data);
      });
    } else {
      console.log(this.formCompetence.value);
      this.componentService.post(this.formCompetence.value).subscribe(compt => {
        compt.isDeletable = true;
        this.activeModal.close(compt);
      });
    }
  }
  close(): void {
    this.activeModal.close();
  }

  ngOnInit() {
    if (!this.secteurs) {
      this.secteurService.getAll().subscribe(sec => this.secteurs = sec, err => this.errorOnSubmit = true);
    }
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
    } else {
      this.compepence = new Competence();
      this.valideButtonLabel = 'Valider';
    }
    this.initForm();
  }

  onChange(event) {
    console.log(event.target);
  }
}
