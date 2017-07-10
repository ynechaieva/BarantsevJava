package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.goTo().GroupPage();
    List<GroupData> beforeList = app.group().list();
    GroupData newgroup = new GroupData().withName("test2");
    app.group().create(newgroup);
    List<GroupData> afterList = app.group().list();
    Assert.assertEquals(afterList.size(), beforeList.size() + 1);

    //newgroup.setId(afterList.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeList.add(newgroup);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }

}
