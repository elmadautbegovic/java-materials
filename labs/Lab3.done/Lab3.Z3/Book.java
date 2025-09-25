import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Book implements Comparable<Book> {
    String title;
    int yearpublished;
    String author;
    String genre;

    Book(String title, int yearpublished, String author, String genre) {
        this.title = title;
        this.yearpublished = yearpublished;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public int getYearPublished() {
        return yearpublished;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int compareTo(Book other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return title + ", " + yearpublished + ", " + author;
    }

    public static void sortByYear(ArrayList<Book> books) {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b1.getYearPublished(), b2.getYearPublished());
            }
        });
    }

    public static void SortBooks (ArrayList<Book> books, boolean SortByTitleFirst, boolean SortByGenreFirst) {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                if (SortByTitleFirst) {
                    int titleComparison = b1.title.compareTo(b2.title);
                    if (titleComparison != 0) return titleComparison;
                    return Integer.compare(b1.yearpublished, b2.yearpublished);
                } else if (SortByGenreFirst) {
                    int genreComparison = b1.genre.compareTo(b2.genre);
                    if (genreComparison != 0) return genreComparison;
                    return Integer.compare(b1.yearpublished, b2.yearpublished);
                } else {
                    return Integer.compare(b1.yearpublished, b2.yearpublished);
                }
            }
        });
    }

}
