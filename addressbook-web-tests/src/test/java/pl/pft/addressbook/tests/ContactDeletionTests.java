package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactsCount();
    if( ! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("tmp", "tmp", "tmp@email.address", "[none]"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().gotoContactHomePageAfterDeletion();
    int after = app.getContactHelper().getContactsCount();
    if(before == 0) {
      Assert.assertEquals(after, before);
    } else {
      Assert.assertEquals(after, before - 1);
    }
  }
}
