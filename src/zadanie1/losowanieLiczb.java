package zadanie1;

import java.util.Random;
import java.util.Scanner;

public class losowanieLiczb {

    public static void main(String[] args) {
        Random random=new Random();
        int randNumb;
        randNumb = random.nextInt((99) + 1) + 1;
        //rand.nextInt((max - min) + 1) + min;
        System.out.println(randNumb);
        int userInt=0;
        System.out.println("Zgadnij liczbe");
        do {
            userInt = funcGetNumber();
            if (userInt<randNumb){
                System.out.println("Za malo");
            }else if(userInt>randNumb){
                System.out.println("Za duzo");
            }
        }while(userInt!=randNumb);
        System.out.println("Zgadles!");


    }
    static int funcGetNumber(){
        Scanner scan = new Scanner(System.in);
        while(!scan.hasNextInt()){
            System.out.println("To nie jest liczba");
            scan.next();
        }
        return scan.nextInt();
    }
}


/*
Gra w zgadywanie liczb
Napisz prostą grę w zgadywanie liczb. Komputer ma wylosować liczbę w zakresie od 1 do 100. Następnie:

wypisać: "Zgadnij liczbę" i pobrać liczbę z klawiatury;
sprawdzić, czy wprowadzony napis, to rzeczywiście liczba i w razie błędu wyświetlić komunikat: "To nie jest liczba",
po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest mniejsza niż wylosowana, wyświetlić komunikat: "Za mało!",
po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest większa niż wylosowana, wyświetlić komunikat: "Za dużo!",
po czym wrócić do pkt. 1;
jeśli liczba podana przez użytkownika jest równa wylosowanej, wyświetlić komunikat: "Zgadłeś!",
po czym zakończyć działanie programu.
 */