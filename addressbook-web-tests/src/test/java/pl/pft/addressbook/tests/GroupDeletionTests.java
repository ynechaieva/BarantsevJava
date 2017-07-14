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
    app.goTo().GroupPage();
    if(app.group().all().size() == 0){
      app.group().create(new GroupData().withName("test3"));
    }
  }

  @Test
  public void testGroupDeletion(){

    Groups beforeList = app.group().all();
    GroupData deletedGroup = beforeList.iterator().next();
    app.group().delete(deletedGroup);
    Groups afterList = app.group().all();

    assertThat(afterList.size(), equalTo(beforeList.size() - 1));
    assertThat(afterList, equalTo(beforeList.without(deletedGroup)));

  }



}
