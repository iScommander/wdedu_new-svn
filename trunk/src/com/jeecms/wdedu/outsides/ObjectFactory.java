
package com.jeecms.wdedu.outsides;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.jeecms.wdedu.outsides package. 
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

    private final static QName _RegistUserResponse_QNAME = new QName("http://webservices.cms.jeecms.com/", "registUserResponse");
    private final static QName _SelectPay_QNAME = new QName("http://webservices.cms.jeecms.com/", "selectPay");
    private final static QName _SelectPayResponse_QNAME = new QName("http://webservices.cms.jeecms.com/", "selectPayResponse");
    private final static QName _RegistUser_QNAME = new QName("http://webservices.cms.jeecms.com/", "registUser");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.jeecms.wdedu.outsides
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SelectPayResponse }
     * 
     */
    public SelectPayResponse createSelectPayResponse() {
        return new SelectPayResponse();
    }

    /**
     * Create an instance of {@link RegistUser }
     * 
     */
    public RegistUser createRegistUser() {
        return new RegistUser();
    }

    /**
     * Create an instance of {@link SelectPay }
     * 
     */
    public SelectPay createSelectPay() {
        return new SelectPay();
    }

    /**
     * Create an instance of {@link RegistUserResponse }
     * 
     */
    public RegistUserResponse createRegistUserResponse() {
        return new RegistUserResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.cms.jeecms.com/", name = "registUserResponse")
    public JAXBElement<RegistUserResponse> createRegistUserResponse(RegistUserResponse value) {
        return new JAXBElement<RegistUserResponse>(_RegistUserResponse_QNAME, RegistUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPay }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.cms.jeecms.com/", name = "selectPay")
    public JAXBElement<SelectPay> createSelectPay(SelectPay value) {
        return new JAXBElement<SelectPay>(_SelectPay_QNAME, SelectPay.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SelectPayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.cms.jeecms.com/", name = "selectPayResponse")
    public JAXBElement<SelectPayResponse> createSelectPayResponse(SelectPayResponse value) {
        return new JAXBElement<SelectPayResponse>(_SelectPayResponse_QNAME, SelectPayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservices.cms.jeecms.com/", name = "registUser")
    public JAXBElement<RegistUser> createRegistUser(RegistUser value) {
        return new JAXBElement<RegistUser>(_RegistUser_QNAME, RegistUser.class, null, value);
    }

}
