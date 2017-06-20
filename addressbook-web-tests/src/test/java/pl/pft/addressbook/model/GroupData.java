package pl.pft.addressbook.model;

public class GroupData {
  private String name;
  private String header;
  private String footer;

  public GroupData(String name){
    this.name = name;
  }
  public GroupData(String name, String header, String footer) {
    this.name = name;
    this.header = header;
    this.footer = footer;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }
}
