
package co.simplon.java.workshop.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour createFoodAttribute complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="createFoodAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="energy" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="prot" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fat" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="carb" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createFoodAttribute", propOrder = {
    "categoryName",
    "name",
    "energy",
    "prot",
    "fat",
    "carb"
})
public class CreateFoodAttribute {

    protected String categoryName;
    protected String name;
    protected int energy;
    protected int prot;
    protected int fat;
    protected int carb;

    /**
     * Obtient la valeur de la propri�t� categoryName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * D�finit la valeur de la propri�t� categoryName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryName(String value) {
        this.categoryName = value;
    }

    /**
     * Obtient la valeur de la propri�t� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * D�finit la valeur de la propri�t� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propri�t� energy.
     * 
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * D�finit la valeur de la propri�t� energy.
     * 
     */
    public void setEnergy(int value) {
        this.energy = value;
    }

    /**
     * Obtient la valeur de la propri�t� prot.
     * 
     */
    public int getProt() {
        return prot;
    }

    /**
     * D�finit la valeur de la propri�t� prot.
     * 
     */
    public void setProt(int value) {
        this.prot = value;
    }

    /**
     * Obtient la valeur de la propri�t� fat.
     * 
     */
    public int getFat() {
        return fat;
    }

    /**
     * D�finit la valeur de la propri�t� fat.
     * 
     */
    public void setFat(int value) {
        this.fat = value;
    }

    /**
     * Obtient la valeur de la propri�t� carb.
     * 
     */
    public int getCarb() {
        return carb;
    }

    /**
     * D�finit la valeur de la propri�t� carb.
     * 
     */
    public void setCarb(int value) {
        this.carb = value;
    }

}
