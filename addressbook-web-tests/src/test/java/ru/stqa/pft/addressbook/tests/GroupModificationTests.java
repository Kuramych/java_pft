package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new groupData("test1", null, null));
        }
    }

    @Test
    public void testGroupModification() {
        List<groupData> before = app.group().list();
        int index = before.size() - 1;
        groupData group = new groupData(before.get(index).getId(), "test3", null, "test5");

        app.group().modify(index, group);

        List<groupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
