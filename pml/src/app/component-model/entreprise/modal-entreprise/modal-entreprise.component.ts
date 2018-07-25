import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {
  NgbActiveModal,
  NgbModal
} from '@ng-bootstrap/ng-bootstrap';
import { EntrepriseService } from '../../../shared/services/entreprise.service';
import { Secteur } from '../../../shared/models/secteur';
import { SecteurService } from '../../../shared/services/secteur.service';
import { Entreprise } from '../../../shared/models/entreprise';
import { TokenStorage } from '../../../shared/services/token.storage';
import { EmployeModalComponent } from '../../employe-modal/employe-modal.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modal-entreprise',
  templateUrl: './modal-entreprise.component.html',
  styleUrls: ['./modal-entreprise.component.css']
})
export class ModalEntrepriseComponent implements OnInit {
  constructor(
    private activeModal: NgbActiveModal,
    private modalService: NgbModal,
    private builder: FormBuilder,
    private service: EntrepriseService,
    private secteurService: SecteurService,
    private entrepriseService: EntrepriseService,
    private storage: TokenStorage,
    private router: Router
  ) {}
  @Input() entreprise: Entreprise;
  phone: RegExp = new RegExp('(0|\\+33|0033)[1-9][0-9]{8}');
  valideButtonLabel: String;
  formEntreprise: FormGroup;
  secteurs: Secteur[];
  showError: Boolean = false;
  isEdition: Boolean;

  ngOnInit() {
    if (!this.secteurs) {
      this.secteurService.getAll().subscribe(scts => {
        this.secteurs = scts;
      });
    }
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
    } else {
      this.entreprise = new Entreprise();
      this.valideButtonLabel = 'Valider';
    }
    this.initiateBuilder();
  }

  closeModale() {
    this.activeModal.close();
  }

  initiateBuilder() {
    this.formEntreprise = this.builder.group({
      intitule: [this.entreprise.intitule, Validators.required],
      adresse: [this.entreprise.adresse, Validators.required],
      telephone: [this.entreprise.telephone, Validators.pattern(this.phone)],
      siret: [this.entreprise.siret, [Validators.pattern('[0-9]{14}'), Validators.required]],
      secteurs: [this.secteurs]
    });
  }

  onSubmitForm() {

    const formValue = this.formEntreprise.value;

    this.entreprise.intitule = formValue['intitule'];
    this.entreprise.secteurs = formValue['secteurs'];
    this.entreprise.siret = formValue['siret'];
    this.entreprise.telephone = formValue['telephone'];
    this.entreprise.adresse = formValue['adresse'];
    if (this.isEdition) {
      this.entrepriseService.update(this.entreprise).subscribe(
        data => {
          this.entreprise = data;
          this.entrepriseService.setData(this.entreprise);
          this.activeModal.close(this.entreprise);
        },
        error => {
          console.log(error);
        }
      );
    } else {
      this.entrepriseService.post(this.entreprise).subscribe(
        data => {
          this.entreprise = data;
          this.entrepriseService.setData(this.entreprise);
          this.activeModal.close(this.entreprise);
        },
        error => {
          console.log(error);
        }
      );
    }
  }
}
