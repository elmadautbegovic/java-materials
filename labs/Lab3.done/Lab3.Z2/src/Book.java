import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
public class Book implements Comparable<Book> {
    String title;
    int yearpublished;
    String author;

    Book(String title, int yearpublished, String author) {
        this.title = title;
        this.yearpublished = yearpublished;
        this.author = author;
    }
public boolean equals (Book a, Book b) {
    return a.title.equals(b.title) && a.author.equals(b.author);
}

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title); // compare by title
    }

@Override
    public String toString() {
        return title + ", " + yearpublished + ", " + author;
}

public static void sortbyyear (List <Book> books) {
        Collections.sort(books, Comparator.comparingInt(b -> b.yearpublished));
}
}
