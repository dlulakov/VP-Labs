package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookReservation {

    private Book book;
    private String readerName;
    private String readerAddress;
    private Long numberOfCopies;

}
