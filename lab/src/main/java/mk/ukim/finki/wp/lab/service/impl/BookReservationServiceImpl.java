package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.repository.impl.InMemoryBookReservationRepository;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Service;

@Service
public class BookReservationServiceImpl implements BookReservationService {
    private final InMemoryBookReservationRepository inMemoryBookReservationRepository;

    public BookReservationServiceImpl(InMemoryBookReservationRepository inMemoryBookReservationRepository) {
        this.inMemoryBookReservationRepository = inMemoryBookReservationRepository;
    }

    @Override
    public BookReservation placeReservation(String bookTitle, String readerName, String readerAddress, int numberOfCopies) {
        return inMemoryBookReservationRepository.save(new BookReservation(bookTitle, readerName, readerAddress, (long) numberOfCopies));
    }
}
