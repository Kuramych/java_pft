package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

public class GroupCreationTests extends TestBase  {

  @Test
  public void testGroupCreation() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new groupData("test1", null, null));
  }
}


