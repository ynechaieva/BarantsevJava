package pl.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("new group").withHeader("new header").withFooter("new footer"));
    }
  }

  @Test
  public void testGroupModification(){

    Groups beforeList = app.db().groups();
    GroupData modifiedGroup = beforeList.iterator().next();
    GroupData newgroup = new GroupData()
            .withId(modifiedGroup.getId()).withName("new group C").withFooter("cc").withHeader("c_header");

    app.goTo().GroupPage();
    app.group().modify(newgroup);

    assertThat(app.group().count(), equalTo(beforeList.size()));
    Groups afterList = app.db().groups();
    assertThat(afterList, equalTo(beforeList.without(modifiedGroup).withAdded(newgroup)));
    verifyGroupListInUI();
  }



}
