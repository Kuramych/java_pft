package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

    @Test
    public void addNewContactTest() {
        app.goTo().contactPage();
        List<contactData> before = app.contact().list();
        contactData contact = new contactData("test1", "test2", "test3", "test4",
                "test5", "test6", "test7", "test1");
        app.contact().create(contact);
        List<contactData> after = app.contact().list();
        Assert.assertEquals(before.size() + 1, after.size());

        before.add(contact);

        Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}