package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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
        groupData group = new groupData(before.get(before.size() - 1).getId(), "test3", null, "test5");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<groupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);

        Set<groupData> beforeSet = new HashSet<>(before);
        Set<groupData> afterSet = new HashSet<>(after);
        Assert.assertEquals(beforeSet, afterSet);
        //Assert.assertEquals(new HashSet<Objects>(before), new HashSet<Objects>(after));
    }
}
