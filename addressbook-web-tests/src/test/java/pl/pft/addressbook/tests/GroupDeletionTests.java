package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if(app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupDeletion(){

    List<GroupData> beforeList = app.group().list();
    int index = beforeList.size() - 1;
    app.group().delete(index);
    List<GroupData> afterList = app.group().list();
    Assert.assertEquals(afterList.size(), beforeList.size() - 1);

    beforeList.remove(index);
    Assert.assertEquals(afterList, beforeList);
  }



}
