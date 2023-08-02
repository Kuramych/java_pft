package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new groupData("test1", null, null));
        }
        List<groupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new groupData("test3", null, "test5"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

    }
}
