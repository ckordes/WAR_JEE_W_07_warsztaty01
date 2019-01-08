package zadanie5;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class wyszukajNajpopulSlowa {
    public static void main(String[] args) {

        String[] strToBeRemoved = {"ponieważ", "oraz", "wcale", "chociaż", "jeżeli", "jest", "jeśli", "tylko"};
        String url = "http://www.onet.pl/";


        //method body
        gettingTagsFromWebsite(url);
        filteringWordsFromTags(strToBeRemoved);

        System.out.println("Files are ready to be processed.");

    }

    // method that recieves as input 'url' and saves words to an file
    public static void gettingTagsFromWebsite(String url) {
        //variable declaration
        Connection connect = Jsoup.connect(url);
        StringTokenizer strToken;
        String tempStr;
        ArrayList<String> outList = new ArrayList<>();
        Path pathSaveToFile = Paths.get("popular_words.txt");

        //method body
        try {
            Document document = connect.get();
            Elements links = document.select("span.title");
            for (Element elem : links) {
                strToken = new StringTokenizer(elem.text(), ",.?! \": ");
                while (strToken.hasMoreTokens()) {
                    tempStr = strToken.nextToken();
                    if (tempStr.length() > 3) {
                        outList.add(tempStr.toLowerCase());
                    }
                }
//                System.out.println(elem.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Files.write(pathSaveToFile, outList);
        } catch (IOException ex) {
            System.out.println("Cannot save to file!");
        }
    }


    // method that recieves as input array of words to be excluded and takes the file and clean data and saves it to a file
    public static void filteringWordsFromTags(String[] arrExcludedWords) {
        // declaration of variables
        Path fileToBeOpened = Paths.get("popular_words.txt");
        Path fileToBeSaved = Paths.get("filtered_popular_words.txt");
        ArrayList<String> inList = new ArrayList<>();

        //body of the method
        try {
            for (String line : Files.readAllLines(fileToBeOpened)) {
                inList.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the file!");
        }

        //checking if in array list any of the words from the 'forbidden' list exists and removes such word from aList
        for (String str : arrExcludedWords) {
            while (inList.contains(str)) {
                inList.remove(str);
            }
        }
        //cleaned aList is being saved to a file
        try {
            Files.write(fileToBeSaved, inList);
        } catch (IOException ex) {
            System.out.println("Could not save the file!");
        }
    }
}

/*
Wyszukiwarka najpopularniejszych słów
Zaimportuj do projektu bibliotekę jsoup, możesz ją pobrać z adresu: https://jsoup.org/download
Wyszukaj w popularnych serwisach internetowych nagłówków artykułów, a następnie zapisz pojedyncze słowa w nich
występujące do pliku o nazwie popular_words.txt. Przykład pobrania tytułów z tagu html span
z atrybutem class o wartości title:
Connection connect = Jsoup.connect("http://www.onet.pl/");
try {
    Document document = connect.get();
    Elements links = document.select("span.title");
    for (Element elem : links) {
        System.out.println(elem.text());
    }
} catch (IOException e) {
    e.printStackTrace();
}
Wywołaj pobieranie dla wybranych serwisów internetowych.
Pomiń wszystkie elementy krótsze niż 3-znakowe.
Utwórz tablicę elementów wykluczonych np. oraz, ponieważ
Wczytaj utworzony plik popular_words.txt i na jego podstawie utwórz plik filtered_popular_words.txt,
który zawierać będzie wszystkie znalezione słowa, pomijając słowa wykluczone.
 */