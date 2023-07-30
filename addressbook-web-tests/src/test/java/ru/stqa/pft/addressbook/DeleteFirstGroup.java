package ru.stqa.pft.addressbook;

import org.testng.annotations.*;


public class DeleteFirstGroup extends TestBase {

  @Test
  public void testDeleteFirstGroup() {
    goToGroupPage();
    selectGroup();
    deleteGroup();
    returnToGroupPage();
  }

}
