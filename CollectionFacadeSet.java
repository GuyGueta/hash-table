import java.util.TreeSet;

/**
 *Wraps an underlying Collection and serves to both simplify its API and give it a common type with the
 * implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {
    protected java.util.Collection<java.lang.String> collection;

    /**
     * Creates a new facade wrapping the specified collection.
     * @param collection
     */
    public CollectionFacadeSet(java.util.Collection<java.lang.String> collection){
        rebootSet(collection);
        this.collection = collection;
    }


    /* reboots the collection as a set , and by that removing duplicate values*/
    private void rebootSet(java.util.Collection<java.lang.String> collection){
        TreeSet<String> temporarySet = new TreeSet<>();
        temporarySet.addAll(collection);
        collection.clear();
        collection.addAll(temporarySet);
    }

    /**
     *
     * @param searchVal Value to search for
     * @return True iff searchVal is found in the set false otherwise
     */
    @Override
    public boolean contains(java.lang.String searchVal){
        return collection.contains(searchVal);
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue New value to add to the set
     * @return False iff newValue already exists in the set,true otherwise
     */
    @Override
    public boolean add(String newValue) {
        if (collection.contains(newValue)){
            return false;
        }
        collection.add(newValue);
        return true;
    }

    /**
     * size in interface supplied.SimpleSet
     * @return The number of elements currently in the set
     */
    @Override
    public int size() {
        return collection.size();
    }

    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True iff toDelete is found and deleted /false otherwise
     */
    @Override
    public boolean delete(String toDelete) {
        if(!collection.contains(toDelete)){
            return false;
        }
        collection.remove(toDelete);
        return true;
    }
}
