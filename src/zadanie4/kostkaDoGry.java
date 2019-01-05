package zadanie4;

import java.util.Random;
import java.util.Scanner;

public class kostkaDoGry {
    public static void main(String[] args) {
        //definition of variables
        String inCode;
        char[] inCodeChar;
        int multiplier = 0;
        int cubeType = 0;
        int charLength = 0;
        int sum = 0;
        int temp = 0;
        String strValue = "";


        //definition of a method
        //checking if on first/second position 'D' letter exists
        do {
            inCode = getString();
            inCodeChar = inCode.toCharArray();
        } while (inCodeChar[0] != 'D' && inCodeChar[1] != 'D');
        charLength = inCodeChar.length;
        //assumption no multiplier!
        try {
            multiplier = Integer.parseInt(Character.toString(inCodeChar[0]));
        } catch (Exception e) {
            multiplier = 1;
            //e.printStackTrace();
        }
        // assumption that multiplier is not greater than 9 and if equal 0 than multiplier = 1
        if (multiplier == 0) {
            multiplier = 1;
        }
        if (multiplier == 1) {
            cubeType = cubeType(inCodeChar, 1);
        } else {
            cubeType = cubeType(inCodeChar, 2);
        }
        //check if the cube type is correct one (D3,D4,D6,D8,D10,D20,D100)
        if (cubeType != 3 || cubeType != 4 || cubeType != 6 || cubeType != 8 || cubeType != 10 || cubeType != 20 || cubeType != 100) {
            System.out.println("Typ kostki nie odpowiedni!");
            return;
        }

        for (int i = 0; i < multiplier; i++) {
            sum += returnCode(cubeType);
        }
        //check sum display
        //System.out.println("sum: " + sum);

        // '+' ascii table value '43', '-' ascii table value '45'
        // adding all the digits after the symbol +/-
        if (multiplier == 1) {
            if (cubeType < 10 && charLength > 2) {
                for (int i = 3; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[2]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[2]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            } else if ((cubeType == 10 || cubeType == 20) && charLength > 3) {
                for (int i = 4; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[3]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[3]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            } else if (cubeType == 100 && charLength > 4) {
                for (int i = 5; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[4]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[4]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            }
        } else if (multiplier > 1) {
            if (cubeType < 10 && charLength > 3) {
                for (int i = 4; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[3]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[3]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            } else if ((cubeType == 10 || cubeType == 20) && charLength > 4) {
                for (int i = 5; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[4]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[4]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            } else if (cubeType == 100 && charLength > 5) {
                for (int i = 6; i < inCodeChar.length; i++) {
                    strValue = strValue.concat(Character.toString(inCodeChar[i]));
                }
                if (Character.valueOf(inCodeChar[5]) == 43) {//43='+'
                    sum += Integer.parseInt(strValue);
                } else if (Character.valueOf(inCodeChar[5]) == 45) {//43='+'
                    sum -= Integer.parseInt(strValue);
                }
            }
        }

        System.out.println("Wynik z podanego kodu kostki wynosi: " + sum);
//        //check displays
//        System.out.println("multiplier: " + multiplier);
//        System.out.println("incode: " + inCode);
//        System.out.println("sum: " + sum);
//        System.out.println("cubetype: " + cubeType);
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

    //generating random number from range - depending on the cube size
    static int returnCode(int inInt) {
        Random rand = new Random();
        //rand.nextInt((max - min) + 1) + min;
        return (rand.nextInt((inInt)) + 1);
    }

    //checking cube type
    static int cubeType(char[] inCodeChar, int startPoint) {
        int cubeType = 3;               //if value not digit by default will be 3??
        int tempCount = 0;
        int temp;
        for (int i = startPoint; i < inCodeChar.length; i++) {
            temp = checkIfInt(inCodeChar[i]);
            if (temp == -1) {
                return cubeType;
            }
            if (temp == 2) {
                return 20;
            } else if (temp == 1 || temp == 0) {
                tempCount += i;
                if (tempCount == 3 || tempCount == 5) {
                    cubeType = 10;
                } else if (tempCount == 6 || tempCount == 9) {
                    cubeType = 100;
                }
            } else {
                cubeType = temp;
                return temp;
            }
        }
        return cubeType;
    }

}
/*
Napisz funkcję, która:

przyjmie w parametrze taki kod w postaci String,
rozpozna wszystkie dane wejściowe:
rodzaj kostki,
liczbę rzutów,
modyfikator,
wykona symulację rzutów i zwróci wynik.
Typy kostek występujące w grach:

D3, D4, D6, D8, D10, D12, D20, D100.
 */