import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../../../shared/models/utilisateur';
import { TokenStorage } from '../../../shared/services/token.storage';
import { UtilisateurService } from '../../../shared/services/utilisateur.service';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { RoleEnum } from '../../../shared/models/role';

@Component({
  selector: 'app-utilisateur-list',
  templateUrl: './utilisateur-list.component.html',
  styleUrls: ['./utilisateur-list.component.css'],

})
export class UtilisateurListComponent implements OnInit {
  public users: Utilisateur[];
  private modalRef: NgbModalRef;


  constructor(
    private storage: TokenStorage,
    private userService: UtilisateurService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.userService.getAll().subscribe(data => {
      console.log(data);
      this.users = data;
    });
  }

  open(content) {
    this.modalRef = this.modalService.open(content, { size: 'lg' });
  }


  onDeletion(user: Utilisateur) {
    this.userService.delete(user.id).subscribe(
      resp => {
        const removed: Utilisateur = this.users.find(
          current => current.id === user.id
        );
        this.users.splice(this.users.indexOf(removed, 0), 1);
        this.modalRef.close();
      },
      error => {
        console.log(error);
      }
    );

    this.modalRef.close();
  }

  onSuspendAccount(user: Utilisateur) {
    this.userService.suspendUtilisateur(user.id).subscribe(
      resp => {
        const index: number =  this.users.findIndex(
          (sec) =>
            sec.id === resp.id
        );
        this.users[index] = resp;
      },
      error => {
        console.log(error);
      }
    );
  }
  userIsEmploye(user: Utilisateur) {
    return this.userService.isUserEmploye(user);
  }
  userIsCandidat(user: Utilisateur) {
    return this.userService.isUserCandidat(user);
  }
}
