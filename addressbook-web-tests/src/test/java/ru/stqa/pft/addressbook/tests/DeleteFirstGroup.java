package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.GroupHelper;
import ru.stqa.pft.addressbook.model.groupData;


public class DeleteFirstGroup extends TestBase {

  @Test
  public void testDeleteFirstGroup() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new groupData("test1", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
