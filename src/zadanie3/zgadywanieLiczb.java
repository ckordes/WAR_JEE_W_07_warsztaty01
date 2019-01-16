package zadanie3;

import java.util.Random;
import java.util.Scanner;

public class zgadywanieLiczb {
    public static void main(String[] args) {
        int min = 0;
        int max = 1000;
        Random random = new Random();
        int temp;
        int tempInput;
        Scanner scan = new Scanner(System.in);
        boolean bCheck = false;

        System.out.println("Pomysl liczbe od 0-1000 a ja ja zgadne max w 10 probach!");
        for (int i = 0; i < 10; i++) {
            temp = ((max - min) / 2) + min;
//            temp = random.nextInt((max - min) / 2) + min;
            System.out.println("Zgaduje: " + temp);
            System.out.println("1: za duzo!");
            System.out.println("2: za malo!");
            System.out.println("3: zgadles!");

            do {
                bCheck = false;
                while (!scan.hasNextInt()) {
                    System.out.println("Podana liczba nie jest cyfra!");
                    scan.next();
                }
                tempInput = scan.nextInt();
                if (tempInput < 1 || tempInput > 3) {
                    System.out.println("Podana liczba jest nieprawidlowa!");
                    bCheck = true;
                }
            } while (bCheck != false);
            switch (tempInput) {
                case 1:
                    max = temp - 1;
                    break;
                case 2:
                    min = temp + 1;
                    break;
            }
            if (tempInput == 3) {
                System.out.println("Wygralem!");
                break;
            }
        }
    }

}
/*
Gra w zgadywanie liczb 2
Odwróćmy teraz sytuację z warsztatu "Gra w zgadywanie liczb": to użytkownik pomyśli sobie liczbę z zakresu 1-1000,
a komputer będzie zgadywał i zrobi to maksymalnie w 10 ruchach (pod warunkiem, że gracz nie będzie oszukiwał).

Zadaniem gracza będzie udzielanie odpowiedzi "więcej", "mniej", "trafiłeś".

Na następnym slajdzie znajduje się schemat blokowy algorytmu.

Dostępny jest także pod adresem:

https://gist.github.com/arek-jozwiak-coderslab/4783d45e75a71793a123673cc0998ae3

Zaimplementuj go w Javie.
 */