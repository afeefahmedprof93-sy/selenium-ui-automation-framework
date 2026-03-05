package com.orange.automation.utils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AllureReport {
    public static void generateAllureReport() {
        try {
            // Timestamped folder to avoid overwriting previous reports
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String reportFolder = "allure-results/allure-report-" + timestamp;

            // Generate Allure report
            ProcessBuilder generateBuilder = new ProcessBuilder(
                    "allure", "generate", "allure-results", "-o", reportFolder, "--clean"
            );
            generateBuilder.inheritIO(); // show output in console
            Process generateProcess = generateBuilder.start();
            int exitCode = generateProcess.waitFor();
            
            if (exitCode == 0) {
                System.out.println("Allure report generated: " + reportFolder);

                // Open report based on OS
                String os = System.getProperty("os.name").toLowerCase();
                ProcessBuilder openBuilder;

                if (os.contains("win")) {
                    // Windows
                    openBuilder = new ProcessBuilder("cmd", "/c", "allure", "open", reportFolder );
                } else if (os.contains("mac")) {
                    // Mac
                    openBuilder = new ProcessBuilder("allure", "open", reportFolder );
                } else {
                    // Linux (assumes xdg-open is installed)
                    openBuilder = new ProcessBuilder("xdg-open", reportFolder + "/index.html");
                }

                openBuilder.inheritIO();
                openBuilder.start();

            } else {
                System.err.println("Failed to generate Allure report. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
