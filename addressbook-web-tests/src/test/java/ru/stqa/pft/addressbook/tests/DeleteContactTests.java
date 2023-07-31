package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class DeleteContactTests extends TestBase {
    @Test
    public void testDeleteContact() {
        app.getNavigationHelper().goToContactPage();
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }
}
