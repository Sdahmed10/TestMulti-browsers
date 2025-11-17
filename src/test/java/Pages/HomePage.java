package Pages;

import com.microsoft.playwright.Page;
import org.testng.annotations.Test;

public class HomePage {

    // Sélecteurs des éléments de la page
    private static final String CART_BUTTON = "//button[normalize-space()='Cart']";

    // Objet Playwright Page (reçu depuis la classe de test)
    public Page page;

    // TestNG permettra d'exécuter cette méthode comme un test si appelée directement
    @Test
    public void HomePageTest() {
        // Attendre que le bouton "Cart" apparaisse
        page.waitForSelector(CART_BUTTON);

        // Vérifier si le bouton "Cart" est visible
        if (page.locator(CART_BUTTON).isVisible()) {
            System.out.println("Home page is displayed!");
        } else {
            System.out.println("Home page is not displayed!");
        }
    }
}
