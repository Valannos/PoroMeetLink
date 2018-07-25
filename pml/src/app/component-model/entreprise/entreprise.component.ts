import { Component, OnInit, Input } from '@angular/core';
import { Entreprise } from '../../shared/models/entreprise';
import { Utilisateur } from '../../shared/models/utilisateur';
import { TokenStorage } from '../../shared/services/token.storage';
import { UtilisateurService } from '../../shared/services/utilisateur.service';
import { EntrepriseService } from '../../shared/services/entreprise.service';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalEntrepriseComponent } from './modal-entreprise/modal-entreprise.component';
import { Employe } from '../../shared/models/employe';
import { AuthenticationService } from '../../shared/services/authentication.service';
import { EmployeService } from '../../shared/services/employe.service';

@Component({
  selector: 'app-entreprise',
  templateUrl: './entreprise.component.html',
  styleUrls: ['./entreprise.component.css']
})
export class EntrepriseComponent implements OnInit {
  constructor(
    private entrepriseService: EntrepriseService,
    private utilisateurService: UtilisateurService,
    private modalService: NgbModal,
    private auth: AuthenticationService,
    private emplService: EmployeService
  ) {}

  public entreprise: Entreprise;
  public user: Utilisateur;
  refModale: NgbModalRef;

  ngOnInit() {
    this.auth.getCurrentUtilisateur().subscribe(user => {
      this.user = user;
      if (user.employeId) {
        this.entrepriseService.getData().subscribe(ent => this.entreprise = ent);
      }
    });
  }

  isEntrepriseUndefined(): boolean {
    return this.entreprise == null;
  }
  openModal(idEdition: boolean) {
    this.refModale = this.modalService.open(ModalEntrepriseComponent, {
      size: 'lg'
    });
    this.refModale.componentInstance.isEdition = idEdition;
    if (idEdition) {
      this.refModale.componentInstance.valideButtonLabel = 'Mettre à jour';
      this.refModale.componentInstance.entreprise = this.entreprise;
    } else {
      this.refModale.componentInstance.valideButtonLabel = 'Valider';
    }
    this.refModale.result.then(result => {
      if (result) {
        this.entreprise = result;
      }
      if (!idEdition) {
       const empl: Employe = new Employe();
       empl.utilisateurId = this.user.id;
       empl.entrepriseId = this.entreprise.id;
       this.emplService.post(empl).subscribe(data => {
         this.emplService.setData(data);
         this.user.employeId = data.id;
         this.auth.setCurrentUtilisateur(this.user);
       });
      }
    });
  }
}
