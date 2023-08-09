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
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"), false);
        }
    }

    @Test(enabled = false)
    public void modificationContact1() {
        Set<ContactData> before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");
        app.goTo().contactPage();
        app.contact().modify(contact, false );

        Set<ContactData> after = app.db().contacts();

        assertEquals(after.size(),before.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
    }
    @Test
    public void modificationContact() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("newTest1").withLastname("newTest2");
        app.goTo().contactPage();
        app.contact().modify(contact, false);
        assertThat(app.contact().getContactCount(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
