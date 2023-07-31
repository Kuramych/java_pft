package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

public class ContactModificationTests extends TestBase{
    @Test
    public void modificationContact() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new contactData("newTest1",
                "newTest2","newTest3", "newTest4","newTest5",
                "newTest6", "newTest7"));
        app.getContactHelper().submitContactModification();
    }
}
