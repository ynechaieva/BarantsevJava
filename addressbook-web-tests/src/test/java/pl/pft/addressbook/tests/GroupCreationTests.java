package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
  }

}
