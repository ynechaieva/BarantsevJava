package pl.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test (enabled = false)
  public void testContactCreation(){
    app.goTo().gotoHomePage();
    List<ContactData> beforeList = app.getContactHelper().getContactList();
    ContactData newcontact = new ContactData("testUser2_FN", "testUser2_LN", "testUser2@email.address", "[none]");
    app.getContactHelper().createContact(newcontact, true);
    List<ContactData> afterList = app.getContactHelper().getContactList();
    Assert.assertEquals(afterList.size(), beforeList.size() + 1);

    //newcontact.setId(afterList.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    beforeList.add(newcontact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    beforeList.sort(byId);
    afterList.sort(byId);
    Assert.assertEquals(afterList, beforeList);
  }

}
