package fr.imie.poromeetlink.outils.constantes;

public class EntityUtils {

    public static final String SECTEUR = "secteur";
    public static final String CANDIDAT = "candidat";
    public static final String UTILISATEUR = "utilisateur";
    public static final String ENTREPRISE = "entreprise";
    public static final String EMPLOYE = "employe";
    public static final String COMPETENCE = "competence";
    public static final String DIPLOME = "diplome";
    public static final String EXPERIENCE = "experience";
    public static final String COMPETENCE_CANDIDAT = "competence_candidat";
    public static final String COMPETENCE_ANNONCE = "competence_annonce";
    public static final String TYPE_DIPLOME = "type_diplome";
    public static final String ANNONCE = "annonce";
    public static final String PROPOSITION_CANDIDATURE = "proposition_candidature";


    public static final String PHONE = "(0|\\+33|0033)[1-9][0-9]{8}";
    public static final String SIRET = "[0-9]{14}";
    public static final String EMAIL = "^[_a-z0-9-]+(\\.?[_a-z0-9-]+)@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
    // Au moins 8 caractères ; au moins : 1 chiffre, 1 minuscule, 1 majuscule, 1 caractère spécial ; pas d'espace
    public static final String PASSWORD = "((?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%])(?=\\S+$).{8,})";

}
