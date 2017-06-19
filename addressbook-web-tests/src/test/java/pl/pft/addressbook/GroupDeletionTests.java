package pl.pft.addressbook;

import org.testng.annotations.Test;

/**
 * Created by ynech on 19/06/2017.
 */
public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletion(){
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
