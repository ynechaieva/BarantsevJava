package pl.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void initContactCreation(){
    click(By.linkText("ADD_NEW"));
  }

  public void gotoContactHomePage(){
    click(By.linkText("home page"));
  }
  public void gotoContactHomePageAfterDeletion() {click(By.linkText("HOME"));}

  public void fillContactForm(ContactData contact, boolean creation){
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("email"), contact.getEmail());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByValue(contact.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")) );
    }
  }

  public void submitContactCreation(){
    click(By.name("submit"));
  }

  public void selectContact(int index){
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public String getContactIdByIndex(int index) {
    String id = wd.findElements(By.name("selected[]")).get(index).getAttribute("id");
    return id;
  }

  public void deleteSelectedContact(){
    click(By.xpath("//input[contains(@value, 'DELETE')]"));
    allertAccept();
  }

  public void initContactModification(int index) {

    selectContact(index);
    String id = getContactIdByIndex(index);
    click(By.xpath("//a[contains(@href,'id=" + id +"')]/img[contains(@title, 'EDIT')]"));
  }

  public void submitContactModification() { click(By.name("update")); }

  public void createContact(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    gotoContactHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactsCount() {
   return  wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    //int rowCount = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry'")).size();
    //int columnCount = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']/td")).size();
    List<WebElement> trElements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));

    for(WebElement trElement : trElements) {
      List<WebElement> tdElements = trElement.findElements(By.tagName("td"));
      int id = Integer.parseInt(tdElements.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = tdElements.get(1).getText();
      String firstname = tdElements.get(2).getText();

      ContactData contact = new ContactData(id, firstname, lastname, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
