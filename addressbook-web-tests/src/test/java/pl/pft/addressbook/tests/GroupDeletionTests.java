package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class GroupDeletionTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("new group").withHeader("new header").withFooter("new footer"));
    }
  }

  @Test
  public void testGroupDeletion(){

    Groups beforeList = app.db().groups();
    GroupData deletedGroup = beforeList.iterator().next();
    app.goTo().GroupPage();
    app.group().delete(deletedGroup);

    assertThat(app.group().count(), equalTo(beforeList.size() - 1));
    Groups afterList = app.db().groups();
    assertThat(afterList, equalTo(beforeList.without(deletedGroup)));

  }



}
