import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList <>();
        books.add(new Book ("Tvrdjava",1111 , "M. Selimovic"));
        books.add(new Book("Prokleta avlija", 2222, "I. Andirc"));
        books.add (new Book("Ruza",3333, "N. B. Hubijar"));
        books.add(new Book("Cekajuci Tahira",4444, "N. B. Hubijar"));
        books.add(new Book("Ne cekam vise Tahira", 5555, "N. B. Hubijar"));
        books.add(new Book("Prokleta avlija", 1010, "I. Andirc"));

        Collections.sort(books);

        System.out.println("books: ");
        for (Book book : books) {
            System.out.println(book);
        }

        Book.sortbyyear(books);
        System.out.println("books sorted by year: ");
        for (Book book : books) {
            System.out.println(book);
        }

        Book a = new Book("Prokleta avlija", 2222, "I. Andirc");
        Book b = new Book ("Prokleta avlija", 1010, "I. Andric");
    System.out.println("Book a and book b are equal" + a.equals(b));
    }
}