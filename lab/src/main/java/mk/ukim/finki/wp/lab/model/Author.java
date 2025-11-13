package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Author {
    private Long id;
    private String name;
    private String surname;
    private String country;
    private String biography;
    private int likes;

    public Author(Long id, String name, String surname, String country, String biography) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.biography = biography;
        this.likes = 0;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
