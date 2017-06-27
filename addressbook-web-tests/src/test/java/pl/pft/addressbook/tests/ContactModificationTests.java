package pl.pft.addressbook.tests;


import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("tmp", "tmp", "tmp@email.address", "[none]"), true);
    }
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Contact: updated", "testUser1_LN", "testUser1@email.address", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoContactHomePage();
  }
}
