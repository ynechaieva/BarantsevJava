package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @Test (enabled = false)
  public void testContactDeletion(){
    app.goTo().gotoHomePage();
    if( ! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("tmp", "tmp", "tmp@email.address", "[none]"), true);
    }
    List<ContactData> beforeList = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(beforeList.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().gotoContactHomePageAfterDeletion();
    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size() - 1);

    beforeList.remove(beforeList.size() - 1);
    Assert.assertEquals(afterList, beforeList);
  }
}
