package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd){
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("GROUPS"));
  }

  public void gotoContactPage(){click(By.linkText("ADD_NEW"));}

  public void gotoHomePage(){click(By.linkText("HOME"));}

}
