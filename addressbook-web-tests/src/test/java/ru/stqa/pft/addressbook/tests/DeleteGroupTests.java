package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;


public class DeleteGroupTests extends TestBase {

  @Test
  public void testDeleteFirstGroup() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereGroup()) {
      app.getGroupHelper().createGroup(new groupData("test1", null, null));
    }
    List<groupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
    List<groupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
