package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.appmanager.ContactHelper;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test(enabled = false)
    public void modificationContact1() {
        Set<contactData> before = app.contact().all();
        contactData modifiedContact = before.iterator().next();
        contactData contact = new contactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");

        app.contact().modify(contact);

        Set<contactData> after = app.contact().all();

        assertEquals(after.size(),before.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
    }
    @Test
    public void modificationContact() {
        Contacts before = app.contact().all();
        contactData modifiedContact = before.iterator().next();
        contactData contact = new contactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");

        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
