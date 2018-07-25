import { Component, OnInit } from '@angular/core';
import { Utilisateur } from '../shared/models/utilisateur';
import { Router } from '@angular/router';
import { UtilisateurService } from '../shared/services/utilisateur.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RoleEnum, Role } from '../shared/models/role';
import { AuthenticationService } from '../shared/services/authentication.service';
import { TokenStorage } from '../shared/services/token.storage';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: Utilisateur;
  isAlertVisible: Boolean = false;
  form: FormGroup;
  regex: RegExp;

  ENT: String = 'entreprise';
  CAN: String = 'candidat';
  isGerant: boolean;
  isRecruteur: boolean;
  option: String;

  constructor(
    private route: Router,
    private userService: UtilisateurService,
    private builder: FormBuilder,
    private auth: AuthenticationService,
    private storage: TokenStorage
  ) {}

  ngOnInit() {
    this.regex = new RegExp(
      '(?=.[a-z])(?=.[A-Z])(?=.d)(?=.[$@$!%?&])(?=S+$)[A-Za-zd$@$!%?&]{8,15}'
    );

    this.user = new Utilisateur();
    this.form = this.builder.group({
      username: [
        this.user.username,
        [Validators.required, Validators.maxLength(255)]
      ],
      password: [
        this.user.password,
        [
          Validators.maxLength(255),
          Validators.required
          // Validators.pattern(this.regex)
        ]
      ],
      roles: [this.user.roles, Validators.required],
      email: [this.user.email, Validators.required]
    });
  }

  choose(event) {
    this.setRoles(event);
  }

  public onValide(): void {
    this.setAdditionnalRole();
    this.userService.post(this.form.value).subscribe(
      _user => {
        this.auth.attemptAuthentication(this.form.value).subscribe(
          data => {
            this.storage.saveToken(data);
            this.route.navigate(['home']);
          },
          error => {
            this.route.navigate(['']);
          }
        );
      },
      err => {
        console.log(err);
      }
    );
  }

  private setRoles(event): void {
    this.user.roles = [];
    if (event === this.CAN) {
      this.user.roles.push(new Role(RoleEnum.Candidat));
    } else if (event === this.ENT) {
      this.user.roles.push(new Role(RoleEnum.AdministrateurCompte));
      if (this.isRecruteur) {
        this.user.roles.push(new Role(RoleEnum.Recruteur));
      }
      if (this.isGerant) {
        this.user.roles.push(new Role(RoleEnum.Gerant));
      }
    }
    this.form.controls['roles'].setValue(this.user.roles);
  }

  private setAdditionnalRole() {
    if (this.option === this.ENT) {
      if (this.isGerant) {
        this.user.roles.push(new Role(RoleEnum.Gerant));
      }
      if (this.isRecruteur) {
        this.user.roles.push(new Role(RoleEnum.Gerant));
      }
      this.form.controls['roles'].setValue(this.user.roles);
    }
  }
}
