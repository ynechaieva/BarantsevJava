package pl.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

  private void selectContactById(int id) {
    wd.findElement(By.id(String.valueOf(id))).click();
  }

  public void deleteSelectedContact(){
    click(By.xpath("//input[contains(@value, 'DELETE')]"));
    allertAccept();
  }

  public void initContactModification(int id) {

    selectContactById(id);
    click(By.xpath("//a[contains(@href,'id=" + id +"')]/img[contains(@title, 'EDIT')]"));
  }

  public void submitContactModification() { click(By.name("update")); }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForm(contact, creation);
    submitContactCreation();
    gotoContactHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    gotoContactHomePageAfterDeletion();
  }

  public void modify(ContactData contact) {
    initContactModification(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    gotoContactHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactsCount() {
   return  wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if( contactCache != null ) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> trElements = wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr[@name='entry']"));

    for(WebElement trElement : trElements) {
      List<WebElement> tdElements = trElement.findElements(By.tagName("td"));
      int id = Integer.parseInt(tdElements.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = tdElements.get(1).getText();
      String firstname = tdElements.get(2).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstname).withhLastName(lastname));
    }
    return new Contacts(contactCache);
  }
}
