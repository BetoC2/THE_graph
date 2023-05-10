package exceptions;

public class NullObjectReceivedException extends Exception {

    private Object receivedObject;

    public  NullObjectReceivedException(Object obj){
        super("NullObjectReceivedException");
        this.receivedObject = obj;
    }

    public String toString(){
        return getMessage() + "\nNull Object Received: " + this.receivedObject;
    }
}