package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if(app.db().contacts().size() == 0){
      app.goTo().HomePage();
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address").withGroup("[none]"), true);
    }
  }

  @Test
  public void testContactDeletion(){

    app.goTo().HomePage();
    Contacts beforeList = app.db().contacts();
    ContactData deletedContact = beforeList.iterator().next();
    app.contact().delete(deletedContact);

    assertThat(app.contact().count(), equalTo(beforeList.size() - 1));
    Contacts afterList = app.db().contacts();
    assertThat(afterList, equalTo(beforeList.without(deletedContact)));
    verifyContactListInUI();
  }

}
