package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class ContactCreationTests extends TestBase{

    @Test(enabled = false)
    public void addNewContactTest1() {
        app.goTo().contactPage();
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("test1").withLastname("test2");
        app.contact().create(contact);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);
    }

    @Test
    public void addNewContactTest() {
        app.goTo().contactPage();
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/photo.jpg");
        ContactData contact = new ContactData().withFirstname("test1").withLastname("test2").withPhoto(photo);
        app.contact().create(contact);
        assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }
}
