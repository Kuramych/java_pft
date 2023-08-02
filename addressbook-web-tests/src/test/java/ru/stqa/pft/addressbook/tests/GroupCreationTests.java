package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;

public class GroupCreationTests extends TestBase  {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    List<groupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().createGroup(new groupData("test1", null, null));
    List<groupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}


