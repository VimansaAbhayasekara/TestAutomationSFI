package com.sfimonitor.utils;

public class CustomLogger {

    // Simple symbols without colors for maximum compatibility
    public static void logSuccess(String message) {
        System.out.println("✓ " + message);
    }

    public static void logError(String message) {
        System.out.println("✗ " + message);
    }

    public static void logInfo(String message) {
        System.out.println("ℹ " + message);
    }

    public static void logWarning(String message) {
        System.out.println("⚠ " + message);
    }

    public static void logStep(String message) {
        System.out.println("→ " + message);
    }

    // Windows CMD.exe compatible colored output (if running in supported terminal)
    public static void logSuccessColored(String message) {
        if (isColorSupported()) {
            System.out.println("\u001B[32m✓ " + message + "\u001B[0m");
        } else {
            logSuccess(message);
        }
    }

    public static void logErrorColored(String message) {
        if (isColorSupported()) {
            System.out.println("\u001B[31m✗ " + message + "\u001B[0m");
        } else {
            logError(message);
        }
    }

    private static boolean isColorSupported() {
        // Check if we're likely in a terminal that supports colors
        String term = System.getenv("TERM");
        String ansicon = System.getenv("ANSICON");
        return term != null && (term.contains("xterm") || term.contains("color"))
                || ansicon != null
                || System.console() != null;
    }
}