package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Controller
@RequestMapping("/book-reservation")
public class BookReservationController {
    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;
    }

    @GetMapping
    public String getConfirmation(Model model, @RequestParam String selectedBook, @RequestParam String readerName, @RequestParam String readerAddress, @RequestParam Integer numCopies, @RequestParam Long bookId, HttpServletRequest request, HttpSession session) {
        model.addAttribute("selectedBook", selectedBook);
        model.addAttribute("readerName", readerName);
        model.addAttribute("readerAddress", readerAddress);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("readerIpAddress", request.getRemoteAddr());
        model.addAttribute("bookId", bookId);

        session.setAttribute("selectedBook", selectedBook);
        session.setAttribute("readerName", readerName);
        session.setAttribute("readerAddress", readerAddress);
        session.setAttribute("numCopies", numCopies);
        session.setAttribute("readerIpAddress", request.getRemoteAddr());
        session.setAttribute("bookId", bookId);
        return "reservationConfirmation";
    }

    @PostMapping("/add")
    public String addReservation(HttpSession session) {
        String selectedBook = (String) session.getAttribute("selectedBook");
        String readerName = (String) session.getAttribute("readerName");
        String readerAddress = (String) session.getAttribute("readerAddress");
        Integer numCopies = (Integer) session.getAttribute("numCopies");
        Long bookId = (Long) session.getAttribute("bookId");
        this.bookReservationService.placeReservation(bookId, selectedBook, readerName, readerAddress, numCopies);
        return "redirect:/books";
    }

}
