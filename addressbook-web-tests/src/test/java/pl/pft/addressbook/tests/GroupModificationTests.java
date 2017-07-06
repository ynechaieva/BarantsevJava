package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test3", "test2", "test3"));
    }
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeList.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData newgroup = new GroupData(beforeList.get(beforeList.size() - 1).getId() , "new group C", "c", "cc");
    app.getGroupHelper().fillGroupForm(newgroup);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(beforeList.size() - 1);
    beforeList.add(newgroup);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }
}
