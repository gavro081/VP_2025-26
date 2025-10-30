package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
@AllArgsConstructor
public class Book {
    private String title;
    private String genre;
    private double averageRating;

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Rating: " + averageRating;
    }
}
