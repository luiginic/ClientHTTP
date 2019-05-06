package data_collection;

public class GenericMessage {

    private String message;

    public GenericMessage(String s){
        this.message=s;
    }

    public void setMessage(String s){
        this.message=s;
    }

    public String getMessage() {
        return message;
    }
}
