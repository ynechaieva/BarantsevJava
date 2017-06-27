package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation(){
    app.getContactHelper().createContact(new ContactData("testUser1_FN", "testUser1_LN", "testUser1@email.address", "[none]"), true);
  }

}
