package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.*;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new groupData().withName("test1"));
        }
    }

    @Test
    public void testGroupModification() {
        Groups before = app.group().all();
        groupData modifiedGroup = before.iterator().next();
        groupData group = new groupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("3").withFooter("test3");

        app.group().modify(group);

        Groups after = app.group().all();

        assertEquals(after.size(), before.size());
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

    @Test(enabled = false)
    public void testGroupModification1() {
        Set<groupData> before = app.group().all();
        groupData modifiedGroup = before.iterator().next();
        groupData group = new groupData()
                .withId(modifiedGroup.getId()).withName("test1").withHeader("3").withFooter("test3");

        app.group().modify(group);

        Set<groupData> after = app.group().all();

        assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        assertEquals(before, after);
    }

}
