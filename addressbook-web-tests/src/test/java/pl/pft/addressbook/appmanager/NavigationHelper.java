package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class NavigationHelper extends HelperBase{

  public NavigationHelper(WebDriver wd){
    super(wd);
  }

  public void GroupPage() {
    if(isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))){
      return;
    } else{
      click(By.linkText("GROUPS"));
    }
  }

  public void gotoContactPage(){
    if(isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("EDIT_ADD_ENTRY")
            && (isElementPresent(By.name("submit")) && wd.findElement(By.name("submit")).getText().equals("ENTER"))){
      return;
    } else{
      click(By.linkText("ADD_NEW"));
    }
  }

  public void HomePage() {
    if (isElementPresent(By.id("mainTable"))) {
      return;
    } else {
      click(By.linkText("HOME"));
    }
  }

}
