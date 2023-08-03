package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().contactPage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new contactData().withFirstname("test1").withLastname("test2"));
        }
    }

    @Test
    public void testDeleteContact() {
        List<contactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        List<contactData> after = app.contact().list();
        Assert.assertEquals(index, after.size());
        before.remove(index);
        Assert.assertEquals(before, after);
    }
}