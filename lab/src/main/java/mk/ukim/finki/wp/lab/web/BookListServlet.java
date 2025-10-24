package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.impl.BookServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Book List Servlet", urlPatterns = "" )
public class BookListServlet extends HttpServlet {
    private final BookServiceImpl bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookServiceImpl bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("books", bookService.listAll());
        context.setVariable("matchedBooks", List.of());
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);
        String text = req.getParameter("matchedText");
        if (text == null) text = "";
        double rating = 0.0;
        try {
            rating = Double.parseDouble(req.getParameter("matchedRating"));
        } catch (NumberFormatException e) {
            rating = 0.0;
        }
        context.setVariable("books", bookService.listAll());
        context.setVariable("matchedBooks", bookService.searchBooks(text, rating));
        springTemplateEngine.process("listBooks.html", context, resp.getWriter());
    }




}
