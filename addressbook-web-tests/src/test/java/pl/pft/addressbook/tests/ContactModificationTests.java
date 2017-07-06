package pl.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if(!app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("tmp", "tmp", "tmp@email.address", "[none]"), true);
    }
    List<ContactData> beforeList = app.getContactHelper().getContactList();
    app.getContactHelper().initContactModification(beforeList.size() - 1);
    ContactData newcontact = new ContactData(beforeList.get(beforeList.size() - 1).getId(), "testUser3_LN", "testUser13email.address", null, null);
    app.getContactHelper().fillContactForm(newcontact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().gotoContactHomePage();
    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size());

    beforeList.remove(beforeList.size() - 1);
    beforeList.add(newcontact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }
}
