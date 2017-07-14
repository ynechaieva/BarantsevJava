package pl.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp").withEmail("tmp@email.address").withGroup("[none]").withHomePhone("111").withMiddleName("222").withMobilePhone("333"), true);
    }
  }

  @Test
  public void testContactPhones() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHomePhone(), equalTo(contactInfoFromEditForm.getHomePhone()));
    assertThat(contact.getMobilePhone(), equalTo(contactInfoFromEditForm.getMobilePhone()));
    assertThat(contact.getWorkPhone(), equalTo(contactInfoFromEditForm.getWorkPhone()));
  }

}
