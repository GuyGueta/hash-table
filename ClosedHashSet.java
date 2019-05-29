/**
 * a hash-set based on chaining. that is implicating the closed hash Closed hashing is a hashing scheme in
 * which each cell contains at most one item.
 */
public class ClosedHashSet extends SimpleHashSet {

    /* the hash table that we work with */
    private HashString[] closedHashTable;



    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor  The upper load factor of the hash table.
     * @param lowerLoadFactor  The lower load factor of the hash table.
     * */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
       super(upperLoadFactor,lowerLoadFactor);
    }

    /**
     *A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     * @param data an Array of strings we are placing in the hash table
     */
    public ClosedHashSet(java.lang.String[] data){
        buildHashTable(INITIAL_CAPACITY);
        upLoadFactor = DEFAULT_UPPER_BOUND;
        lowLoadFactor = DEFAULT_LOWER_BOUND;
        addData(data);
    }

    /*
     * initialize the initial hash table that we gonna work with
     */
    protected void buildHashTable(int capacity){
        closedHashTable = new HashString[capacity];
    }


    /**
     * calculate for us the location of the string in our hash table by using the hash code
     * @param hashCode the hase code of the string that we are checking
     * @return the index of the string in our hash table
     */
    public int clamp(int hashCode){
        return (hashCode)&(capacity() - CHANGE);
    }

    /**
     * Add a specified element to the set if it's not already in it
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) {
        if ( rehashFlag || !contains(newValue)) {
            checkUpperBound();
            int clampFactor;
            for (int i = 0; i < capacity(); i++){
                clampFactor = (i + (i * i)) / HashFactor;
                int hashIndex = clamp(newValue.hashCode() + clampFactor);
                if (checkCell(hashIndex)) {
                    closedHashTable[hashIndex] = new HashString(newValue);
                    numberOfElements++;
                    return true;
                }else {
                    if(closedHashTable[hashIndex].getValue() == null){
                        closedHashTable[hashIndex].setValue(newValue);
                        numberOfElements ++;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set false otherwise
     */
    public boolean contains (java.lang.String searchVal) {
        int clampFactor;
        for (int i = 0; i < capacity(); i++) {
            clampFactor = (i + (i * i)) / HashFactor;
            int hashIndex = clamp(searchVal.hashCode() + clampFactor);
            if (checkCell(hashIndex)){
                return false;
            }else if (closedHashTable[hashIndex].getValue() != null){
                if(closedHashTable[hashIndex].getValue().equals(searchVal)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted,false otherwise
     */
    public boolean delete (java.lang.String toDelete){
        if (contains(toDelete)) {
            int clampFactor;
            for (int i = 0; i < capacity(); i++) {
                clampFactor = (i + (i * i)) / HashFactor;
                int hashIndex = clamp(toDelete.hashCode() + clampFactor);
                if (!checkCell(hashIndex)){
                    if (closedHashTable[hashIndex].getValue() != null
                            && closedHashTable[hashIndex].getValue().equals(toDelete)){
                        numberOfElements --;
                        closedHashTable[hashIndex].setValue(null);
                        checkLowerBound();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
     * checks if a certion cell in the hash table is empty or occupied
     * @return true if so false otherwise
     */
    private boolean checkCell(int hashIndex){
        return closedHashTable[hashIndex] == null;
    }

    /**
     * inserting all elements from the “old” table into the new one.depends on the rehash case we reduce or
     * enlargement
     * @param rehashCase  a string that is reduce or enlarge
     */
    public void reHash(String rehashCase) {
        rehashFlag = true;
        numberOfElements = DEFAULT_NAM;
        HashString[] oldHashTable = closedHashTable;
        buildNewHashTable(rehashCase);
        for (HashString cell : oldHashTable) {
            if (cell != null && cell.getValue() != null) {
                add(cell.getValue());
            }
        }
        rehashFlag = false;
    }












}
