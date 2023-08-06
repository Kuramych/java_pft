package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test(enabled = false)
    public void modificationContact1() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");

        app.contact().modify(contact);

        Set<ContactData> after = app.contact().all();

        assertEquals(after.size(),before.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
    }
    @Test
    public void modificationContact() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");

        app.contact().modify(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
