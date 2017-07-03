package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactsCount();
    app.getContactHelper().createContact(new ContactData("testUser1_FN", "testUser1_LN", "testUser1@email.address", "[none]"), true);
    int after = app.getContactHelper().getContactsCount();
    Assert.assertEquals(after, before + 1);
  }

}
