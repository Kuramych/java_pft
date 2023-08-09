package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ContactHelper extends HelperBase{
    public ContactHelper(WebDriver wd) { super(wd); }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        // attach(By.name("photo"), contactData.getPhoto());
        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertTrue(isElementPresent(By.name("new_group")));
            // Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
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


    public void modify(ContactData contact, boolean creation) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, creation);
        submitContactModification();
        contactCache = null;
    }

    public void create(ContactData contact, boolean creation) {
        goToAddContactPage();
        fillContactForm(contact, creation);
        submitContactCreation();
        contactCache = null;
    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
    }
    public void delete(ContactData deleteContact) {
        selectContactById(deleteContact.getId());
        deleteContact();
        contactCache = null;
    }

    public int getContactCount() {
         return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for (WebElement element: elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
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
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhone(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
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
        return new ContactData().withId(contact.getId()).withFirstname(firstname).
                withLastname(lastname).withHomePhone(home).withWorkPhone(work).
                withMobilePhone(mobile).withEmail(email).withEmail1(email1).
                withEmail2(email2).withAddress(address);
    }
    public ContactData infoFromFullForm(ContactData contact) {
        getContactFullInfoById(contact.getId());
        String element = wd.findElement(By.id("content")).getText();
        Pattern patternHomePhone = Pattern.compile("H: (.*)");
        Pattern patternMobilePhone = Pattern.compile("M: (.*)");
        Pattern patternWorkPhone = Pattern.compile("W: (.*)");
        Matcher matcherHomePhone = patternHomePhone.matcher(element);
        Matcher matcherWorkPhone = patternWorkPhone.matcher(element);
        Matcher matcherMobilePhone = patternMobilePhone.matcher(element);
        String home = null;
        if (matcherHomePhone.find()) {
            home = matcherHomePhone.group(1);
        }
        String work = null;
        if (matcherWorkPhone.find()) {
            work = matcherWorkPhone.group(1);
        }
        String mobile = null;
        if (matcherMobilePhone.find()) {
            mobile = matcherMobilePhone.group(1);
        }
        return new ContactData().withId(contact.getId()).withWorkPhone(work)
                .withMobilePhone(mobile).withHomePhone(home);
    }

    private void getContactFullInfoById(int id) {
        wd.findElement(By.cssSelector("a[href='view.php?id=" + id + "']")).click();
    }
}