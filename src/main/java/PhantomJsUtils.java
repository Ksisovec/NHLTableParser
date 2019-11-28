import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;

class PhantomJsUtils {


    //TODO: enter date
    public static WebDriver getWebDriver(String date) {
        File path = new File("phantomjs.exe");
        System.setProperty("phantomjs.binary.path", path.getAbsolutePath());
        WebDriver ghostDriver = new PhantomJSDriver();
        date = date.replaceAll("-", "");

        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/nhl-hockey/?date=" + date);
        /**
         * Из условия было не ясно: что надо парсить: только nhl-hockey или для каждого вида спорта
         * в виду этого сделал первый вариант. В случае если надо будет добавить другой вид спорта -
         * можно добавить ещё один входной параметр (вид спорта). В зависимости от которого выбириается
         * ссылка. Один из вариантов решения. (Хотя в  этом случае ещё надо будет изменить таблицу в бд
         * добавив в неё столбец с указанием вида спорта)
         * Если парсить для всех видов спорта - перебрать все ссылки по порядку.
         * */
        /*
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/college-football/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/arena-football/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/cfl-football/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/mlb-baseball/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/nba-basketball/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/ncaa-basketball/?date=" + date);
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/ufc/?date=" + date);
        */

        return ghostDriver;
    }




    public static Document turnThePage(WebDriver ghostDriver){
        WebElement next = ghostDriver.findElement(By.className("next"));
        next.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Jsoup.parse(ghostDriver.getPageSource());
    }



}