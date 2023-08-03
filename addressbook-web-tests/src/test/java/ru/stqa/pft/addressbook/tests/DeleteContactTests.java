package ru.stqa.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class DeleteContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.getNavigationHelper().goToContactPage();
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new contactData("test1", "test2", "test3", "test4",
                    "test5", "test6", "test7", "test1"));
        }
    }

    @Test
    public void testDeleteContact() {
        List<contactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().selectContact(index);
        app.getContactHelper().deleteContact();
        List<contactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(index, after.size());
        before.remove(index);
        Assert.assertEquals(before, after);
    }
}