package Model;

public class BookValues {
    Integer id;
    String bookName;
    String firstValue;
    String secondValue;
    public BookValues(){}

    public BookValues(String bookName, String firstValue, String secondValue) {
        this.bookName = bookName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
}
