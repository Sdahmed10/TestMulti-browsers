package Pages;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

// Classe représentant la page de connexion
public class LoginPage {
    // URL de la page de login
    private static final String LOGIN_URL = "https://freelance-learn-automation.vercel.app/login";

    // Sélecteurs des éléments de la page
    private static final String EMAIL_FIELD = "//input[@id='email1']";
    private static final String PASSWORD_FIELD = "//input[@id='password1']";
    private static final String SIGN_IN_BUTTON   = "//button[normalize-space()='Sign in']";
    private static final String SUCCESS_LOGIN_BUTTON = "//button[normalize-space()='Cart']";

    // Données de connexion (en dur ici pour la démo)
    private static final String EMAIL = "liviso7195@agenra.com";
    private static final String PASSWORD = "12345678";



    // Objet Playwright Page (reçu depuis la classe de test)
    public Page page;

    // TestNG permettra d'exécuter cette méthode comme un test si appelée directement
        @Test
        public void loginTest() {
            // Ouvrir la page de connexion
            page.navigate(LOGIN_URL);
            System.out.println("Title of the page: " + page.title());

            // Remplir les champs email et mot de passe
            page.fill(EMAIL_FIELD, EMAIL);
            page.fill(PASSWORD_FIELD, PASSWORD);

            // Cliquer sur le bouton "Sign in"
            page.click(SIGN_IN_BUTTON);

            // Attendre que le bouton "Cart" apparaisse → signe d’une connexion réussie
            page.waitForSelector(SUCCESS_LOGIN_BUTTON);

            // Vérifier si le bouton "Cart" est visible
            if (page.locator(SUCCESS_LOGIN_BUTTON).isVisible()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }
        }
    }

