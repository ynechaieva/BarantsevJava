package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("tmp", "tmp", "tmp@email.address", "[none]"), true);
    }
    int before = app.getContactHelper().getContactsCount();
    app.getContactHelper().initContactModification(before - 1);
    app.getContactHelper().fillContactForm(new ContactData("Contact: updated", "testUser1_LN", "testUser1@email.address", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoContactHomePage();
    int after = app.getContactHelper().getContactsCount();
    Assert.assertEquals(after, before);
  }
}
