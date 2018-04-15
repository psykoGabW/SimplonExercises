
package co.simplon.java.workshop.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.simplon.java.workshop.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetFoodCategoryById_QNAME = new QName("http://soap.workshop.java.simplon.co/", "getFoodCategoryById");
    private final static QName _GetFoodCategoryListByName_QNAME = new QName("http://soap.workshop.java.simplon.co/", "getFoodCategoryListByName");
    private final static QName _GetFoodCategoryListByNameResponse_QNAME = new QName("http://soap.workshop.java.simplon.co/", "getFoodCategoryListByNameResponse");
    private final static QName _CreateFoodCategoryResponse_QNAME = new QName("http://soap.workshop.java.simplon.co/", "createFoodCategoryResponse");
    private final static QName _GetFoodCategoryByIdResponse_QNAME = new QName("http://soap.workshop.java.simplon.co/", "getFoodCategoryByIdResponse");
    private final static QName _CreateFoodCategory_QNAME = new QName("http://soap.workshop.java.simplon.co/", "createFoodCategory");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.simplon.java.workshop.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateFoodCategory }
     * 
     */
    public CreateFoodCategory createCreateFoodCategory() {
        return new CreateFoodCategory();
    }

    /**
     * Create an instance of {@link GetFoodCategoryById }
     * 
     */
    public GetFoodCategoryById createGetFoodCategoryById() {
        return new GetFoodCategoryById();
    }

    /**
     * Create an instance of {@link GetFoodCategoryListByNameResponse }
     * 
     */
    public GetFoodCategoryListByNameResponse createGetFoodCategoryListByNameResponse() {
        return new GetFoodCategoryListByNameResponse();
    }

    /**
     * Create an instance of {@link GetFoodCategoryListByName }
     * 
     */
    public GetFoodCategoryListByName createGetFoodCategoryListByName() {
        return new GetFoodCategoryListByName();
    }

    /**
     * Create an instance of {@link CreateFoodCategoryResponse }
     * 
     */
    public CreateFoodCategoryResponse createCreateFoodCategoryResponse() {
        return new CreateFoodCategoryResponse();
    }

    /**
     * Create an instance of {@link GetFoodCategoryByIdResponse }
     * 
     */
    public GetFoodCategoryByIdResponse createGetFoodCategoryByIdResponse() {
        return new GetFoodCategoryByIdResponse();
    }

    /**
     * Create an instance of {@link FoodCategory }
     * 
     */
    public FoodCategory createFoodCategory() {
        return new FoodCategory();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFoodCategoryById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "getFoodCategoryById")
    public JAXBElement<GetFoodCategoryById> createGetFoodCategoryById(GetFoodCategoryById value) {
        return new JAXBElement<GetFoodCategoryById>(_GetFoodCategoryById_QNAME, GetFoodCategoryById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFoodCategoryListByName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "getFoodCategoryListByName")
    public JAXBElement<GetFoodCategoryListByName> createGetFoodCategoryListByName(GetFoodCategoryListByName value) {
        return new JAXBElement<GetFoodCategoryListByName>(_GetFoodCategoryListByName_QNAME, GetFoodCategoryListByName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFoodCategoryListByNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "getFoodCategoryListByNameResponse")
    public JAXBElement<GetFoodCategoryListByNameResponse> createGetFoodCategoryListByNameResponse(GetFoodCategoryListByNameResponse value) {
        return new JAXBElement<GetFoodCategoryListByNameResponse>(_GetFoodCategoryListByNameResponse_QNAME, GetFoodCategoryListByNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFoodCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "createFoodCategoryResponse")
    public JAXBElement<CreateFoodCategoryResponse> createCreateFoodCategoryResponse(CreateFoodCategoryResponse value) {
        return new JAXBElement<CreateFoodCategoryResponse>(_CreateFoodCategoryResponse_QNAME, CreateFoodCategoryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFoodCategoryByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "getFoodCategoryByIdResponse")
    public JAXBElement<GetFoodCategoryByIdResponse> createGetFoodCategoryByIdResponse(GetFoodCategoryByIdResponse value) {
        return new JAXBElement<GetFoodCategoryByIdResponse>(_GetFoodCategoryByIdResponse_QNAME, GetFoodCategoryByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFoodCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap.workshop.java.simplon.co/", name = "createFoodCategory")
    public JAXBElement<CreateFoodCategory> createCreateFoodCategory(CreateFoodCategory value) {
        return new JAXBElement<CreateFoodCategory>(_CreateFoodCategory_QNAME, CreateFoodCategory.class, null, value);
    }

}
