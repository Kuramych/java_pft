package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new contactData("test1", "test2", "test3", "test4",
                    "test5", "test6", "test7", "test1"));
        }
    }

    @Test
    public void modificationContact() {
        List<contactData> before = app.contact().list();
        int index = before.size() - 1;
        contactData contact = new contactData("newTest1",
                "newTest2","newTest3", "newTest4","newTest5",
                "newTest6", "newTest7", null);

        app.contact().modify(index, contact);

        List<contactData> after = app.contact().list();

        Assert.assertEquals(before.size(), after.size());

        before.remove(index);
        before.add(contact);

        Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
