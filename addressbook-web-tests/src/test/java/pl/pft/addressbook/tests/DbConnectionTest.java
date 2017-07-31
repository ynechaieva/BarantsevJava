package pl.pft.addressbook.tests;


import org.testng.annotations.Test;
import pl.pft.addressbook.model.ContactData;
import pl.pft.addressbook.model.Contacts;
import pl.pft.addressbook.model.GroupData;
import pl.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name")).withFooter(rs.getString("group_header"))
                .withHeader(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);

    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
    }
  }

    @Test
    public void testDbConnectionContacts() {
      Connection conn = null;
      try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select id, firstname, middlename, lastname, nickname, company, title, address, home, mobile, work, fax, email, " +
                "email2, email3, homepage from addressbook");
        Contacts contacts = new Contacts();
        while (rs.next()) {
          contacts.add(new ContactData().withId(rs.getInt("id")).withFirstName(rs.getString("firstname")).withMiddleName(rs.getString("middlename"))
                  .withhLastName(rs.getString("lastname")).withNickname(rs.getString("nickname")).withCompany(rs.getString("company"))
                  .withTitle(rs.getString("title")).withAddress(rs.getString("address")).withHomePhone(rs.getString("home"))
                  .withMobilePhone(rs.getString("mobile")).withWorkPhone(rs.getString("work")).withFax(rs.getString("fax"))
                  .withEmail(rs.getString("email")).withEmail2(rs.getString("email2")).withEmail3(rs.getString("email3")).withHomepage(rs.getString("homepage")));
        }
        rs.close();
        st.close();
        conn.close();
        System.out.println(contacts);

      } catch (SQLException ex) {
        System.out.println("SQLException: " + ex.getMessage());
      }
    }
}
