/**
 * a class that contains string object or null
 */
public class HashString {

    /* the content of the object */
    private String value;

    /**
     * Constructs a new object with a string in it
     * @param string the content of the object
     */
    public HashString(String string){
        value = string;
    }

    /**
     *
     * @return the value of the object
     */
    public String getValue() {
        return value;
    }

    /**
     * sets the value of the object
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }
}
