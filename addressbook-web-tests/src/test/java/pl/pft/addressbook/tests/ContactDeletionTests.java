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
    app.goTo().HomePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address").withGroup("[none]"), true);
    }
  }

  @Test (enabled = true)
  public void testContactDeletion(){

    Contacts beforeList = app.contact().all();
    ContactData deletedContact = beforeList.iterator().next();
    app.contact().delete(deletedContact);

    assertThat(app.contact().count(), equalTo(beforeList.size() - 1));
    Contacts afterList = app.contact().all();
    assertThat(afterList, equalTo(beforeList.without(deletedContact)));
  }

}
