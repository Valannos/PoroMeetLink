import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule, NgbDatepickerI18n, NgbDateAdapter, NgbDateNativeAdapter } from '@ng-bootstrap/ng-bootstrap';

// pages
import { AppComponent } from './app.component';
import { FlexLayoutModule } from '@angular/flex-layout';

// composants communs
import { PoroNavComponent } from './poro-nav/poro-nav.component';
import { PageNotFoundComponentComponent } from './page-not-found-component/page-not-found-component.component';
import { AppRoutingModule } from './/app.routing.module';
import { ConnexionComponent } from './connexion/connexion.component';
import { AvatarSelectionComponent } from './avatar-selection/avatar-selection.component';
import { AvatarImageComponent } from './avatar-image/avatar-image.component';

// module http
import { HttpClientModule } from '@angular/common/http';

// module métier
import { SecteurComponent } from './component-model/secteur/secteur.component';
import { SecteurService } from './shared/services/secteur.service';
import { AuthenticationService } from './shared/services/authentication.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/services/authentication.interceptor';
import { TokenStorage } from './shared/services/token.storage';
import { AccueilComponent } from './accueil/accueil.component';
import { ProfileComponent } from './profile/profile.component';
import { CandidatService } from './shared/services/candidat.service';
import { SecteurComboboxComponent } from './component-model/secteur/secteur-combobox/secteur-combobox.component';
import { UtilisateurService } from './shared/services/utilisateur.service';
import { CandidatComponent } from './component-model/candidat/candidat.component';
import { BoutonGeneriqueComponent } from './shared/bouton-generique/bouton-generique.component';
import { UtilisateurListComponent } from './component-model/utilisateur/utilisateur-list/utilisateur-list.component';
import { RolePipe } from './shared/pipes/role.pipe';
import { CandidatDetailsComponent } from './component-model/candidat/candidat-details/candidat-details.component';
import { EmployeComponent } from './component-model/employe/employe.component';
import { EmployeDetailComponent } from './component-model/employe/employe-detail/employe-detail.component';
import { EmployeService } from './shared/services/employe.service';
import { I18nService } from './shared/services/i18n.service';
import { I18nDatePickerService } from './shared/services/i18n-date-picker.service';
import { EntrepriseComponent } from './component-model/entreprise/entreprise.component';
import { AnnonceService } from './shared/services/annonce.service';
import { EntrepriseService } from './shared/services/entreprise.service';
import { EntrepriseDetailsComponent } from './component-model/entreprise/entreprise-details/entreprise-details.component';
import { ModalEntrepriseComponent } from './component-model/entreprise/modal-entreprise/modal-entreprise.component';
import { EmployeModalComponent } from './component-model/employe-modal/employe-modal.component';
import { SecteurModalComponent } from './component-model/secteur/secteur-modal/secteur-modal.component';
import { DatepickerComponent } from './shared/datepicker/datepicker.component';
import { RegistrationComponent } from './registration/registration.component';
import { CompetenceComponent } from './component-model/competence/competence.component';
import { CompetenceService } from './shared/services/competence.service';
import { CompetenceModalComponent } from './component-model/competence/competence-modal/competence-modal.component';
import { CompetencesCandidatComponent } from './component-model/competence/competences-candidat/competences-candidat.component';
import { CompetencesCandidatDetailsComponent } from './component-model/competence/competences-candidat-details/competences-candidat-details.component';
import { CompetenceCandidatModalComponent } from './component-model/competence/competence-candidat-modal/competence-candidat-modal.component';
import { CandidatModalComponent } from './component-model/candidat/candidat-modale/candidat-modale.component';
import { CompetenceCandidatService } from './shared/services/competence.candidat.service';
import { DiplomeComponent } from './component-model/diplome/diplome/diplome.component';
import { DiplomeService } from './shared/services/Diplome.service';
import { TypeDiplomeService } from './shared/services/type.diplome.service';
import { TypeDiplomeComponent } from './component-model/type_diplome/typediplome/typediplome.component';
import { TypeDiplomeModalComponent } from './component-model/type_diplome/typediplome/type.diplome.modal/type.diplome.modal.component';
import { DiplomeModalComponent } from './component-model/diplome/diplome-modal/diplome-modal.component';
import { DiplomeDetailsComponent } from './component-model/diplome/diplome-details/diplome-details.component';
import { AnnoncesComponent, WarningAnnonceModalComponent } from './component-model/annonce/annonces/annonces.component';
import { CompetenceAnnonceService } from './shared/services/competence.annonce.service';
import { PoroFooterComponent } from './poro-footer/poro-footer.component';
import { AnnonceModalComponent } from './component-model/annonce/annonce-modal/annonce-modal.component';
import { EnumPiper } from './shared/pipes/enum.pipe';
import { ComptenceAnnonceDetailsComponent } from './component-model/competence/competence-annonce/comptence-annonce-details/comptence-annonce-details.component';
import { ComptenceAnnonceModalComponent } from './component-model/competence/competence-annonce/comptence-annonce-modal/comptence-annonce-modal.component';
import { PropositionCandidatureService } from './shared/services/proposition-candidature.service';
import { PropositionModalComponent } from './component-model/proposition/proposition-modal/proposition-modal.component';
import { HashLocationStrategy, LocationStrategy } from '../../node_modules/@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    PoroNavComponent,
    PageNotFoundComponentComponent,
    ConnexionComponent,
    AvatarSelectionComponent,
    AvatarImageComponent,
    SecteurComponent,
    AccueilComponent,
    ProfileComponent,
    SecteurComponent,
    SecteurComboboxComponent,
    CandidatComponent,
    AvatarSelectionComponent,
    BoutonGeneriqueComponent,
    UtilisateurListComponent,
    RolePipe,
    CandidatDetailsComponent,
    EmployeComponent,
    EmployeDetailComponent,
    CandidatModalComponent,
    EntrepriseComponent,
    EntrepriseDetailsComponent,
    ModalEntrepriseComponent,
    EmployeModalComponent,
    SecteurModalComponent,
    DatepickerComponent,
    RegistrationComponent,
    CompetenceComponent,
    CompetenceModalComponent,
    CompetencesCandidatComponent,
    CompetencesCandidatDetailsComponent,
    CompetenceCandidatModalComponent,
    DiplomeComponent,
    TypeDiplomeComponent,
    TypeDiplomeModalComponent,
    DiplomeModalComponent,
    DiplomeDetailsComponent,
    AnnoncesComponent,
    PoroFooterComponent,
    AnnonceModalComponent,
    EnumPiper,
    WarningAnnonceModalComponent,
    ComptenceAnnonceDetailsComponent,
    ComptenceAnnonceModalComponent,
    PropositionModalComponent,
  ],
  entryComponents: [
    CandidatModalComponent,
    ModalEntrepriseComponent,
    EmployeModalComponent,
    SecteurModalComponent,
    CompetenceModalComponent,
    CompetenceCandidatModalComponent,
    TypeDiplomeModalComponent,
    DiplomeModalComponent,
    AnnonceModalComponent,
    WarningAnnonceModalComponent,
    ComptenceAnnonceModalComponent,
    PropositionModalComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    FlexLayoutModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule.forRoot()
  ],
  providers: [
    SecteurService,
    TokenStorage,
    AuthenticationService,
    CandidatService,
    UtilisateurService,
    EmployeService,
    I18nService,
    I18nDatePickerService,
    AnnonceService,
    EntrepriseService,
    CompetenceService,
    CompetenceCandidatService,
    DiplomeService,
    TypeDiplomeService,
    AnnonceService,
    CompetenceAnnonceService,
    PropositionCandidatureService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: NgbDatepickerI18n,
      useClass: I18nDatePickerService
    },
    {
      provide: NgbDateAdapter,
      useClass: NgbDateNativeAdapter
    },
    {
      provide: LocationStrategy,
      useClass: HashLocationStrategy
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
