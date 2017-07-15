package pl.pft.addressbook.model;


public class ContactData {
  private int id = Integer.MAX_VALUE;
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
  private String allPhones;
  private String fax;
  private String email;
  private String email2;
  private String email3;
  private String allEmails;
  private String homepage;
  private int bDay;
  private int bMonth;
  private int bYear;
  private String group;

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName){
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName){
    this.middleName = middleName;
    return this;
  }

  public ContactData withhLastName(String lastName){
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickname(String nickname){
    this.nickname = nickname;
    return this;
  }

  public ContactData withTitle(String title){
    this.title = title;
    return this;
  }

  public ContactData withCompany(String company){
    this.company = company;
    return this;
  }

  public ContactData withAddress(String address){
    this.address = address;
    return this;
  }

  public ContactData withHomePhone(String homePhone){
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone){
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone){
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public ContactData withFax(String fax){
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email){
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2){
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3){
    this.email3 = email3;
    return this;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withHomepage(String homepage){
    this.homepage = homepage;
    return this;
  }

  public ContactData withbDay(int bDay){
    this.bDay = bDay;
    return this;
  }

  public ContactData withbMonth(int bMonth){
    this.bMonth = bMonth;
    return this;
  }

  public ContactData withbYear(int bYear){
    this.bYear = bYear;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
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
  public String getAllPhones() { return allPhones; }
  public String getFax(){return this.fax;}
  public String getEmail(){return this.email;}
  public String getEmail2(){return this.email2;}
  public String getEmail3(){return this.email3;}
  public String getAllEmails() { return allEmails; }
  public String getHomepage(){return this.homepage;}
  public int getbDay(){return this.bDay;}
  public int getbMonth(){return this.bMonth;}
  public int getbYear(){return this.bYear;}
  public String getGroup(){return this.group;}

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return middleName != null ? middleName.equals(that.middleName) : that.middleName == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
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

}
