package model;

import javax.persistence.*;

@Entity
@Table(name = "book_value", schema = "match_result_schema", catalog = "postgres")
public class BookValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "book_name")
    String bookName;

    @Column(name = "first_value")
    String firstValue;

    @Column(name = "second_value")
    String secondValue;

    @ManyToOne
    @JoinColumn(name = "match_result_id")
    private MatchResult matchResult;

    public BookValue(){}

    public BookValue(String bookName, String firstValue, String secondValue) {
        this.bookName = bookName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(String firstValue) {
        this.firstValue = firstValue;
    }

    public String getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(String secondValue) {
        this.secondValue = secondValue;
    }

    public MatchResult getMatchResult() {
        return matchResult;
    }

    public void setMatchResult(MatchResult matchResult) {
        this.matchResult = matchResult;
    }
}
