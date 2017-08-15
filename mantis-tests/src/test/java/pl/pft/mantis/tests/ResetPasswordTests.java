package pl.pft.mantis.tests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.pft.mantis.model.MailMessage;
import pl.pft.mantis.model.UserData;
import pl.pft.mantis.model.Users;
import ru.lanwen.verbalregex.VerbalExpression;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
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
    Users users = usersFromDB();
    UserData testuser = users.iterator().next();
    /*String username = "user1502653617378";
    String password = "password";
    String email = String.format("%s@localhost", username);*/

    String username = testuser.getUsername();
    String password = "password";
    String email = testuser.getEmail();
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

  private Users usersFromDB(){
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select id, username, email, password from mantis_user_table");
      Users users = new Users();
      while (rs.next()) {
        users.add(new UserData().withId(rs.getInt("id")).withUsername(rs.getString("username"))
                .withEmail(rs.getString("email")).withPassword(rs.getString("password")));
      }
      rs.close();
      st.close();
      conn.close();
      return users;
    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
    }
    return null;
  }

}
