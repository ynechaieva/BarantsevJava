package pl.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation(){
    app.goTo().HomePage();
    Contacts beforeList = app.contact().all();
    File photo = new File("src/test/resources/raccoon.jpg");
    ContactData newcontact = new ContactData()
            .withFirstName("testUser2_FN").withhLastName("testUser2_LN").withEmail("testUser2@email.address").withGroup("[none]").withPhoto(photo);
    app.contact().create(newcontact, true);

    assertThat(app.contact().count(), equalTo(beforeList.size() + 1));
    Contacts afterList = app.contact().all();
    assertThat(afterList, equalTo(beforeList.withAdded(newcontact.withId(afterList.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir() {
    File dir = new File(".");
    System.out.println(dir.getAbsoluteFile());
    File photo = new File("src/test/resources/raccoon.jpg");
    System.out.println(photo.getAbsoluteFile());
    System.out.println(photo.exists());
  }

}
