
package pacc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Add complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Add">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="number1" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="number2" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Add", propOrder = {
    "number1",
    "number2"
})
public class Add {

    protected double number1;
    protected double number2;

    /**
     * Gets the value of the number1 property.
     * 
     */
    public double getNumber1() {
        return number1;
    }

    /**
     * Sets the value of the number1 property.
     * 
     */
    public void setNumber1(double value) {
        this.number1 = value;
    }

    /**
     * Gets the value of the number2 property.
     * 
     */
    public double getNumber2() {
        return number2;
    }

    /**
     * Sets the value of the number2 property.
     * 
     */
    public void setNumber2(double value) {
        this.number2 = value;
    }

}
