package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchResult {
    Integer id;
    LocalDate date;
    String timeTv;
    String firstTeamName;
    String secondTeamName;
    /**
     * true - first team win
     * false - second team win
     * */
    Boolean winner = null;
    List<BookValues> bookValues;

    public MatchResult(){}

    public MatchResult(Integer id, String date, String firstTeamName,  String secondTeamName, Boolean winner,
                     List<BookValues> bookValues){
        this.id = id;
        LocalDate localDate = LocalDate.parse(date);
        this.date = localDate;
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.winner = winner;
        this.bookValues = bookValues;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<BookValues> getBookValues() {
        return bookValues;
    }

    public void setBookValues(List<BookValues> bookValues) {
        this.bookValues = bookValues;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public void setFirstTeamName(String firstTeamName) {
        this.firstTeamName = firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public void setSecondTeamName(String secondTeamName) {
        this.secondTeamName = secondTeamName;
    }

    public Boolean getWinner() {
        return winner;
    }

    public void setWinner(Boolean winner) {
        this.winner = winner;
    }

    public String getTimeTv() {
        return timeTv;
    }

    public void setTimeTv(String timeTv) {
        this.timeTv = timeTv;
    }


}