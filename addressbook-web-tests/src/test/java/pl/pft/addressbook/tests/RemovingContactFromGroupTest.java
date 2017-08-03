package pl.pft.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;

import javax.xml.crypto.Data;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemovingContactFromGroupTest extends TestBase{

  @BeforeTest
  public void ensurePreconditions() {
    if(app.db().groups().size() == 0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("group").withHeader("header").withFooter("footer"));
    }

    if(app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("contact").withhLastName("last name").withEmail("contact@email.address"), true);
    }
  }

  @DataProvider
  public Object[][] provide() {
    HashMap<GroupData, ContactData> dictionary = new HashMap<GroupData, ContactData>();
    ContactData contact = app.db().contacts().iterator().next();

    if(contact.getGroups().size() != 0) {
      dictionary.put(contact.getGroups().iterator().next(), contact);
    } else {
      GroupData group = app.db().groups().iterator().next();
      contact.inGroup(group);
      app.goTo().HomePage();
      app.contact().addContactToGroup(contact, group);
      dictionary.put(group, contact);
    }
    return new Object[][] {{dictionary}};
  }

  @Test(dataProvider = "provide")
  public void testRemovingContactFromGroup(HashMap<GroupData, ContactData> dictionary) {

    for (HashMap.Entry<GroupData, ContactData> entry : dictionary.entrySet()) {
      GroupData group = entry.getKey();
      ContactData contact = entry.getValue();

      Groups beforeGroups = contact.getGroups();
      app.goTo().HomePage();
      app.contact().removeFromGroup(contact, group);
      Groups afterGroups = contact.getGroups();

      assertThat(afterGroups, equalTo(beforeGroups.without(group)));
    }
  }
}
