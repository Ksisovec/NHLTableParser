package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "match_result", schema = "match_result_schema", catalog = "postgres")
public class MatchResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(name = "timetv")
    private String timeTv;

    @Column(name = "first_team_name")
    private String firstTeamName;

    @Column(name = "second_team_name")
    private String secondTeamName;
    /**
     * true - first team win
     * false - second team win
     * */
    @Column(name = "winner")
    private Boolean winner = null;

    @OneToMany(mappedBy = "matchResult",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<BookValue> bookValues;

    public MatchResult(){}

    public MatchResult(Integer id, String date, String firstTeamName,  String secondTeamName, Boolean winner,
                     List<BookValue> bookValues){
        this.id = id;
        LocalDate localDate = LocalDate.parse(date);
        this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());;
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

    public List<BookValue> getBookValues() {
        return bookValues;
    }

    public void setBookValues(List<BookValue> bookValues) {
        this.bookValues = bookValues;
    }

    public LocalDate getDate() {
        LocalDate localDate = this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    public void setDate(String date) {

        LocalDate localDate = LocalDate.parse(date);
        this.date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
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