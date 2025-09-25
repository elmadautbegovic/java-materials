import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Tvrdjava", 1111, "M. Selimovic", "roman"));
        books.add(new Book("Prokleta avlija", 2222, "I. Andirc", "roman"));
        books.add(new Book("Ruza", 3333, "N. B. Hubijar", "roman"));
        books.add(new Book("Cekajuci Tahira", 4444, "N. B. Hubijar", "roman"));
        books.add(new Book("Ne cekam vise Tahira", 5555, "N. B. Hubijar", "roman"));
        books.add(new Book("Prokleta avlija", 1010, "I. Andirc", "roman"));

        // Collections.sort(books);

       /* System.out.println("books: ");
        for (Book book : books) {
            System.out.println(book);
        }

         Book.sortByYear(books);
        System.out.println("books sorted by year: ");
        for (Book book : books) {
            System.out.println(book);
        }

       Book a = new Book("Prokleta avlija", 2222, "I. Andirc", "roman");
        Book b = new Book ("Prokleta avlija", 1010, "I. Andric", "roman");
    System.out.println("Book a and book b are equal: " + a.equals(b));
    } */


        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose sorting");
        System.out.println("1 - sort by title then by year");
        System.out.println("2 - sort by genre then by year");
        System.out.println("3 - sort by year only");
        int choice = scanner.nextInt();

        boolean sortByTitleFirst = (choice == 1);
        boolean sortByGenreFirst = (choice == 2);

        Book.SortBooks(books, sortByTitleFirst, sortByGenreFirst);

        System.out.println("Sorted books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}



