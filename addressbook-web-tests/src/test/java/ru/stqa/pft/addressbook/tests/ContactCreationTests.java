package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase{

    @Test(enabled = false)
    public void addNewContactTest1() {
        app.goTo().contactPage();
        Set<contactData> before = app.contact().all();
        contactData contact = new contactData().withFirstname("test1").withLastname("test2");
        app.contact().create(contact);
        Set<contactData> after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);
    }

    @Test
    public void addNewContactTest() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        contactData contact = new contactData().withFirstname("test1").withLastname("test2");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
