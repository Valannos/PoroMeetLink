import { Component, OnInit, Input } from '@angular/core';
import { DiplomeService } from '../../../shared/services/Diplome.service';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { Diplome, StatutDiplomeEnum } from '../../../shared/models/diplome';
import { IModal } from '../../abstract/abstract.modal';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import {
  FormGroup,
  FormBuilder,
  Validators,
  FormControl} from '@angular/forms';
import { TypeDiplome } from '../../../shared/models/type.diplome';
import { TypeDiplomeService } from '../../../shared/services/type.diplome.service';

@Component({
  selector: 'app-diplome-modal',
  templateUrl: './diplome-modal.component.html',
  styleUrls: ['./diplome-modal.component.css']
})
export class DiplomeModalComponent implements OnInit, IModal {
  @Input() user: Utilisateur;
  @Input() public diplome: Diplome;
  public diplomeForm: FormGroup;
  public valideButtonLabel: string;
  public isEdition: Boolean = false;
  public errorOnSubmit: Boolean = false;
  public titre: string;
  @Input() types: TypeDiplome[];
  public statutDiplomeEnum = StatutDiplomeEnum;
  public option: Number;
  public isLoading = false;

  constructor(
    private activeModal: NgbActiveModal,
    private typeService: TypeDiplomeService,
    private builder: FormBuilder,
    private diplomeService: DiplomeService
  ) {}

  ngOnInit() {
    if (this.isEdition) {
      this.valideButtonLabel = 'Mettre à jour';
      this.titre = 'Modifier un diplôme';
    } else {
      this.diplome = new Diplome();
      this.valideButtonLabel = 'Valider';
      this.titre = 'Ajouter un diplôme';
    }
    this.typeService.getAll().subscribe(data => (this.types = data));
    this.initForm();
  }
  initForm(): void {
    this.diplomeForm = this.builder.group({
      id: [this.diplome.id],
      intitule: [this.diplome.intitule, Validators.required],
      anneeObtention: [
        this.diplome.anneeObtention,
        [Validators.pattern('^\\d{4}$')]
      ],
      etablissement: [this.diplome.etablissement],
      typeDiplome: [this.diplome.typeDiplome, [Validators.required]],
      diplomeEnum: [this.diplome.diplomeEnum, Validators.required],
      idCandidat: [(this.isEdition) ? this.diplome.idCandidat : this.user.candidatId, [Validators.required]]
    });
    this.diplomeForm.valueChanges.subscribe(() => {
      this.diplome = this.diplomeForm.value;
    });
  }
  onSubmitForm(): void {
    this.isLoading = true;
    if (this.isEdition) {
      this.diplomeService
      .update(this.diplome)
      .subscribe(dip => {
        this.isLoading = false;
        this.activeModal.close(dip);
      }, (err) => {
        this.errorOnSubmit = true;
        this.isLoading = false;
      });
    } else {
      this.diplomeService
        .post(this.diplome)
        .subscribe(dip => {
          this.isLoading = false;
          this.activeModal.close(dip);
        }, (err) => {
          this.errorOnSubmit = true;
          this.isLoading = false;
        });
    }
  }
  close(): void {
    this.activeModal.close();
  }

  choose(event) {
    this.diplomeForm.controls['diplomeEnum'].setValue(event);
    console.log(this.diplomeForm.controls['diplomeEnum'].value)
  }
}
