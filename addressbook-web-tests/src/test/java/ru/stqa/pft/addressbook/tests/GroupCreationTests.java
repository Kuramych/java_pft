package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase  {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<groupData> before = app.getGroupHelper().getGroupList();
    groupData group = new groupData("test2", null, null);
    app.getGroupHelper().createGroup(group);
    List<groupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Set<groupData> beforeSet = new HashSet<>(before);
    Set<groupData> afterSet = new HashSet<>(after);
    Comparator<? super groupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}


