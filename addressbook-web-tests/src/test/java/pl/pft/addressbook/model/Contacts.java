package pl.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.*;

public class Contacts extends ForwardingSet<ContactData> {

  private Set<ContactData> delegate;

  public Contacts(Contacts contact) {
    this.delegate = new HashSet<ContactData>(contact.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<ContactData>();
  }

  public Contacts(Collection<ContactData> contacts) {
    this.delegate = new HashSet<ContactData>(contacts);
  }

  @Override
  protected Set<ContactData> delegate() {
    return delegate;
  }

  public Contacts sort() {
    List<ContactData> result = new ArrayList<ContactData>(this);
    result.sort((c1, c2) -> Integer.compare(c1.getId(), c2.getId()));
    return new Contacts(result);
  }

  public Contacts withAdded(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    contacts.sort();
    return contacts;
  }

  public Contacts without(ContactData contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    contacts.sort();
    return contacts;
  }
}
