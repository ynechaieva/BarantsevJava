package pl.pft.addressbook.tests;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pl.pft.addressbook.appmanager.ApplicationManager;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  public void verifyGroupListInUI() {
    Groups dbGroups = app.db().groups();
    Groups uiGroups = app.group().all();
    assertThat(uiGroups, equalTo(dbGroups.stream()
            .map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
            .collect(Collectors.toSet())));
  }

}
