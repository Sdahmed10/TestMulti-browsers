package BaseTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.*;


//Classe de base utilisée pour exécuter les tests sur plusieurs navigateurs
public class TestMultiBrowsers {
    Browser browser1; // Objet Browser qui représente le navigateur ouvert
    protected Page page; // Page Playwright utilisée dans les tests
    Playwright playwright;  // Instance principale Playwright (point d'entrée)


    // Méthode de configuration : s’exécute avant chaque méthode de test
    // Elle prend en paramètre "browser" défini dans le fichier testng.xml
    @Parameters ("browser")
    @BeforeMethod
    public void setup(String browser) {
        playwright = Playwright.create(); // Démarre le moteur Playwright
        BrowserType browserType = null; // Initialise le type de navigateur
        if (browser.equalsIgnoreCase("chrome"))
        {
            browserType = playwright.chromium();
        }
        else if  (browser.equalsIgnoreCase("firefox"))
        {
            browserType = playwright.firefox();
        }
        else if (browser.equalsIgnoreCase("safari"))
        {
            browserType = playwright.webkit();
        }

        // Lance le navigateur en mode non-headless avec ralentissement de 500ms (pour visualiser les actions)

        browser1 = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(500));

        // Ouvre une nouvelle page
        page = browser1.newPage();
    }


    // Méthode de nettoyage : s’exécute après chaque test
    @AfterMethod
    public void tearDown() {
        page.close();
        browser1.close();
        playwright.close();
    }
}
