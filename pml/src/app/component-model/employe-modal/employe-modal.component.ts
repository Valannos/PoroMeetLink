import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { EmployeService } from '../../shared/services/employe.service';
import { Employe } from '../../shared/models/employe';
import { TokenStorage } from '../../shared/services/token.storage';
import { Entreprise } from '../../shared/models/entreprise';
import { Utilisateur } from '../../shared/models/utilisateur';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employe-modal',
  templateUrl: './employe-modal.component.html',
  styleUrls: ['./employe-modal.component.css']
})
export class EmployeModalComponent implements OnInit {
  @Input() employe: Employe;
  @Input() entreprise: Entreprise;
  public formEmploye: FormGroup;
  public showError: Boolean = false;
  public valideButtonLabel: string;
  public isEdition: Boolean = false;

  constructor(
    private activeModal: NgbActiveModal,
    private builder: FormBuilder,
    private employeService: EmployeService,
    private storage: TokenStorage,
    private route: Router
  ) {}

  ngOnInit() {
      this.valideButtonLabel = 'Mettre à jour';
      this.formEmploye = this.builder.group({
      id: [this.employe.id],
      libellePoste: [this.employe.libellePoste, Validators.maxLength(255)],
      nom: [this.employe.nom, [Validators.required, Validators.maxLength(255)]],
      prenom: [this.employe.prenom, Validators.required],
      entrepriseId: [this.employe.entrepriseId, Validators.required],
      utilisateurId: this.employe.utilisateurId
    });
  }

  onSubmitForm() {
    this.employeService.update(this.formEmploye.value).subscribe(
      data => {
        this.activeModal.close(data);
      },
      error => {
        this.showError = true;
      }
    );
  }

  close() {
    this.activeModal.close();
  }
}
