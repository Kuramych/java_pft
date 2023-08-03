package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test
    public void addNewContactTest() {
        app.goTo().contactPage();
        Set<contactData> before = app.contact().all();
        contactData contact = new contactData().withFirstname("test1").withLastname("test2");
        app.contact().create(contact);

        Set<contactData> after = app.contact().all();

        Assert.assertEquals(before.size() + 1, after.size());
        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}