package pl.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by ynech on 20/06/2017.
 */
public class NavigationHelper extends HelperBase{

  public NavigationHelper(FirefoxDriver wd){
    super(wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("GROUPS"));
  }
}
