import Model.BookValues;
import Model.MatchResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class HTMLParser {

    public static Map<String, String> gettingValuesFromCellsTest(Element row, String teamRel) {
        /**Getting values from rows by teams*/
        Map<String, String> teamValues = new HashMap<>();

        Elements firstCell = row.select("div[id~=eventLineOpener.*-" + teamRel + "-]");
        teamValues.put(teamRel, firstCell.get(0).text());

        getOtherCellsTest(row, teamRel, teamValues);

        /**End getting values from rows by team*/
        return teamValues;
    }
    public static void getOtherCellsTest(Element row, String teamRel, Map<String, String> teamValues) {
        //эти данные надо получать несколько раз
        Elements teamRow = row.select("div[id~=eventLineBook.*-" + teamRel + "-]");
        Elements teamCells = teamRow.select("b");
        Elements teamCellsId = teamRow;
        for (int i = 0; i < teamCells.size(); i++) {
            String id = teamCellsId.get(i).id().split("-")[2] +
                    teamRel;
//                    .("(\\d-\\d+-\\d)");
            teamValues.put(id , teamCells.get(i).text());
        }
    }


    public static List<String> getListOfBooks(Document doc) {
        Elements books = doc.select("#booksCarousel");
        List<String> bookList = new ArrayList<>();
        bookList.addAll(books.select("a").eachText());
        return bookList;
    }

    public static Map<String, String> getMapOfBooks(Document doc) throws Exception {
        Elements books = doc.select("#booksCarousel");
        List<String> bookNames = new ArrayList<>();
        List<String> bookId = new ArrayList<>();
        bookNames.addAll(books.select("a").eachText());
        bookId.addAll(books.select("a").eachAttr("book"));
        Map<String, String> mapBooks = new HashMap<>();
        if (bookNames.size() != bookId.size())
            throw new Exception();
        mapBooks.put("0", "Opener");
        for (int i = 0; i < bookNames.size(); i++) {
            mapBooks.put(bookId.get(i), bookNames.get(i));
        }
        return mapBooks;
    }

    public static Map<String, String> getMapOfTeams(Document doc) throws Exception {
        Elements NHLHockeyNames = doc.select(".team-name");
        List<String> teamNames = new ArrayList<>();
        List<String> teamId = new ArrayList<>();

        teamNames.addAll(NHLHockeyNames.eachText());
        teamId.addAll(NHLHockeyNames.eachAttr("rel"));

        Map<String, String> mapTeams = new HashMap<>();
        if (teamNames.size() != teamId.size())
            throw new Exception();
        for (int i = 0; i < teamNames.size(); i++) {
            mapTeams.put(teamId.get(i), teamNames.get(i));
        }
        return mapTeams;
    }

    public static void main(String[] args) {
        try {
            String startDate = args[0];
            String endDate = null;
            if(args.length > 1)
                endDate = args[1];
            //TODO: add setting to choose date
            List<MatchResult> matchResults = setInNHLResult(startDate, endDate);
            //TODO: enter to db
            System.out.println("Finish");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static List<MatchResult> setInNHLResult(String startDate, String endDate) {
        List<MatchResult> matchResultList = new ArrayList<>();
        try {

            WebDriver webDriver = PhantomJsUtils.getWebDriver(startDate, endDate);
            Document doc = Jsoup.parse(webDriver.getPageSource());

            Map<String, String> mapOfBooks = getMapOfBooks(doc);
            Map<String, String> mapOfTeams = getMapOfTeams(doc);
            /**Get date*/
            String date = doc.select("div[id^=byDateleagueData]").first().id();

            date = date.substring(date.length() - 10);

            Map<String, String> firstTeamValuesTest = new HashMap<>();
            Map<String, String> secondTeamValuesTest = new HashMap<>();
            /**End get date*/
            for (int i = 0; i < 3; i++) {
                Element table = doc.select(".content-final.content-complete").first();
                Elements rows = table.children();
                if (i == 0) {

                }
                for (Element row : rows) {
                    Elements NHLHockeyNames = row.select(".team-name");

                    /**-------------------------*/
                    /**Teams*/
                    /**First team id*/
                    Element firstTeam = NHLHockeyNames.first();
                    String firstTeamRel = firstTeam.attr("rel");

                    /**Second team id*/
                    Element secondTeam = NHLHockeyNames.last();
                    String secondTeamRel = secondTeam.attr("rel");
                    /**-------------------------*/

                    /**Getting table values for first and second team*/
                    firstTeamValuesTest.putAll(gettingValuesFromCellsTest(row, firstTeamRel));
                    secondTeamValuesTest.putAll(gettingValuesFromCellsTest(row, secondTeamRel));
                    /**End getting table values*/
                    //т.е. вся таблица распаршена
                    if (i == 2) {
                        String firstTeamName = firstTeam.text();
                        String secondTeamName = secondTeam.text();
                        MatchResult matchResult = new MatchResult();
                        matchResult.setDate(LocalDate.parse(date));
                        matchResult.setFirstTeamName(firstTeamName);
                        matchResult.setSecondTeamName(secondTeamName);

                        /**Set winner*/

                        Boolean winner = null;
                        Element winnerEl = row.select(".eventLine-value").first();
                        if(winnerEl.children().first().children().size() >
                                winnerEl.children().last().children().size())
                            winner = true;
                        else winner = false;
                        matchResult.setWinner(winner);
                        /**End set winner*/

                        /**Set Time*/

                        String time = row.select(".el-div.eventLine-time").text();
                        matchResult.setTimeTv(time);
                        /**End set time*/

                        List<BookValues> bookValuesList = new ArrayList<>();
                        {
                            String[] booksId = new String[mapOfBooks.size()];
                            mapOfBooks.keySet().toArray(booksId);
                            for (int j = 0; j < mapOfBooks.size(); j++) {
                                String firstTeamId = getTeamIdByName(firstTeamName, mapOfTeams);
                                String secondTeamId = getTeamIdByName(secondTeamName, mapOfTeams);
                                String bookId = booksId[j];
                                String firstTeamValue = firstTeamValuesTest.get(bookId + firstTeamId);
                                String secondTeamValue = secondTeamValuesTest.get(bookId + secondTeamId);
                                BookValues bookValue = new BookValues(mapOfBooks.get(bookId), firstTeamValue, secondTeamValue);
                                bookValuesList.add(bookValue);
                            }
                        }
                        matchResult.setBookValues(bookValuesList);
                        matchResultList.add(matchResult);
                    }

                }
                // поворот страницы
                if(i != 2)
                    doc = PhantomJsUtils.turnThePage(webDriver);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchResultList;
    }

    public static String getTeamIdByName(String teamName, Map<String, String> mapOfTeams) throws Exception {
        for (Map.Entry<String, String> entry : mapOfTeams.entrySet()) {
            if (entry.getValue().equals(teamName)) {
                return entry.getKey();
            }
        }
        throw new Exception();
    }



}
