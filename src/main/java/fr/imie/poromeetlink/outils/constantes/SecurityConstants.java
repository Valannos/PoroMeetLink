package fr.imie.poromeetlink.outils.constantes;



public class SecurityConstants {

    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final Long EXPIRATION_TIME = 15L;
    public static final Long EXPIRATION_TIME_REFRESH_TOKEN = 7L;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String COOKIE_NAME = "token";

}
