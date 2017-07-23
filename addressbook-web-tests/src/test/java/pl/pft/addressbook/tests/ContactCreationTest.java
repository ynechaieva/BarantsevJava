package pl.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;


public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while(line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
      return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactData contact){
    app.goTo().HomePage();
    Contacts beforeList = app.contact().all();
    File photo = new File("src/test/resources/raccoon.jpg");
    contact.withPhoto(photo);
    app.contact().create(contact, true);

    assertThat(app.contact().count(), equalTo(beforeList.size() + 1));
    Contacts afterList = app.contact().all();
    assertThat(afterList, equalTo(beforeList.withAdded(contact.withId(afterList.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
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
