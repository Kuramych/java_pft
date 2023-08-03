package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;
import java.util.Set;


public class DeleteGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new groupData().withName("test1"));
    }
  }

  @Test
  public void testDeleteFirstGroup() {
    Set<groupData> before = app.group().all();
    groupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<groupData> after = app.group().all();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }
}
