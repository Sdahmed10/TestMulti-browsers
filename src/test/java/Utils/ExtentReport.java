package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

// Classe utilitaire pour gérer le rapport ExtentReports
public class ExtentReport {
    private static ExtentReports extent; // Objet unique

    public static ExtentReports getInstance() {
        if (extent == null) {
            try {
                // Définition du chemin du rapport HTML
                Path reportDir = Path.of(System.getProperty("user.dir"), "reports");
                Files.createDirectories(reportDir); // crée le dossier si nécessaire
                String reportPath = reportDir.resolve("TestReport.html").toString();

                // Création du reporter Spark (HTML)
                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setDocumentTitle("Automation Report");
                spark.config().setReportName("Login Test Execution");

                // Liaison du reporter à ExtentReports
                extent = new ExtentReports();
                extent.attachReporter(spark);

                // Informations supplémentaires sur le système
                extent.setSystemInfo("Tester", "Ahmed Sdiri");
                extent.setSystemInfo("Browser", "MultiBrowser");
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize ExtentReports: " + e.getMessage());
            }
        }
        return extent;
    }
}
