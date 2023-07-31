package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.contactData;


public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) { super(wd); }

    public void fillContactForm(contactData contactData) {
        wd.get("http://localhost/addressbook/edit.php");
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }
}
