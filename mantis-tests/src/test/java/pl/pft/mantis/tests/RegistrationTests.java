package pl.pft.mantis.tests;


import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testBasicRegistration() {
    app.registration().start("user1", "user1@localhost.localdomain");
  }
}
