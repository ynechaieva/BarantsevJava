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
    app.goTo().HomePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address").withGroup("[none]"), true);
    }
  }

  @Test (enabled = true)
  public void testContactModification(){

    Contacts beforeList = app.contact().all();
    ContactData modifiedContact = beforeList.iterator().next();
    ContactData newcontact = new ContactData()
            .withId(modifiedContact.getId()).withFirstName("testUser_FN").withhLastName("testUser_LN").withEmail("trst@email.address");
    app.contact().modify(newcontact);
    Contacts afterList = app.contact().all();

    assertThat(afterList.size(), equalTo(beforeList.size()));
    assertThat(afterList, equalTo(beforeList.without(modifiedContact).withAdded(newcontact)));

  }


}
