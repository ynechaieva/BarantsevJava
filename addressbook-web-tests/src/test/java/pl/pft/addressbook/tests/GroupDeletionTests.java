package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion(){
    app.getNavigationHelper().gotoGroupPage();
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    }
    List<GroupData> beforeList = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(beforeList.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> afterList = app.getGroupHelper().getGroupList();
    Assert.assertEquals(afterList.size(), beforeList.size() - 1);

    beforeList.remove(beforeList.size() - 1);
    Assert.assertEquals(afterList, beforeList);
  }

}
