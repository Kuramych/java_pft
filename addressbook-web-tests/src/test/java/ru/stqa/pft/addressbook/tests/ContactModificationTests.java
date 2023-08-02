package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void modificationContact() {
        app.getNavigationHelper().goToContactPage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new contactData("test1", null, "test3", "test4",
                    "test5", "test6", "test7", "test1"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new contactData("newTest1",
                "newTest2","newTest3", "newTest4","newTest5",
                "newTest6", "newTest7", null), false);
        app.getContactHelper().submitContactModification();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before, after);
    }
}
