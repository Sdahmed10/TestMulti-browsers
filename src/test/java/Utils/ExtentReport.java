package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

// Classe utilitaire pour gérer le rapport ExtentReports
public class ExtentReport {
    private static ExtentReports extent; // Objet unique

    // Retourne une seule instance d’ExtentReports
    public static ExtentReports getInstance() {
        if (extent == null) {
            // Définition du chemin du rapport HTML
            String reportPath = System.getProperty("user.dir") + "/reports/TestReport.html";

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
        }
        return extent;
    }
}
