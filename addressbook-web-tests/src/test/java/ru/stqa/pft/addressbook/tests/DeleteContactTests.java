package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test(enabled = false)
    public void testDeleteContact1() {
        Set<contactData> before = app.contact().all();
        contactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Set<contactData> after = app.contact().all();
        assertEquals(before.size() - 1, after.size());
        before.remove(deleteContact);
        assertEquals(before, after);
    }

    @Test
    public void testDeleteContact() {
        Contacts before = app.contact().all();
        contactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Contacts after = app.contact().all();
        assertEquals(before.size() - 1, after.size());
        assertThat(after, CoreMatchers.equalTo(before.without(deleteContact)));
    }
}