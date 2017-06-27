package pl.pft.addressbook.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import pl.pft.addressbook.model.ContactData;


public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd){
    super(wd);
  }

  public void fillContactForm(ContactData contact, boolean creation){
    type(By.name("firstname"), contact.getFirstName());
    type(By.name("lastname"), contact.getLastName());
    type(By.name("email"), contact.getEmail());

    if(creation){
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else{
      Assert.assertFalse(isElementPresent(By.name("new_group")) );
    }
  }

  public void submitContactCreation(){
    click(By.name("submit"));
  }

  public void selectContact(){
    click(By.name("selected[]"));
  }

  public void deleteSelectedContact(){
    click(By.xpath("//input[contains(@value, 'DELETE')]"));
    allertAccept();
  }

  public void initContactModification() {
    click(By.xpath("//img[contains(@title, 'EDIT')]"));
  }

  public void submitContactModification() { click(By.name("update")); }
}
