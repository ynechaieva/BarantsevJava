package pl.pft.addressbook.model;

import pl.pft.addressbook.appmanager.ContactHelper;

/**
 * Created by ynech on 18/06/2017.
 */
public class ContactData {
  private int id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String nickname;
  private String title;
  private String company;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String fax;
  private String email;
  private String email2;
  private String email3;
  private String homepage;
  private int bDay;
  private int bMonth;
  private int bYear;
  private String group;


    public ContactData(String firstName, String lastName, String email, String group){
    this.id = Integer.MAX_VALUE;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.group = group;
  }

  public ContactData(int id, String firstName, String lastName, String email, String group){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.group = group;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName){
    this.firstName = firstName;
  }

  public void setMiddleName(String middleName){
    this.middleName = middleName;
  }

  public void setLastName(String lastName){
    this.lastName = lastName;
  }

  public void setNickname(String nickname){
    this.nickname = nickname;
  }

  public void setTitle(String title){
    this.title = title;
  }

  public void setCompany(String company){
    this.company = company;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public void setHomePhone(String homePhone){
    this.homePhone = homePhone;
  }

  public void setMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
  }

  public void setWorkPhone(String workPhone){
    this.workPhone = workPhone;
  }

  public void setFax(String fax){
    this.fax = fax;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setEmail2(String email2){
    this.email2 = email2;
  }

  public void setEmail3(String email3){
    this.email3 = email3;
  }

  public void setHomepage(String homepage){
    this.homepage = homepage;
  }

  public void setbDay(int bDay){
    this.bDay = bDay;
  }

  public void setbMonth(int bMonth){
    this.bMonth = bMonth;
  }

  public void setbYear(int bYear){
    this.bYear = bYear;
  }

  public void setGroup(String group) { this.group = group; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }

  public int getId() {
    return id;
  }

  public String getFirstName(){return this.firstName;}
  public String getMiddleName(){return this.middleName;}
  public String getLastName(){return this.lastName;}
  public String getNickname(){return this.nickname;}
  public String getTitle(){return this.title;}
  public String getCompany(){return this.company;}
  public String getAddress(){return this.address;}
  public String getHomePhone(){return this.homePhone;}
  public String getMobilePhone(){return this.mobilePhone;}
  public String getWorkPhone(){return this.workPhone;}
  public String getFax(){return this.fax;}
  public String getEmail(){return this.email;}
  public String getEmail2(){return this.email2;}
  public String getEmail3(){return this.email3;}
  public String getHomepage(){return this.homepage;}
  public int getbDay(){return this.bDay;}
  public int getbMonth(){return this.bMonth;}
  public int getbYear(){return this.bYear;}
  public String getGroup(){return this.group;}

}
