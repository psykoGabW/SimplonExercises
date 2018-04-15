package co.simplon.java.workshop.soap.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class FoodCategory {
  
  @XmlElement(name = "id")
  private final int id;
  @XmlElement(name = "name")
  private final String name;
  
  public FoodCategory(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  @Override
  public String toString() {
    return name;
  }
  
}
