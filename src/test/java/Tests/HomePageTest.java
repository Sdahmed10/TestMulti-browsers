package Tests;

import BaseTest.TestMultiBrowsers;
import Pages.HomePage;
import Pages.LoginPage;
import Utils.ExtentReport;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestMultiBrowsers {
    HomePage homePage;
    LoginPage loginPage; // Objet Page
    static ExtentReports extent;  // Rapport global
    ExtentTest test;           // Rapport spécifique à un test

    @BeforeClass
    public void initReport() {
        // Initialise le rapport avant d’exécuter les tests de cette classe
        extent = ExtentReport.getInstance();
    }

    @BeforeMethod
    public void setUpTest() {
        // Crée un nouvel objet Home page et assigne la page Playwright
        homePage = new HomePage();
        loginPage = new LoginPage();
        loginPage.page = this.page;
        homePage.page = this.page;

        // Crée un test nommé dans le rapport
        test = extent.createTest("Login Test - Verify login functionality");
    }

    @Test(priority = 1)
    public void testLogin() {
        try {
            // Exécution du scénario de login
            loginPage.loginTest();
            test.pass("Login executed successfully");  // Succès
        } catch (Exception e) {
            // En cas d’erreur, on logue dans le rapport
            test.fail("Login failed: " + e.getMessage());
            throw e; // Relance l’exception pour que TestNG marque le test comme échoué
        }
    }
    @Test(priority = 2)
    public void testHomepage() {
        try {
            // Exécution du scénario de verification
            homePage.HomePageTest();
            test.pass("Homepage executed successfully");
        } catch (Exception e) {
            test.fail("Homepage failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownReport() {
        // Génère le rapport HTML à la fin de la classe
        extent.flush();
    }

}
