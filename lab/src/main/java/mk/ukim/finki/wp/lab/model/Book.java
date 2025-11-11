package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Book {
    private static Long ID_AUTO_INCREMENT = 1L;
    private final Long id;
    private String title;
    private String genre;
    private double averageRating;
    private Author author;

    public Book(String title, String genre, double averageRating, Author author) {
        this.id = ID_AUTO_INCREMENT++;
        this.title = title;
        this.genre = genre;
        this.averageRating = averageRating;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Genre: " + genre +
                ", Rating: " + averageRating +
                ", Author: " + author.toString();
    }
}
