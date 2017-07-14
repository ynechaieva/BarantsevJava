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
    app.goTo().GroupPage();
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupModification(){

    Groups beforeList = app.group().all();
    GroupData modifiedGroup = beforeList.iterator().next();
    GroupData newgroup = new GroupData()
            .withId(modifiedGroup.getId()).withName("new group C").withFooter("cc").withHeader("c_header");
    app.group().modify(newgroup);

    assertThat(app.group().count(), equalTo(beforeList.size()));
    Groups afterList = app.group().all();
    assertThat(afterList, equalTo(beforeList.without(modifiedGroup).withAdded(newgroup)));
  }


}
