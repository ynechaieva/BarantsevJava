package pl.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemovingContactFromGroupTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test group").withHeader("test header").withFooter("test footer"));
    }

    if(app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address"), true);
    }
    //app.db().contacts().iterator().next().inGroup(app.db().groups().iterator().next());
  }

  @Test
  public void testRemovingContactFromGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    contact.removeAllGroups();
    GroupData group = app.db().groups().iterator().next();
    app.contact().addToGroup(contact, group);
    Groups beforeList = contact.getGroups();

    app.goTo().HomePage();
    app.contact().removeFromGroup(contact, group);
    Groups afterList = contact.getGroups();

    assertThat(afterList, equalTo(beforeList.without(group)));
  }
}
