package co.simplon.java.workshop.soap.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class FoodAttribute {
  
  @XmlElement(name = "id")
  private final int id;
  @XmlElement(name = "category")
  private final FoodCategory category;
  @XmlElement(name = "name")
  private final String name;
  @XmlElement(name = "energy")
  private final int energy;
  @XmlElement(name = "prot")
  private final int prot;
  @XmlElement(name = "fat")
  private final int fat;
  @XmlElement(name = "carb")
  private final int carb;

  public FoodAttribute(int id, FoodCategory category, String name, int energy, int prot, int fat, int carb) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.energy = energy;
    this.prot = prot;
    this.fat = fat;
    this.carb = carb;
  }
  
  public int getId() {
    return this.id;
  }

  public FoodCategory getCategory() {
    return category;
  }

  public String getName() {
    return name;
  }

  public int getEnergy() {
    return energy;
  }

  public int getProt() {
    return prot;
  }

  public int getFat() {
    return fat;
  }

  public int getCarb() {
    return carb;
  }
  
  public String toString() {
    return name;
  }
}
