package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if(app.group().list().size() == 0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModification(){

    List<GroupData> beforeList = app.group().list();
    int index = beforeList.size() - 1;
    GroupData newgroup = new GroupData()
            .withId(beforeList.get(index).getId()).withName("new group C").withFooter("cc").withHeader("c_header");
    app.group().modify(index, newgroup);
    List<GroupData> afterList = app.group().list();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(index);
    beforeList.add(newgroup);

    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }


}
