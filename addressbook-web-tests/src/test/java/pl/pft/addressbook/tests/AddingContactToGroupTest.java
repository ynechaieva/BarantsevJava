package pl.pft.addressbook.tests;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddingContactToGroupTest extends TestBase {

  @BeforeTest
  public void ensurePreconditions() {

    if(app.db().groups().size() == 0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("tmp group").withHeader("tmp header").withFooter("tmp footer"));
    }

    if(app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("contact").withhLastName("contact").withEmail("contact@email.address"), true);
    }
  }

  @DataProvider
  public Object[][] provide() {
    HashMap<GroupData, ContactData> dictionary = new HashMap<GroupData, ContactData>();
    ContactData contact = app.db().contacts().iterator().next();

    if(contact.getGroups().size() == 0) {
      dictionary.put(app.db().groups().iterator().next(), contact);
      return new Object[][] {{dictionary}};
    } else {
      /*
    groupsDB - list of the groups from DB
    groupFromDB - group from groupsDB
    groupC - group to which contact is already added
     */
      Groups groupsDB = app.db().groups();
      int size = contact.getGroups().size();
      for(GroupData groupFromDB : groupsDB) {
        int count = size;
        for(GroupData groupC: contact.getGroups()) {
          if( groupFromDB.getId() == groupC.getId() ) {
            break;
          }
          else {count -= 1;}
        }
        if(count == 0) {
          dictionary.put(groupFromDB, contact);
          return new Object[][] {{dictionary}};
        }
      }
      app.goTo().GroupPage();
      GroupData groupForTest = new GroupData().withName("groupForTest").withHeader("header").withFooter("footer");

      app.group().create(groupForTest);
      int newID = app.group().getDbIdForCreatedGroup(groupForTest);
      groupForTest.withId(newID);
      dictionary.put(groupForTest, contact);
      return new Object[][] {{dictionary}};
    }
  }

  @Test(dataProvider = "provide")
  public void testAddingContactToGroup(HashMap<GroupData, ContactData> dictionary) {

    for ( HashMap.Entry<GroupData, ContactData> entry : dictionary.entrySet()) {
      GroupData group = entry.getKey();
      ContactData contact = entry.getValue();
      Groups beforeGroups = contact.getGroups();
      contact.inGroup(group);

      app.goTo().HomePage();
      app.contact().addContactToGroup(contact, group);
      Groups afterGroups = contact.getGroups();

      assertThat(afterGroups, equalTo(beforeGroups.withAdded(group)));
    }
  }
}
