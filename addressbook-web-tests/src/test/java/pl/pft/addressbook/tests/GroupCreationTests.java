package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();
    GroupData newgroup = new GroupData("test2", "test2", "test3");
    app.getGroupHelper().createGroup(newgroup);
    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterList.size(), beforeList.size() + 1);

    //newgroup.setId(afterList.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeList.add(newgroup);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }

}
