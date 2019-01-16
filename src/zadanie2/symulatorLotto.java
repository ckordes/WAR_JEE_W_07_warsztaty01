package zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class symulatorLotto {
    public static void main(String[] args) {
        int[] numArray = wylosowaneLiczby();
        int[] numPodane;
        for (int numb : numArray) {
            System.out.print(numb + " ");
        }
        System.out.println();
        numPodane = podaneLiczby();
        Arrays.sort(numPodane);
        for (int numb : numPodane) {
            System.out.print(numb + " ");
        }
        System.out.println();
        System.out.println("Trafiono "+ileTrafien(numArray,numPodane)+" liczb.");
    }

    //metoda do generowania liczby losowej
    static int randNumber() {
        Random rand = new Random();
        int randInt = 0;
        //rand.nextInt((max - min) + 1) + min;
        randInt = rand.nextInt((49 - 1) + 1) + 1;
        return randInt;
    }

    // wylosowane liczby
    static int[] wylosowaneLiczby() {
        int randInt = 0;
        int[] intArr = new int[6];
        boolean exitLoop = false;

        for (int i = 0; i < 6; i++) {
            do {
                randInt = randNumber();
                exitLoop = false;
                for (int j = 0; j < 6; j++) {
                    if ((i != j) && (randInt == intArr[j])) {
                            exitLoop = true;
                            break;
                        }
                }
                if (!exitLoop) {
                    intArr[i] = randInt;
                    exitLoop = true;
                }
            } while (!exitLoop);
        }
        return intArr;

    }

    //pobieram int
    static int getNumb(int input) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj " + (input + 1) + " liczbe z zakresu 1-49: ");
        while (!scan.hasNextInt()) {
            System.out.print("Podaj liczbe calkowita z zakresu 1-49: ");
            scan.next();
        }
        return scan.nextInt();
    }

    //tablica z podanymi przez uzytkownika liczbami
    static int[] podaneLiczby() {
        int[] podaneArr = new int[6];
        int tempNumb = 0;
        boolean bCheck1 = false;
        boolean bCheck2 = false;
        boolean bCheck3 = false;

        for (int i = 0; i < 6; i++) {
            do {
                bCheck1 = false;
                bCheck2 = false;
                bCheck3 = false;
                tempNumb = getNumb(i);
                if (tempNumb < 1 || tempNumb > 49) {
                    System.out.println("Podana liczba jest spoza zakresu 1-49!");
                    bCheck1 = true;
                }
                for (int j = 0; j <= i; j++) {
                    if (tempNumb == podaneArr[j]) {
                        bCheck2 = true;
                        System.out.println("Ta liczba juz zostala podana!");
                    }
                }

            } while (bCheck1 == true || bCheck2 == true);
            podaneArr[i] = tempNumb;
        }
        return podaneArr;
    }

    //metoda sprawdzajaca czy to co uzytkownik podal znajduje sie w wylosowanych liczbach
    static int ileTrafien(int[] inWylosowane, int[] inPodane) {
        int liczbaTrafien = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (inWylosowane[i] == inPodane[j]) {
                    liczbaTrafien += 1;
                }
            }
        }
        return liczbaTrafien;
    }
}
/*
Symulator LOTTO
Jak wszystkim wiadomo, LOTTO to gra liczbowa polegająca na losowaniu 6 liczb z zakresu od 1 do 49.
Zadaniem gracza jest poprawne wytypowanie losowanych liczb. Nagradzane jest trafienie 3, 4, 5 lub 6 poprawnych liczb.

Napisz program, który:

zapyta o typowane liczby, przy okazji sprawdzi następujące warunki:
czy wprowadzony ciąg znaków jest poprawną liczbą,
czy użytkownik nie wpisał tej liczby już poprzednio,
czy liczba należy do zakresu 1-49,
po wprowadzeniu 6 liczb, posortuje je rosnąco i wyświetli na ekranie,
wylosuje 6 liczb z zakresu i wyświetli je na ekranie,
poinformuje gracza, czy trafił przynajmniej "trójkę".
 */