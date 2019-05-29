import java.util.Objects;

/**
 * a hash-set based on chaining. that is implicating open hash  Open hashing is a hashing scheme which allows
 * several items to be hashed to the same cell.
 */
public class OpenHashSet extends SimpleHashSet {
    /* the hash table that we work with */
    private Wrapper[] openHashTable;


    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor The upper load factor of the hash table.
     * @param lowerLoadFactor The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
       super(upperLoadFactor,lowerLoadFactor);
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     *
     * @param data an Array of strings we are placing in the hash table
     */
    public OpenHashSet(java.lang.String[] data) {
        buildHashTable(INITIAL_CAPACITY);
        upLoadFactor = DEFAULT_UPPER_BOUND;
        lowLoadFactor = DEFAULT_LOWER_BOUND;
        addData(data);
    }


    /*
     * initialize the initial hash table that we gonna work with Wrapper objects in every cell of
     * the Array.
     */
    protected void buildHashTable(int capacity) {
        openHashTable = new Wrapper[capacity];
        for (int i = 0; i < openHashTable.length; i++) {
            openHashTable[i] = new Wrapper();
        }
    }

    /**
     * calculate for us the location of the string in our hash table by using the hash code and the hashCode
     * calculation for open hash set
     *
     * @param hashCode the hash code of the word we check
     * @return
     */
    protected int clamp(int hashCode) {
        if (capacity() == CHANGE){
            return DEFAULT_NAM;
        }
        return hashCode & (capacity() - CHANGE);
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set
     */
    public boolean contains(java.lang.String searchVal) {
        int hashValue = clamp(searchVal.hashCode());
        return openHashTable[hashValue].contains(searchVal);
    }

    /**
     * Add a specified element to the set if it's not already in it
     *
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set
     */
    public boolean add(java.lang.String newValue) {
        int hashIndex;
        if(rehashFlag) {
            hashIndex = clamp(newValue.hashCode());
            openHashTable[hashIndex].add(newValue);
            numberOfElements++;
            return true;
        }
        checkUpperBound();
        hashIndex = clamp(newValue.hashCode());
        if (!openHashTable[hashIndex].contains(newValue)){
            openHashTable[hashIndex].add(newValue);
            numberOfElements++;
            return true;
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted/false other wise
     */
    public boolean delete(java.lang.String toDelete) {
        int hashIndex = clamp(toDelete.hashCode());
        if (openHashTable[hashIndex].contains(toDelete)) {
            numberOfElements--;
            openHashTable[hashIndex].delete(toDelete);
            checkLowerBound();
            return true;
        }
        return false;
    }


    /**
     * inserting all elements from the “old” table into the new one.depends on the rehash case we reduce or
     * enlargement
     *
     * @param rehashCase a string that is reduce or enlargement
     */
    public void reHash(String rehashCase) {
        numberOfElements = DEFAULT_NAM;
        rehashFlag = true;
        Wrapper[] oldHashTable = openHashTable;
        buildNewHashTable(rehashCase);
        for (Wrapper cell : oldHashTable) {
            if (cell == null) {
                continue;
            }
            for (int i = 0; i < cell.size(); i++) {
                add(cell.getValue(i));
            }
        }
        rehashFlag = false;
    }
}








