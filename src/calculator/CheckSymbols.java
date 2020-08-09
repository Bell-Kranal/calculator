package calculator;

import exception.ErrorInputLine;

public class CheckSymbols {
    public static void checkSymbols(String inputString) throws ErrorInputLine {
        int stringSize = inputString.length() - 2;
        for(int i = 0; i < stringSize; i++) {
            if(checkSymbol(inputString.charAt(i), inputString.charAt(i + 1)))
                throw new ErrorInputLine();
        }
        char firstSymbol = inputString.charAt(0);
        char lastSymbol = inputString.charAt(inputString.length() - 3);
        if(checkMathOperations(firstSymbol) || checkMathOperations(lastSymbol))
            throw new ErrorInputLine();
    }

    private static boolean checkSymbol(char c, char nextSymbol) {
        return (c != '*' && c != '/' && c != '+' && c != '-' && c != '.' && c != '(' && c != ')' && !(c >= '0' && c <= '9')) ||
                (c == nextSymbol && checkMathOperations(nextSymbol));
    }

    private static boolean checkMathOperations(char c) {
        return c == '-' || c == '+' || c == '/' || c == '*';
    }
}
