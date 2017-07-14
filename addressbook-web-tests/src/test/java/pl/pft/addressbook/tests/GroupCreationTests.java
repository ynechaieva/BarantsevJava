package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class GroupCreationTests extends TestBase{

  @Test
  public void testGroupCreation() {

    app.goTo().GroupPage();
    Groups beforeList = app.group().all();
    GroupData newgroup = new GroupData().withName("test2");
    app.group().create(newgroup);
    Groups afterList = app.group().all();

    assertThat(afterList.size(), equalTo(beforeList.size() + 1));
    assertThat(afterList, equalTo(beforeList.withAdded(newgroup.withId(afterList.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
