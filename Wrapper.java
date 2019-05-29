
import java.lang.reflect.Array;
import java.util.LinkedList;
/**
 * a class that that has-a LinkedList<String> and delegates methods to it,
 */
public class Wrapper {


    /* the class LinkedList that on her we use all the LinkedList methods */
    private LinkedList<String> myLinkedList;

    /**
     * Constructs a new, empty string LinkedList
     */
    public Wrapper(){
        myLinkedList = new LinkedList<>();
    }

    /**
     * adds a string to myLinkedList
     * @param string the string we want to add
     */
    public void add(String string){
        myLinkedList.add(string);
    }

    /**
     * delete a string from MyLinkedList
     * @param string the string we want to delete
     */
    public void delete(String string){
        myLinkedList.remove(string);
    }

    /**
     *
     * @return myLinkedList size
     */
    public int size(){
        return myLinkedList.size();
    }

    /**
     * checks if a cartoon string is in myLinkedList
     * @return true if so,false otherwise
     */
    public boolean contains(String string){
        return myLinkedList.contains(string);
    }

    /**
     *
     * @param index the index of the value we want to return
     * @return the value of the given index in myLinkedList
     */
    public String getValue(int index){
        return myLinkedList.get(index);
    }

    /**
     * checks if myLinkedList is empty
     * @return true if so / false otherwise
     */
    public boolean isEmpty(){
        return myLinkedList.isEmpty();
    }

    /**
     * remove and returns the firest element from my linked list
     * @return the first element in my linked list
     */
    public String pop(){
        return myLinkedList.pop();
    }

}
