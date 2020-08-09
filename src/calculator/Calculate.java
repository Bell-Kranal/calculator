package calculator;

import exception.ErrorInputLine;

public class Calculate {
    private static double[] arrayNumbers = new double[9999];
    private static char[] arrayOperations = new char[9999];
    private static int indexArrayNumber;
    private static int indexArrayOperations;

    public static void calculate(String inputString) throws ErrorInputLine {
        readInputString(inputString);
        priorityOperations();
        notPriorityOperations();
        writeResult();
    }

    private static void readInputString(String inputString) throws ErrorInputLine {
        int stringSize = inputString.length() - 2;
        for(int i = 0; i < stringSize; i++) {
            char c = inputString.charAt(i);
            StringBuilder number = new StringBuilder();
            while ((c >= '0' && c <= '9') || c == '.') {
                number.append(c);
                c = inputString.charAt(++i);
            }
            arrayNumbers[indexArrayNumber++] = Double.parseDouble(String.valueOf(number));
            if(indexArrayOperations - 1 != -1)
                if(arrayNumbers[indexArrayNumber - 1] == 0 && arrayOperations[indexArrayOperations - 1] == '/')
                    throw new ErrorInputLine();
            c = inputString.charAt(i);
            if(c == '?')
                break;
            arrayOperations[indexArrayOperations++] = c;
        }
    }

    private static void priorityOperations() {
        for(int i = 0; i < indexArrayOperations; i++) {
            if(arrayOperations[i] == '*') {
                arrayNumbers[i + 1] *= arrayNumbers[i];
                arrayNumbers[i] = -1;
                arrayOperations[i] = '&';
                continue;
            }
            if(arrayOperations[i] == '/') {
                arrayNumbers[i + 1] = arrayNumbers[i] / arrayNumbers[i + 1];
                arrayNumbers[i] = -1;
                arrayOperations[i] = '&';
            }
        }
    }

    private static void notPriorityOperations() {
        for(int i = 0; i < indexArrayOperations; i++) {
            if(arrayOperations[i] == '+') {
                double number = arrayNumbers[i++];
                arrayOperations[i - 1] = '&';
                arrayNumbers[i - 1] = -1;
                while(arrayNumbers[i] == -1)
                    i++;
                arrayNumbers[i] += number;
                i--;
                continue;
            }
            if(arrayOperations[i] == '-') {
                double number = arrayNumbers[i++];
                arrayOperations[i - 1] = '&';
                arrayNumbers[i - 1] = -1;
                while(arrayNumbers[i] == -1)
                    i++;
                arrayNumbers[i] = number - arrayNumbers[i];
                i--;
            }
        }
    }

    private static void writeResult() {
        System.out.println(arrayNumbers[indexArrayNumber - 1]);
    }
}
