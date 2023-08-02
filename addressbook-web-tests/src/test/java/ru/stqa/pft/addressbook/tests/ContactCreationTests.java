package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;;
import ru.stqa.pft.addressbook.model.contactData;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase{

    @Test
    public void addNewContactTest() {
        app.getNavigationHelper().goToContactPage();
        List<contactData> before = app.getContactHelper().getContactList();
        contactData contact = new contactData("test1", "test2", "test3", "test4",
                "test5", "test6", "test7", "test1");
        app.getContactHelper().goToAddContactPage();
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactCreation();
        List<contactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(before.size() + 1, after.size());

        before.add(contact);

        Set<contactData> beforeSet = new HashSet<>(before);
        Set<contactData> afterSet = new HashSet<>(after);
        Comparator<? super contactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}