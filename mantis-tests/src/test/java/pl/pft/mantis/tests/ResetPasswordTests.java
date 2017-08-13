package pl.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.pft.mantis.model.MailMessage;
import ru.lanwen.verbalregex.VerbalExpression;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ResetPasswordTests extends  TestBase {

  @DataProvider
  public Object[][] provide() throws InterruptedException, IOException, MessagingException {
    Map<String, String> dictionary = new HashMap<String, String>();

    long now = System.currentTimeMillis();
    String user = String.format("user%s", now);
    String email = String.format("user%s@localhost", now);
    String password = "password";
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, user, password);
    dictionary.put(user, password);
    return new Object[][] {{dictionary}};
  }

 // @Test(dataProvider = "provide")
  @Test
  public void testResetUserPassword() throws IOException, InterruptedException, MessagingException {
    String username = "user1502653617378";
    String password = "password";
    String email = String.format("%s@localhost", username);
    app.james().drainEmail(username, password);
    app.registration().loginThroughWeb("administrator", "root");
    app.registration().resetUserPassword(username);
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 100000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    String newpassword = username;
    app.registration().changePassword(confirmationLink, username, newpassword);

    app.registration().loginThroughWeb(username, newpassword);
    Assert.assertTrue(app.registration().isLoggedOnWebAs(username));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter( (m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

}
