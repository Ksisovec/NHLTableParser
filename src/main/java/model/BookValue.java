package Model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BookValues {
    Integer id;
    String bookName;
    String firstValue;
    String secondValue;
    @ManyToOne
    @JoinColumn(name = "")
    private MatchResult matchResult;

    public BookValues(){}

    public BookValues(String bookName, String firstValue, String secondValue) {
        this.bookName = bookName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
}
