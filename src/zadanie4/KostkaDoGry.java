package zadanie4;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


///////////////////////////
//assumption that user always gives only one letter 'D' or 'd' as input and only one symbol '+' or '-"
///////////////////////////

public class KostkaDoGry {
    public static void main(String[] args) {

        //variable declaration
        int[] intArr;
        String inputStr = getString();
        StringTokenizer stInput = new StringTokenizer(inputStr, "Dd+-");
        int multiplier = 0;
        boolean intAtFirst = false;
        int[] intArrWithStrLenght;

        //trying to split and convert it to int, if it fails code will break
        intArr = new int[stInput.countTokens()];
        try {
            int i = 0;
            while (stInput.hasMoreTokens()) {
                intArr[i] = Integer.valueOf(stInput.nextToken());
                i++;
            }
        } catch (NumberFormatException e) {
            System.out.println("Niepoprawny kod kostki!");
        }

        //checking if first char is digit
        if (checkIfInt(inputStr.charAt(0)) > 0) {
            multiplier = intArr[0];
            intAtFirst = true;
        } else if (checkIfInt(inputStr.charAt(0)) < 0) {
            multiplier = 1;
        } else if (checkIfInt(inputStr.charAt(0)) == 0) {
            System.out.println("Pierwszym znakiem podanym jest 0! Koncze program!");
            return;
        }

        //checking what sign is in the string + or -
        if (intAtFirst && cubeType(intArr[1]) != -1) {
            if (intArr.length == 2) {
                System.out.println("Wynik kodu dla: " + inputStr + " wynosi: " + diceCount(multiplier, cubeType(intArr[1])));
            } else if (intArr.length == 3) {
                int lastStrLenght = String.valueOf(intArr[2]).length();
                int inputStrLenght = inputStr.length();
                int sum = 0;

                if (Character.valueOf(inputStr.charAt(inputStrLenght - lastStrLenght - 1)) == 43) {//43='+'
                    sum = diceCount(multiplier, cubeType(intArr[1])) + intArr[2];
                } else if (Character.valueOf(inputStr.charAt(inputStrLenght - lastStrLenght - 1)) == 45) {//45='-'
                    sum = diceCount(multiplier, cubeType(intArr[1])) - intArr[2];
                }
                System.out.println("Wynik kodu dla: " + inputStr + " wynosi: " + sum);

            } else if (intArr.length > 3) {
                System.out.println("za duzo parametrow wejsciowych!");
                return;
            }
        } else if (!intAtFirst && cubeType(intArr[0]) != -1) {
            if (intArr.length == 1) {
                System.out.println("Wynik kodu dla: " + inputStr + " wynosi: " + diceCount(multiplier, cubeType(intArr[0])));
            } else if (intArr.length == 2) {
                int lastStrLenght = String.valueOf(intArr[1]).length();
                int inputStrLenght = inputStr.length();
                int sum = 0;

                if ((inputStr.charAt(inputStrLenght - lastStrLenght - 1)) == '+') {//43='+'
                    sum = diceCount(multiplier, cubeType(intArr[0])) + intArr[1];
                } else if ((inputStr.charAt(inputStrLenght - lastStrLenght - 1)) == '-') {//45='-'
                    sum = diceCount(multiplier, cubeType(intArr[0])) - intArr[1];
                }
                System.out.println("Wynik kodu dla: " + inputStr + " wynosi: " + sum);

            } else if (intArr.length > 2) {
                System.out.println("za duzo parametrow wejsciowych!");
                return;
            }

        } else if (intAtFirst && cubeType(intArr[1]) == -1) {
            System.out.println("NIepoprawny typ kostki!");
            return;
        }

    }

    //getting code from the user
    static String getString() {
        //definition of variables
        Scanner scan = new Scanner(System.in);
        String inCode;
        //definition of a method
        System.out.print("Podaj kod kostki w formacie 'xDy+z': ");
        while (!scan.hasNextLine()) {
            System.out.print("nie podales kodu kostki! Podaj kod kostki: ");
            scan.next();
        }
        inCode = scan.nextLine();
        return inCode;
    }

    //checking if the char is digit
    static int checkIfInt(char inChar) {
        int result = 0;
        try {
            result = Integer.parseInt(Character.toString(inChar));
        } catch (Exception e) {
            result = -1;
            //e.printStackTrace();
        }
        return result;
    }

    //checking type of the cube, if badly defined returns -1
    static int cubeType(int inInt) {
        if (inInt != 3 && inInt != 4 && inInt != 6 && inInt != 8 && inInt != 10 && inInt != 20 && inInt != 100) {
            System.out.println("Typ kostki nie odpowiedni!");
            return -1;
        } else {
            return inInt;
        }
    }

    //generating random number from range - depending on the cube size
    static int randomNumber(int inInt) {
        Random rand = new Random();
        //rand.nextInt((max - min) + 1) + min;
        return (rand.nextInt((inInt)) + 1);
    }

    //counting points from multiplier and rolled dice
    static int diceCount(int multiplier, int cubeType) {
        int tempInt = 0;
        for (int i = 0; i < multiplier; i++) {
            tempInt += randomNumber(cubeType);
        }
        return tempInt;
    }
}