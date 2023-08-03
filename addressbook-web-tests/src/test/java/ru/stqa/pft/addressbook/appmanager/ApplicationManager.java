package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;
import java.util.Objects;

public class ApplicationManager {
        private Browser browser;
        WebDriver wd;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private ContactHelper contactHelper;

    public ApplicationManager(Browser browser) {
        this.browser = browser;
    }

    public void init() {
        if (Objects.equals(browser, Browser.CHROME)) {
            System.setProperty("webdriver.chrome.driver", "C://Windows//System32//0409//chromedriver.exe");
            wd = new ChromeDriver();
        } else if (Objects.equals(browser, Browser.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", "C://Windows//System32//0409//geckodriver.exe");
            System.setProperty("webdriver.firefox.bin", "C://Program Files//Mozilla Firefox//firefox.exe");
            wd = new FirefoxDriver();
        }

        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() { return contactHelper; }
}
