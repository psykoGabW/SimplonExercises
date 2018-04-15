
package co.simplon.java.workshop.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getFoodCategoryByIdResponse complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getFoodCategoryByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FoodCategory" type="{http://soap.workshop.java.simplon.co/}foodCategory" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFoodCategoryByIdResponse", propOrder = {
    "foodCategory"
})
public class GetFoodCategoryByIdResponse {

    @XmlElement(name = "FoodCategory")
    protected FoodCategory foodCategory;

    /**
     * Obtient la valeur de la propri�t� foodCategory.
     * 
     * @return
     *     possible object is
     *     {@link FoodCategory }
     *     
     */
    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    /**
     * D�finit la valeur de la propri�t� foodCategory.
     * 
     * @param value
     *     allowed object is
     *     {@link FoodCategory }
     *     
     */
    public void setFoodCategory(FoodCategory value) {
        this.foodCategory = value;
    }

}
