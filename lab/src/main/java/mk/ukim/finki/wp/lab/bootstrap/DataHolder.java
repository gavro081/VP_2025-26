package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<BookReservation> reservations = new ArrayList<>();

    @PostConstruct
    public void init(){
        Random random = new Random();
        String []genres = new String[]{"comedy", "romance", "drama"};
        String []names = new String[]{"name", "kniga", "book"};
        for (int i = 0; i < 10; i++) {
            int nameIndex = random.nextInt(3);
            int genreIndex = random.nextInt(3);
            double rand = random.nextDouble() * 10;
            double rating = Math.round(rand * 100.0) / 100.0;
            Book book = new Book(names[nameIndex] + i, genres[genreIndex], rating);
            books.add(book);
        }
    }
}
