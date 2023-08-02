package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{
    @Test
    public void modificationContact() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new contactData("test1", null, "test3", "test4",
                    "test5", "test6", "test7", "test1"));
        }

        List<contactData> before = app.getContactHelper().getContactList();

        contactData contact = (new contactData(before.get(before.size() - 1).getId(),"newTest1",
                "newTest2","newTest3", "newTest4","newTest5",
                "newTest6", "newTest7", null));

        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactModification();

        List<contactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(before.size(), after.size());

        before.remove(before.size() - 1);
        before.add(contact);

        Set<contactData> beforeSet = new HashSet<>(before);
        Set<contactData> afterSet = new HashSet<>(after);

        Assert.assertEquals(beforeSet, afterSet);
    }
}
