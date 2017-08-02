package pl.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroupTest extends TestBase {

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
  }

  @Test
  public void testAddingContactToGroup() {
    ContactData contact = app.db().contacts().iterator().next();
    contact.removeAllGroups();
    Groups beforeList = contact.getGroups();
    GroupData group = app.db().groups().iterator().next();
    app.goTo().HomePage();
    app.contact().addToGroup(contact, group);
    Groups afterList = contact.getGroups();

    assertThat(afterList, equalTo(beforeList.withAdded(group)));
  }
}
