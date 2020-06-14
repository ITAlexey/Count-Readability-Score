package readability;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Text.FILE_NAME = args[0];
            try (FileReader reader = new FileReader(Text.FILE_NAME)) {
                Text text = new Text(reader);
                System.out.println(text.toString());
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the score you want to calculate: (ARI, FK, SMOG, CL, all): ");
                displayReadabilityAge(text, scanner.next());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayReadabilityAge(Text text, String option) {
        if (option.equals("all")) {
            double averageScore;
            int score = 0;
            for (Methods method : Methods.values()) {
                ReadabilityIndex initializeMethod = method.InitializeMethod(text);
                Methods.displayResultOfMethod(initializeMethod);
                score += initializeMethod.getYears();
            }
            averageScore = Math.round(score / (double) Methods.values().length);
            System.out.println("\nThis text should be understood in average by " + averageScore + " year olds.");
        } else {
            for (Methods method : Methods.values()) {
                if (option.equals(method.toString())) {
                    Methods.displayResultOfMethod(method.InitializeMethod(text));
                }
            }
        }
    }
}
