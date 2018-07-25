import { Component, OnInit, Input } from '@angular/core';
import {
  NgbActiveModal,
  NgbDatepickerI18n,
  NgbDateStruct,
  NgbDatepickerConfig
} from '@ng-bootstrap/ng-bootstrap';
import { Utilisateur } from '../../../shared/models/utilisateur';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl
} from '@angular/forms';
import { I18nService } from '../../../shared/services/i18n.service';
import { I18nDatePickerService } from '../../../shared/services/i18n-date-picker.service';
import * as moment from 'moment';
import { Candidat } from '../../../shared/models/candidat';
import { CandidatService } from '../../../shared/services/candidat.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../../shared/services/authentication.service';

@Component({
  selector: 'app-candidat-modal',
  templateUrl: './candidat-modale.component.html',
  styleUrls: ['./candidat-modale.component.css'],
  providers: [
    I18nService, NgbDatepickerConfig]
})
export class CandidatModalComponent implements OnInit {
  constructor(
    private activeModal: NgbActiveModal,
    private builder: FormBuilder,
    private candidatService: CandidatService,
    private config: NgbDatepickerConfig,
    private auth: AuthenticationService,
    private router: Router
  ) {}
  @Input() user: Utilisateur;
  @Input() candidat: Candidat;
  public formCandidat: FormGroup;
  valideButtonLabel: string;
  public dateNaissance: Date;
  public showError: Boolean = false;
  public isEdition: Boolean = false;

  ngOnInit() {
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
      this.dateNaissance = moment(this.candidat.dateNaissance).toDate();
    } else {
      this.candidat = new Candidat();
      this.candidat.utilisateurId = this.user.id;
      this.valideButtonLabel = 'Valider';

    }
    this.config.minDate = {year: 1900, month: 1, day: 1};
    this.config.maxDate = {year: 2030, month: 12, day: 31};
    this.config.outsideDays = 'hidden';
    this.initForm();
  }

  private initForm() {
    this.formCandidat = this.builder.group({
      nom: [this.candidat.nom, Validators.required],
      prenom: [this.candidat.prenom, Validators.required],
      dateNaissance: [this.dateNaissance, Validators.required],
      id: this.candidat.id,
      ville: [this.candidat.ville, Validators.maxLength(255)],
      utilisateurId: new FormControl(
        this.candidat.utilisateurId,
        Validators.required
      ),
      sexe: this.candidat.sexe,
      urlAvatar: this.candidat.urlAvatar
    });
    this.formCandidat.valueChanges.subscribe(() => {
        this.candidat.dateNaissance = moment.utc(this.formCandidat.get('dateNaissance').value).toISOString();
        this.candidat = this.formCandidat.value;
    });
  }
  onSubmitForm(): void {

    if (!this.isEdition) {

      this.candidatService.post(this.candidat).subscribe(
        data => {
          this.activeModal.close(data);
        },
        err => {
          this.showError = true;
          console.log(err);
        }
      );
    } else {
      this.candidatService.update(this.candidat).subscribe(
        data => {
          this.activeModal.close(data);
        },
        err => {
          this.showError = true;
          console.log(err);
        }
      );
    }
  }
  /**
   * Ferme la modale sans traitement du formulaire, si il ne s'agit pas d'un mode édition, l'utilisateur est déconnecté.
   * @memberof ModalInitUserComponent
   */
  closeAndDisconnect() {
    this.activeModal.close();

  }
  toggleDatePicker() {
// TODO handle datepicker
  }
}
