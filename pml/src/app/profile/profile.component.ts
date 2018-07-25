import { Component, OnInit, Input } from '@angular/core';
import { Utilisateur } from '../shared/models/utilisateur';
import { UtilisateurService } from '../shared/services/utilisateur.service';
import { Candidat } from '../shared/models/candidat';
import { CandidatService } from '../shared/services/candidat.service';
import { Employe } from '../shared/models/employe';
import { AuthenticationService } from '../shared/services/authentication.service';
import { EmployeService } from '../shared/services/employe.service';
/**
 * Component de base pour gérer les profiles utilisateurs
 *
 * @export
 * @class ProfileComponent
 * @implements {OnInit}
 */
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  @Input() user: Utilisateur;
  @Input() candidat: Candidat;
  @Input() employe: Employe;
  isLoading = false;

  constructor(
    private userService: UtilisateurService,
    private candidatService: CandidatService,
    private employeService: EmployeService,
    private auth: AuthenticationService
  ) {}

  ngOnInit() {
    this.userService.getCurrentUtilisateur().subscribe(response => {
      this.user = response;
      if (this.user.candidatId) {
        this.candidatService
          .getById(this.user.candidatId)
          .subscribe(candidat => (this.candidat = candidat));
      } else if (this.user.employeId) {
        this.employeService
          .getData()
          .subscribe(employe => (this.employe = employe));
      }
    });
  }
  isCandidat(): Boolean {
    return this.auth.isCandidat;
  }

  isEmploye(): Boolean {
    return this.auth.isGerant || this.auth.isAdminCompte;
  }
}
