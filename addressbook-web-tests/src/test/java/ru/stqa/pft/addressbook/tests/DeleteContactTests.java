package ru.stqa.pft.addressbook.tests;

import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.contactData;

public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().goToContactPage();
        int before = app.getContactHelper().getContactCount();
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
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(before - 1, after);
    }
}