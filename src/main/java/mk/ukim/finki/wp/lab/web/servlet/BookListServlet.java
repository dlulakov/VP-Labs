package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookListServlet", value = "")
public class BookListServlet extends HttpServlet {

    private final BookService bookService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookListServlet(BookService bookService, SpringTemplateEngine springTemplateEngine) {
        this.bookService = bookService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext webContext = new WebContext(iWebExchange);

        String searchTitle = req.getParameter("searchTitle");
        String searchRating = req.getParameter("searchRating");

        List<Book> books;
        if(searchRating != null && searchTitle != null && !searchRating.isEmpty() && !searchTitle.isEmpty()){
            Double searchRatingDouble = Double.parseDouble(searchRating);
            books = bookService.searchBooks(searchTitle, searchRatingDouble);
        }else {
            books = bookService.listAll();
        }

        webContext.setVariable("books", books);

        springTemplateEngine.process("listBooks.html", webContext,resp.getWriter());
    }

}
