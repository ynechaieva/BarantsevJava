package pl.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by ynech on 18/06/2017.
 */
public class ContactCreationTest {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.cssSelector("html")).click();
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  @Test
  public void testContactCreation(){
    gotoContactPage();
    fillContactForm(new ContactData("testUser1_FN", "testUser1_LN", "testUser1@email.address"));
    submitContactCreation();
    gotoHomePage();

  }

  private void gotoContactPage(){
    wd.findElement(By.linkText("ADD_NEW")).click();
  }

  private void fillContactForm(ContactData contact){
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(contact.getFirstName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(contact.getLastName());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(contact.getEmail());
  }

  private void submitContactCreation(){
    wd.findElement(By.name("submit")).click();
  }

  private void gotoHomePage(){
    wd.findElement(By.linkText("HOME")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }
}
