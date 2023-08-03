package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.*;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (! app.getGroupHelper().isThereGroup()) {
            app.getGroupHelper().createGroup(new groupData("test1", null, null));
        }

        List<groupData> before = app.getGroupHelper().getGroupList();

        groupData group = new groupData(before.get(before.size() - 1).getId(), "test3", null, "test5");

        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        List<groupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        //Set<groupData> beforeSet = new HashSet<>(before);
        //Set<groupData> afterSet = new HashSet<>(after);
        Assert.assertEquals(before, after);
        //Assert.assertEquals(new HashSet<Objects>(before), new HashSet<Objects>(after));
    }
}
