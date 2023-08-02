package ru.stqa.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

import java.util.List;

public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().goToContactPage();
        List<contactData> before = app.getContactHelper().getContactList();
        //if (! app.getContactHelper().isThereContact()) {
        //    app.getContactHelper().goToAddContactPage();
        //    app.getContactHelper().fillContactForm(new contactData("test1", null, "test3", "test4",
        //            "test5", "test6", "test7", "test1"), true);
        //    app.getContactHelper().submitContactCreation();
        //}
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new contactData("test1", null, "test3", "test4",
                    "test5", "test6", "test7", "test1"), true);
        }
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteContact();
        List<contactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size() - 1, after.size());
        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}