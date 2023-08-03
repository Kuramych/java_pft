package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;
import java.util.Set;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test
    public void testDeleteContact() {
        Set<contactData> before = app.contact().all();
        contactData deleteContact = before.iterator().next();
        app.contact().delete(deleteContact);
        Set<contactData> after = app.contact().all();
        Assert.assertEquals(before.size() - 1, after.size());
        before.remove(deleteContact);
        Assert.assertEquals(before, after);
    }
}