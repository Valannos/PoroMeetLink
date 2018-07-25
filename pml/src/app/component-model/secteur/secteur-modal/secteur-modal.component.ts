import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Secteur } from '../../../shared/models/secteur';
import { SecteurService } from '../../../shared/services/secteur.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-secteur-modal',
  templateUrl: './secteur-modal.component.html',
  styleUrls: ['./secteur-modal.component.css']
})
export class SecteurModalComponent implements OnInit {
  constructor(private builder: FormBuilder,
              private secteurService: SecteurService,
              private activeModal: NgbActiveModal) {}

  public formSecteur: FormGroup;
  public valideButtonLabel: string;
  public isEdition: Boolean = false;
  public errorOnSubmit: Boolean = false;
  @Input() secteur: Secteur;

  ngOnInit() {
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
    } else {
      this.valideButtonLabel = 'Valider';
      this.secteur = new Secteur();
    }
    this.initForm();
  }

  private initForm() {
    this.formSecteur = this.builder.group({
      libelle: [
        this.secteur.libelle,
        [Validators.required, Validators.maxLength(255)]
      ],
      id: this.secteur.id
    });
  }

  onSubmitForm() {
    if (this.isEdition) {
      this.secteurService.update(this.formSecteur.value).subscribe(secteur => {
        this.activeModal.close(secteur);
      }, err => this.errorOnSubmit = true);
    } else {
      this.secteurService.post(this.formSecteur.value).subscribe(secteur => {
        this.activeModal.close(secteur);
      }, err => this.errorOnSubmit = true);
    }
  }
  public close(): void {
    this.activeModal.dismiss();
  }
}
