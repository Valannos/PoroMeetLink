package fr.imie.poromeetlink.outils.constantes;

public class UrlConstants {

    public static final String API_URL = "/api/";
    public static final String ID_URL = "/{id}";
    public static final String REGISTER_USER_URL = API_URL + EntityUtils.UTILISATEUR;
    public static final String LOGIN_URL = API_URL + "login";
    public static final String REFRESH_TOKEN = "/refresh";
    public static final String REFRESH_URL = API_URL + EntityUtils.UTILISATEUR + REFRESH_TOKEN;

    public static final String ID_ANNONCE_ID_COMPETENCE = "/{idAnnonce}/{idCompetence}";
    public static final String ID_CANDIDAT_ID_ANNONCE = "/{idCandidat}/{idAnnonce}";
    public static final String ID_CANDIDAT_ID_COMPETENCE = "/{idCandidat}/{idCompetence}";

}
