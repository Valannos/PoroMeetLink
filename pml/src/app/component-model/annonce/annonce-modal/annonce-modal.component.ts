import { Component, OnInit, Input } from '@angular/core';
import { NgbActiveModal, NgbDatepickerI18n, NgbDateStruct, NgbDatepickerConfig } from '@ng-bootstrap/ng-bootstrap';
import { Annonce, TypeContrat } from '../../../shared/models/annonce';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Employe } from '../../../shared/models/employe';
import { I18nService } from '../../../shared/services/i18n.service';
import { I18nDatePickerService } from '../../../shared/services/i18n-date-picker.service';
import { ValidateTypeContrat } from '../../../shared/validators/type-contrat.validator';
import { AnnonceService } from '../../../shared/services/annonce.service';
import * as moment from 'moment';

@Component({
  selector: 'app-annonce-modal',
  templateUrl: './annonce-modal.component.html',
  styleUrls: ['./annonce-modal.component.css'],
  providers: [I18nService, NgbDatepickerConfig]
})
export class AnnonceModalComponent implements OnInit {


  constructor(private active: NgbActiveModal,
              private builder: FormBuilder,
              private annonceService: AnnonceService,
              private config: NgbDatepickerConfig) {

   }
  @Input()
  annonce: Annonce;
  formAnnonce: FormGroup;
  typesContrat = TypeContrat;
  @Input()
  public employe: Employe;
  @Input()
  public isEdition: Boolean;
  public confirmButton: string;
  public showError = false;
  public dateDebut: Date;
  public dateFin: Date;


  ngOnInit() {

    this.config.minDate = {year: 1900, month: 1, day: 1};
    this.config.maxDate = {year: 2030, month: 12, day: 31};
    this.config.outsideDays = 'hidden';
    this.confirmButton = this.isEdition ? 'Mettre à jour' : 'Confirmer';
    if (!this.isEdition) {
      this.annonce = new Annonce();
    } else {
      this.dateDebut = moment(this.annonce.dateDebut).toDate();
      this.dateFin = moment(this.annonce.dateFin).toDate();
    }
    this.initForm();

  }

  initForm(): void {
    this.formAnnonce = this.builder.group({
      intitule: [this.annonce.intitule, [Validators.required]],
      dateDebut: [this.dateDebut, [Validators.required]],
      niveauExperience: this.annonce.niveauExperience,
      dateFin: this.dateFin,
      description: this.annonce.description,
      typeContrat: [this.annonce.typeContrat, [ValidateTypeContrat(), Validators.required]],
      employeId: this.employe.id,
      id: this.annonce.id
    });
    this.formAnnonce.valueChanges.subscribe(() => {
      this.annonce = this.formAnnonce.value;
      this.annonce.dateDebut = moment.utc(this.formAnnonce.get('dateDebut').value).toISOString();
      this.annonce.dateFin = moment.utc(this.formAnnonce.get('dateFin').value).toISOString();
    });

    if (this.isEdition) {
      if (this.annonce.typeContrat === TypeContrat.CDI) {
        this.formAnnonce.get('dateFin').disable();
        this.formAnnonce.get('dateFin').setValue(null);
      } else {
        this.formAnnonce.get('dateFin').enable();
      }
    }
  }

  checkValue(event) {
    let controlerDateFin = this.formAnnonce.get('dateFin');
    if (this.annonce.typeContrat === TypeContrat.CDI) {
      this.formAnnonce.get('dateFin').setValue(null);
      controlerDateFin.disable();
    } else {
      controlerDateFin.enable();
    }
  }

  onSubmitForm() {

    if (this.isEdition) {
      this.annonceService.update(this.annonce).subscribe((value) => {
        this.active.close(value);
      }, (err) => this.showError = true);
    } else {
      this.annonceService.post(this.annonce).subscribe((value) => {
        this.active.close(value);
      }, (err) => this.showError = true);
    }
  }

  close(): void {
    this.active.close();
  }
}
