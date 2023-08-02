package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void addNewContactTest() {
        app.getNavigationHelper().goToContactPage();
        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().fillContactForm(new contactData("test1", null, "test3", "test4",
                "test5", "test6", "test7", "test1"), true);
        app.getContactHelper().submitContactCreation();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before + 1, after);
    }
}