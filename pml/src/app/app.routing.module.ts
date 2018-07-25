import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConnexionComponent } from './connexion/connexion.component';
import { PageNotFoundComponentComponent } from './page-not-found-component/page-not-found-component.component';
import { AvatarSelectionComponent } from './avatar-selection/avatar-selection.component';
import { SecteurComponent } from './component-model/secteur/secteur.component';
import { AccueilComponent } from './accueil/accueil.component';
import { ProfileComponent } from './profile/profile.component';
import { UtilisateurListComponent } from './component-model/utilisateur/utilisateur-list/utilisateur-list.component';
import { EntrepriseComponent } from './component-model/entreprise/entreprise.component';
import { EmployeComponent } from './component-model/employe/employe.component';
import { RegistrationComponent } from './registration/registration.component';
import { CompetenceComponent } from './component-model/competence/competence.component';
import { CompetencesCandidatComponent } from './component-model/competence/competences-candidat/competences-candidat.component';
import { TypeDiplomeComponent } from './component-model/type_diplome/typediplome/typediplome.component';
import { DiplomeComponent } from './component-model/diplome/diplome/diplome.component';
import { AnnoncesComponent } from './component-model/annonce/annonces/annonces.component';



const routes: Routes = [
    {
        path: '',
        // redirectTo: 'test',
        pathMatch: 'full',
        component: ConnexionComponent
    },
    {
        path: 'competence',
        component: CompetenceComponent
    },
    {
      path: 'mescompetences',
      component: CompetencesCandidatComponent
    },
    {

        path: 'avatar-selection',
        component: AvatarSelectionComponent
    },
    {

        path: 'home',
        component: AccueilComponent,
    },
    {
      path: 'secteur',
      component: SecteurComponent,
    },
    {
      path: 'utilisateurs',
      component: UtilisateurListComponent
    },

    {
      path: 'profile',
      component: ProfileComponent
    },
    {
      path: 'entreprise',
      component: EntrepriseComponent
    },
    {
      path: 'employes',
      component: EmployeComponent
    },
    {
      path: 'typediplome',
      component: TypeDiplomeComponent
    },
    {
      path: 'diplomes',
      component: DiplomeComponent
    },
    {
      path: 'annonces',
      component: AnnoncesComponent
    },
    {
        path: 'not-found',
        component: PageNotFoundComponentComponent
    },
    {
        path: 'avatar-selection',
        component: AvatarSelectionComponent
    },
    {
      path: 'registration',
      component: RegistrationComponent
    },
    {
        path: '**',
        redirectTo: 'not-found'
    }


];

@NgModule({
    imports: [ RouterModule.forRoot(routes) ],
    exports: [ RouterModule ]
})
export class AppRoutingModule { }
