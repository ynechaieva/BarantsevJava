package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address"), true);
    }
  }

  @Test (enabled = true)
  public void testContactModification(){

    Contacts beforeList = app.db().contacts();
    ContactData modifiedContact = beforeList.iterator().next();
    ContactData newcontact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("testUser_FN").withMiddleName("").withhLastName("testUser_LN").withNickname("").withTitle("").withCompany("")
            .withHomePhone("").withMobilePhone("").withWorkPhone("").withEmail2("").withEmail3("").withEmail("trst@email.address").withFax("").withHomepage("").withAddress("");
    app.goTo().HomePage();
    app.contact().modify(newcontact);

    assertThat(app.contact().count(), equalTo(beforeList.size()));
    Contacts afterList = app.db().contacts();
    assertThat(afterList, equalTo(beforeList.without(modifiedContact).withAdded(newcontact)));
    verifyContactListInUI();
  }

}
