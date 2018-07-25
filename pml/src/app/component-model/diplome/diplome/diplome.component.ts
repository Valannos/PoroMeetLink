import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../../shared/services/authentication.service';
import { DiplomeService } from '../../../shared/services/Diplome.service';
import { Diplome } from '../../../shared/models/diplome';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DiplomeModalComponent } from '../diplome-modal/diplome-modal.component';
import { Utilisateur } from '../../../shared/models/utilisateur';

@Component({
  selector: 'app-diplome',
  templateUrl: './diplome.component.html',
  styleUrls: ['./diplome.component.css']
})
export class DiplomeComponent implements OnInit {
  constructor(
    private auth: AuthenticationService,
    private serviceDiplome: DiplomeService,
    private ctrl: NgbModal
  ) {}

  diplomes: Diplome[];
  user: Utilisateur;
  public ref: NgbModalRef;

  ngOnInit() {
    this.auth.getCurrentUtilisateur().subscribe(utilisateur => {
      this.user = utilisateur;
      this.serviceDiplome
        .getAllByCandidat(utilisateur.candidatId)
        .subscribe(data => (this.diplomes = data));
    });
  }

  addDiplome(): void {
    this.ref = this.ctrl.open(DiplomeModalComponent, {
      size: 'lg'
    });
    this.ref.componentInstance.user = this.user;
    this.ref.componentInstance.isEdition = false;
    this.ref.result.then((res) => {
      if (res) {
        this.diplomes.push(res);
      }
    });
  }

  remove(event) {
    console.log(event);
    this.diplomes = this.diplomes.filter(diplome => event.id !== diplome.id);
  }
}
