package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.groupData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;


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
    Groups before = app.group().all();
    groupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

  @Test(enabled = false)
  public void testDeleteFirstGroup1() {
    Set<groupData> before = app.group().all();
    groupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    Set<groupData> after = app.group().all();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(deletedGroup);
    Assert.assertEquals(before, after);
  }
}
