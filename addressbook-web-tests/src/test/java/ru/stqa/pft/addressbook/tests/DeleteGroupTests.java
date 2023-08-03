package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;


public class DeleteGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new groupData().withName("test1"));
    }
  }

  @Test
  public void testDeleteFirstGroup() {
    List<groupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    List<groupData> after = app.group().list();
    Assert.assertEquals(index, after.size());

    before.remove(index);
    Assert.assertEquals(before, after);
  }
}
