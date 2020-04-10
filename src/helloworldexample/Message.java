package helloworldexample;
/* =================================

author ankitrajprasad created on 10/04/20 
inside the package - helloworldexample 

=====================================*/


import javax.persistence.Entity;
import java.io.Serializable;


public class Message  implements Serializable {


    private Short id;
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Short getId() {
        return this.id;
    }

    public void setId(Short id) {
        this.id = id;
    }
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
