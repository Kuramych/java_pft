package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void addNewContactTest() {
        app.getNavigationHelper().goToAddContactPage();
        app.getContactHelper().fillContactForm(new contactData("test1", "test2", "test3", "test4", "test5", "test6", "test7"));
        app.getContactHelper().submitContactCreation();
    }
}