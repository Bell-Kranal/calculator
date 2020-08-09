package calculator;

import exception.ErrorInputLine;

import java.util.Scanner;

import static calculator.Calculate.calculate;

public class PullsMethod {
    public static void pull() throws ErrorInputLine {
        String inputString = new Scanner(System.in).nextLine();

        inputString = inputString.replaceAll(" ", "");
        inputString = inputString + "??";

        CheckSymbols.checkSymbols(inputString);
        calculate(inputString);
    }
}
