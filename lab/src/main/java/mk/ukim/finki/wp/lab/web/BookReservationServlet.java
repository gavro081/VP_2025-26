package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.impl.BookReservationServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "Book Reservation Servlet", urlPatterns = "/bookReservation")
public class BookReservationServlet extends HttpServlet {
    private final BookReservationServiceImpl bookReservationService;
    private final BookServiceImpl bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationServiceImpl bookReservationService,
                                  BookServiceImpl bookService,
                                  SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        String bookTitle = req.getParameter("selectedBook");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        String numCopies = req.getParameter("numCopies");

        context.setVariable("readerName", readerName);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("readerAddress", readerAddress);
        context.setVariable("numCopies", numCopies);

        context.setVariable("ipAddress", req.getRemoteAddr());

        Book book = bookService.searchBooks(bookTitle, 0.0).get(0);

        bookReservationService.placeReservation(book, readerName, readerAddress, Integer.parseInt(numCopies));
        springTemplateEngine.process("reservationConfirmation.html", context, resp.getWriter());
    }
}
