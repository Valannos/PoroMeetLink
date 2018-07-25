import { Component, OnInit } from '@angular/core';
import { TokenStorage } from '../../shared/services/token.storage';
import { EmployeService } from '../../shared/services/employe.service';
import { Employe } from '../../shared/models/employe';
import { EntrepriseService } from '../../shared/services/entreprise.service';
import { Entreprise } from '../../shared/models/entreprise';
import { Utilisateur } from '../../shared/models/utilisateur';
import { UtilisateurService } from '../../shared/services/utilisateur.service';
import { AuthenticationService } from '../../shared/services/authentication.service';

@Component({
  selector: 'app-employe',
  templateUrl: './employe.component.html',
  styleUrls: ['./employe.component.css']
})
export class EmployeComponent implements OnInit {
  currentEmploye: Employe;
  user: Utilisateur;
  employes: Employe[];
  entreprise: Entreprise;

  constructor(
    private employeService: EmployeService,
    private entrepriseService: EntrepriseService,
    private auth: AuthenticationService
  ) {}

  ngOnInit() {
    this.auth.getCurrentUtilisateur().subscribe(user => {
      if (user.employeId) {
        this.employeService.getData().subscribe(empl => {
          this.currentEmploye = empl;
          this.fetchEmployes();
          this.entrepriseService.getData().subscribe((entreprise) => this.entreprise = entreprise);
        });
      }
    });
  }

  onDeletion(employe: Employe) {
    // TODO implémenter la suppression d'un employé.
  }

  fetchEmployes(): void {
    this.employeService
      .getAllByEntrepriseId(this.currentEmploye.entrepriseId)
      .subscribe(empls => {
        this.employes = empls;
      });
  }
}
