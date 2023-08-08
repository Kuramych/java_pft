package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test(enabled = false)
    public void testDeleteContact1() {
        Set<ContactData> before = app.db().contacts();
        ContactData deleteContact = before.iterator().next();
        app.goTo().contactPage();
        app.contact().delete(deleteContact);
        Set<ContactData> after = app.db().contacts();
        assertEquals(before.size() - 1, after.size());
        before.remove(deleteContact);
        assertEquals(before, after);
    }

    @Test
    public void testDeleteContact() {
        Contacts before = app.db().contacts();
        ContactData deleteContact = before.iterator().next();
        app.goTo().contactPage();
        app.contact().delete(deleteContact);
        assertThat(app.contact().getContactCount(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(deleteContact)));
    }
}