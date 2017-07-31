package pl.pft.addressbook.model;


import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "middlename")
  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "nickname")
  private String nickname;

  @Expose
  @Column(name = "title")
  private String title;

  @Expose
  @Column(name = "company")
  private String company;

  @Expose
  @Column(name = "address")
  @Type(type="text")
  private String address;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "fax")
  @Type(type = "text")
  private String fax;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage;

  @Expose
  @Transient
  private int bDay;

  @Expose
  @Transient
  private int bMonth;

  @Expose
  @Transient
  private int bYear;

  /*@Expose
  @Transient
  private String group; */

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id")
          , inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Expose
  @Transient
  private String photo;

  public File getPhoto() {
    return new File(photo);
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    if (middleName != null ? !middleName.equals(that.middleName) : that.middleName != null) return false;
    if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
    if (nickname != null ? !nickname.equals(that.nickname) : that.nickname != null) return false;
    if (title != null ? !title.equals(that.title) : that.title != null) return false;
    if (company != null ? !company.equals(that.company) : that.company != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (fax != null ? !fax.equals(that.fax) : that.fax != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
    if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;
    return homepage != null ? homepage.equals(that.homepage) : that.homepage == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
    result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (title != null ? title.hashCode() : 0);
    result = 31 * result + (company != null ? company.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (fax != null ? fax.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (email2 != null ? email2.hashCode() : 0);
    result = 31 * result + (email3 != null ? email3.hashCode() : 0);
    result = 31 * result + (homepage != null ? homepage.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", title='" + title + '\'' +
            ", company='" + company + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", fax='" + fax + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", homepage='" + homepage + '\'' +
            ", bDay=" + bDay +
            ", bMonth=" + bMonth +
            ", bYear=" + bYear +
            ", photo='" + photo + '\'' +
            '}';
  }

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
  public Groups getGroups() {
    return new Groups(groups);
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
