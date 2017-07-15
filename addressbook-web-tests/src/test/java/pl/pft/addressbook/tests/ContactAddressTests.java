package pl.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().HomePage();
    if(app.contact().all().size() == 0){
      app.contact().create(new ContactData().withFirstName("tmp").withhLastName("tmp")
              .withEmail("tmp@email.address").withEmail2("11@11").withEmail3("222@22.2").withGroup("[none]")
              .withHomePhone("+7 (111)").withMiddleName("222-22").withMobilePhone("333 33 46").withAddress("test street 48, city A"), true);
    }
  }

  @Test
  public void testContactAddress() {
    app.goTo().HomePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
  }
}
