package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test
    public void modificationContact() {
        Set<contactData> before = app.contact().all();
        contactData modifiedContact = before.iterator().next();
        contactData contact = new contactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");

        app.contact().modify(contact);

        Set<contactData> after = app.contact().all();

        Assert.assertEquals(before.size(), after.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
