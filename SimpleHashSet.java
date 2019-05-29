/**
 * implementation of a hash set which  provides constant-time implementations of add(), contains(), delete(),
 * and size()
 */
public abstract class SimpleHashSet implements SimpleSet {

    /* the current number of elements in the table */
    protected int numberOfElements;

    /** the intial capacity of the hash talbe*/
    public final static int INITIAL_CAPACITY = 16;

    /* the upper bound of the load factor*/
    protected float upLoadFactor;

    /* the lower bound of the load factor*/
    protected float lowLoadFactor;

    /** the default lowerBound of the load factor*/
    public static final float DEFAULT_LOWER_BOUND = 0.25f;

    /** the default upperBound of the load factor*/
    public static final float DEFAULT_UPPER_BOUND = 0.75f;

    /* the currrent capacity of the hase table */
    private int capacity = INITIAL_CAPACITY;

    /* a constant string that represent a case of enlargement in rehash */
    protected final static String ENLARGE = "enlargem";

    /*  a constant string that represent a case of reduce*/
    protected final static String REDUCE = "reduce";

    /* a constant representing a default number */
    protected final static int DEFAULT_NAM = 0;

    /* a constant representing the int we Multiply or divide by when we change the hash table capacity */
    protected static int HashFactor = 2;

    /* a constant representing a change by one */
    protected final static int CHANGE = 1;

    /* a flag that indecates if we are rehashing or not */
    protected boolean rehashFlag = false;


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor  The upper load factor of the hash table.
     * @param lowerLoadFactor  The lower load factor of the hash table.
     */
    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        buildHashTable(INITIAL_CAPACITY);
        upLoadFactor = upperLoadFactor;
        lowLoadFactor = lowerLoadFactor;
    }

    /**
     *A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public SimpleHashSet(){
        buildHashTable(INITIAL_CAPACITY);
        upLoadFactor = DEFAULT_UPPER_BOUND;
        lowLoadFactor = DEFAULT_LOWER_BOUND;
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data an Array of strings we are placing in the hash table
     */
    public SimpleHashSet(java.lang.String[] data){
        buildHashTable(INITIAL_CAPACITY);
        upLoadFactor = DEFAULT_UPPER_BOUND;
        lowLoadFactor = DEFAULT_LOWER_BOUND;
        addData(data);
    }

    /**
     * Add a specified element to the set if it's not already in it
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public abstract boolean add(java.lang.String newValue);

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public abstract boolean contains(java.lang.String searchVal);

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted
     */
    public abstract boolean delete(java.lang.String toDelete);

    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        return numberOfElements;
    }

    /**
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return capacity;
    }

    /**
     * calculate for us the location of the string in our hash table by using the hash code
     * @param hashCode the hash code of the string we check
     * @return the index of the string in our hash table
     */
    protected abstract int clamp(int hashCode);

    /**
     * check if the load factor is crosing the upper bound
     * @return true if so /false otherwise
     */
    public void checkUpperBound(){
        float loadFactor = (float)(numberOfElements + CHANGE) / (float)capacity();
        if (loadFactor > upLoadFactor){
            reHash(ENLARGE);
        }

    }

    /**
     * check if the load factor is crosing the lower bound
     * @return true if so /false otherwise
     */
    public void checkLowerBound(){
        float loadFactor = (float)(numberOfElements) / (float)capacity();
        if (loadFactor < lowLoadFactor && capacity > 1){
            reHash(REDUCE);
        }
    }

    /**
     * inserting all elements from the “old” table into the new one.depends on the rehash case we reduce or
     * enlargement
     * @param rehashCase  a string that is reduce or enlargement
     */
    public abstract void reHash(String rehashCase);

    /**
     * adding data from a given array of strings to the hash table.
     * @param data the data we want to add to the hash table
     */
    public void addData(java.lang.String[] data){
        for (int i = 0; i <data.length ; i++) {
            add(data[i]);
        }
    }

    /**
     * sets the capacity of the hash table
     * @param newCapacity the new capacity that we are changing to
     */
    public void setCapacity(int newCapacity){
        capacity = newCapacity;
    }

    /*
     * initialize the initial hash table that we gonna work with
     */
    protected abstract void buildHashTable(int capacity);


    /**
     * reduce or enlarge the hase table and place the data again in the new hash table
     * @param rehashCase a String represents reduce or enlarge
     */
    public void buildNewHashTable(String rehashCase){
        switch (rehashCase) {
            case ENLARGE:
                setCapacity(capacity() * HashFactor);
                buildHashTable(capacity());
                break;
            case REDUCE:
                setCapacity(capacity() / HashFactor);
                buildHashTable(capacity());
                break;
        }
    }

    /**
     * @return Lower Load Factor
     */
    protected float getLowerLoadFactor(){
        return lowLoadFactor;
    }

    /**
     *
     * @return return upper Load Factor
     */
    protected float getUpperLoadFactor(){
        return upLoadFactor;
    }
}


