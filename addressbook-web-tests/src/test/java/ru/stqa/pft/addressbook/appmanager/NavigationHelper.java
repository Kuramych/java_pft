package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1")) &&
                wd.findElement(By.tagName("h1")).getText().equals("Groups") &&
                isElementPresent(By.tagName("new"))) {
            return;
        }
        click(By.linkText("groups"));
    }


    public void contactPage() {
        if (isElementPresent(By.tagName("strong")) &&
                wd.findElement(By.tagName("strong")).getText().equals("Number of results: ")) {
            return;
        }
        click(By.linkText("home")); }

}
