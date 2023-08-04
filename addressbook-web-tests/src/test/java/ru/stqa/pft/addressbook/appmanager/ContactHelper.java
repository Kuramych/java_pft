package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) { super(wd); }

    public void fillContactForm(contactData contactData) {
        // wd.get("http://localhost/addressbook/edit.php");
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        //if (creation) {
        //    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //} else {
        //    Assert.assertFalse(isElementPresent(By.name("new_group")));
        //}
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }
    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
    }

    //public void initContactModification() {
    //    click(By.xpath("//img[@alt='Edit']"));
    //}

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }
    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void goToAddContactPage() {
        if (isElementPresent(By.tagName("h1")) &&
                wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void modify(int index, contactData contact) {
        selectContact(index);
        initContactModification(index);
        fillContactForm(contact);
        submitContactModification();
    }
    public void modify(contactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
    }

    public void create(contactData contact) {
        goToAddContactPage();
        fillContactForm(contact);
        submitContactCreation();
        contactCache = null;
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
    }
    public void delete(contactData deleteContact) {
        selectContactById(deleteContact.getId());
        deleteContact();
        contactCache = null;
    }

    public int getContactCount() {
         return wd.findElements(By.name("selected[]")).size();
    }

    public List<contactData> list() {
        List<contactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            contactData contact = new contactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null){
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            contactCache.add(new contactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhone(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public contactData infoFromEditForm(contactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email1 = wd.findElement(By.name("email2")).getAttribute("value");
        String email2 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new contactData().withId(contact.getId()).withFirstname(firstname).
                withLastname(lastname).withHomePhone(home).withWorkPhone(work).
                withMobilePhone(mobile).withEmail(email).withEmail1(email1).
                withEmail2(email2).withAddress(address);

    }
}