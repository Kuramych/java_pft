package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;


public class DeleteFirstGroup extends TestBase {

  @Test
  public void testDeleteFirstGroup() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getGroupHelper().returnToGroupPage();
  }

}
