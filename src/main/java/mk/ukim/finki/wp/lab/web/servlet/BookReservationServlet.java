package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "BookReservationServlet", value = "/bookReservation" )
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;

    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange iWebExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);

        WebContext webContext = new WebContext(iWebExchange);

        String selectedBook = req.getParameter("selectedBook");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        Integer numCopies = Integer.valueOf(req.getParameter("numCopies"));

        webContext.setVariable("selectedBook", selectedBook);
        webContext.setVariable("readerName", readerName);
        webContext.setVariable("readerAddress", readerAddress);
        webContext.setVariable("numCopies", numCopies);
        webContext.setVariable("readerIpAddress", req.getRemoteAddr());


        getServletContext().setAttribute("selectedBook", selectedBook);
        getServletContext().setAttribute("readerName", readerName);
        getServletContext().setAttribute("readerAddress", readerAddress);
        getServletContext().setAttribute("numCopies", numCopies);
        getServletContext().setAttribute("readerIpAddress", req.getRemoteAddr());

        springTemplateEngine.process("reservationConfirmation.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedBook = (String) getServletContext().getAttribute("selectedBook");
        String readerName = (String) getServletContext().getAttribute("readerName");
        String readerAddress = (String) getServletContext().getAttribute("readerAddress");
        Integer numCopies = (Integer) getServletContext().getAttribute("numCopies");
        this.bookReservationService.placeReservation(selectedBook, readerName, readerAddress, numCopies);
        resp.sendRedirect("/");
    }
}
