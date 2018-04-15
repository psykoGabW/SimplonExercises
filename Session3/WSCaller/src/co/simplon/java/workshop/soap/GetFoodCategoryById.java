
package co.simplon.java.workshop.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour getFoodCategoryById complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="getFoodCategoryById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="foodCategoryId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getFoodCategoryById", propOrder = {
    "foodCategoryId"
})
public class GetFoodCategoryById {

    protected int foodCategoryId;

    /**
     * Obtient la valeur de la propriété foodCategoryId.
     * 
     */
    public int getFoodCategoryId() {
        return foodCategoryId;
    }

    /**
     * Définit la valeur de la propriété foodCategoryId.
     * 
     */
    public void setFoodCategoryId(int value) {
        this.foodCategoryId = value;
    }

}
