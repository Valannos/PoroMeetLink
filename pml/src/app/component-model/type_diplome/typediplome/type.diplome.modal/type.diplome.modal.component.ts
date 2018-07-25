import { Component, OnInit, Input } from '@angular/core';
import { TypeDiplomeService } from '../../../../shared/services/type.diplome.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { TypeDiplome } from '../../../../shared/models/type.diplome';
import {
  FormGroup,
  FormBuilder,
  FormControl,
  Validators
} from '@angular/forms';
import { IModal } from '../../../abstract/abstract.modal';

@Component({
  selector: 'app-type-diplome-modal',
  templateUrl: './type.diplome.modal.component.html',
  styleUrls: ['./type.diplome.modal.component.css']
})
export class TypeDiplomeModalComponent implements OnInit, IModal {
  constructor(
    private builder: FormBuilder,
    private service: TypeDiplomeService,
    private activeModal: NgbActiveModal
  ) {}

  public isEdition: boolean;
  public formTypeDiplome: FormGroup;
  public valideButtonLabel: string;
  public errorOnSubmit = false;
  @Input() type: TypeDiplome;

  ngOnInit() {
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
    } else {
      this.type = new TypeDiplome();
      this.valideButtonLabel = 'Valider';
    }
    this.initForm();
  }

  initForm(): void {
    this.formTypeDiplome = this.builder.group({
      intitule: [this.type.intitule, [Validators.required]],
      id: [this.type.id]
    });
  }

  onSubmitForm(): void {
    if (this.isEdition) {
      this.service.update(this.formTypeDiplome.value).subscribe(type => {
        this.activeModal.close(type);
      }, () => (this.errorOnSubmit = true));
    } else {
      this.service.post(this.formTypeDiplome.value).subscribe(type => {
        this.activeModal.close(type);
      }, () => (this.errorOnSubmit = true));
    }
  }

  close(): void {
    this.activeModal.close();
  }
}
