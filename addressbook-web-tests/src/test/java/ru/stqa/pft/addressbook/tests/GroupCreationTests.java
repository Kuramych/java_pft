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

    group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Set<groupData> beforeSet = new HashSet<>(before);
    Set<groupData> afterSet = new HashSet<>(after);
    Assert.assertEquals(beforeSet, afterSet);
  }
}


