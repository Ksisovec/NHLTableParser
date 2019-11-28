import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.File;

class PhantomJsUtils {


    //TODO: enter date
    public static WebDriver getWebDriver(String startDate, String endDate){
        File path = new File("d:\\Download\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        System.setProperty("phantomjs.binary.path", path.getAbsolutePath());
        WebDriver ghostDriver = new PhantomJSDriver();
//        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/nhl-hockey/?date=20191115");
//        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/college-football/?date=20191115");
        ghostDriver.get("https://classic.sportsbookreview.com/betting-odds/ncaa-basketball/?date=20191115");
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