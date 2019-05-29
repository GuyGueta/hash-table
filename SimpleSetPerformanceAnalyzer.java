

import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * A class that compares the performance of different data structures by certain criteria
 */
public class SimpleSetPerformanceAnalyzer {

    /* a constant representing the number of sets in the analyzed sets array*/
    private static int NUMBER_OF_SETS = 5;

    /* a constant representing the index of open hash set in the analyzed sets array*/
    private static final int OPEN_HASH_SET_INDEX = 0;

    /* a constant representing the index of closed hash set in the analyzed sets array*/
    private static final int CLOESD_HASH_SET_INDEX = 1;

    /* a constant representing the index of tree set set in the analyzed sets array*/
    private static final int TREE_SET_INDEX = 2;

    /* a constant representing the index of Linked list set  in the analyzed sets array*/
    private static final int LINKED_LIST_SET_INDEX = 3;

    /* a constant representing the index of the simple hash set  in the analyzed sets array*/
    private static final int HASH_SET_INDEX = 4;

    /* a constant string that represents string OpenHashSet */
    private static final String OPEN_HASH = "OpenHashSet";

    /* a constant string that represents string ClosedHash */
    private static final String CLOSED_HASH = "ClosedHash";

    /* a constant string that represents string TreeSet */
    private static final String TREE_SET = "TreeSet";

    /* a constant string that represents the string LinkedListSet */
    private static final String LINKED_LIST = "LinkedListSet";

    /* a constant string that represents the string HashSet */
    private static final String HASH_SET = "HashSet";

    /* a constant string that represents the string default */
    private static final String DEFAULT_SET_NAME = " default";

    /*  a constant representing the number of warm up we are doing for each set except LinkedList*/
    private static final int WARM_UP_INSERTIONS_NOT_LINKED_LIST = 50000;

    /*  a constant representing the number of warm up we are doing for LinkedList*/
    private static final int WARM_UP_INSERTIONS_LINKED_LIST = 5000;

    /* a constant representing the number of time we gonna check  contain method in the contain test and
    gonna do average on that number
     */
    public static final int AVRAGE_CONSTANT = 1000;

    /* a constant use in nano to mili convert*/
    public static final int BASE = 10;

    /* a constant use in nano to mili convert*/
    public static final int POW = 6;

    /*  a constant string that represents the data1 file name */
    private static final String DATA_1_PATH = "data1.txt";

    /*  a constant string that represents the data2 file name */
    private static final String DATA_2_PATH = "data2.txt";

    /* constants for the difference the tests in the main method*/
    private static final String DEFERENCE = "*************************************************" +
            "***************************************************************";

    /* strings that we check in the main method */
    private static final String STRING_1 = "hi";
    private static final String STRING_2 = "“-13170890158";
    public static final String STRING_3 = "23";


    /* the method reboots an array of different sets that we gonna check */
    private static SimpleSet[] rebootAnalyzedSets() {
        SimpleSet[] analyzedSets = new SimpleSet[NUMBER_OF_SETS];
        analyzedSets[OPEN_HASH_SET_INDEX] = new OpenHashSet();
        analyzedSets[CLOESD_HASH_SET_INDEX] = new ClosedHashSet();
        analyzedSets[TREE_SET_INDEX] = new CollectionFacadeSet(new TreeSet<>());
        analyzedSets[LINKED_LIST_SET_INDEX] = new CollectionFacadeSet(new LinkedList<>());
        analyzedSets[HASH_SET_INDEX] = new CollectionFacadeSet(new HashSet<>());
        return analyzedSets;
    }

    /* the methods returns the tape of  that we are making the checks on */
    private static String nameOfSet(int nameIndex) {
        switch (nameIndex) {
            case OPEN_HASH_SET_INDEX:
                return OPEN_HASH;
            case CLOESD_HASH_SET_INDEX:
                return CLOSED_HASH;
            case TREE_SET_INDEX:
                return TREE_SET;
            case LINKED_LIST_SET_INDEX:
                return LINKED_LIST;
            case HASH_SET_INDEX:
                return HASH_SET;
            default:
                return DEFAULT_SET_NAME; // the program would not reach to that case
        }
    }

    /* the method is doing the warm for the contain tests ,Checks the amount of time we do the contain
     over the number of iterations of the warm up constant that the method receives*/
    private static void warmUp(SimpleSet setToCheck, String checkVal, int warmUpCheck) {
        for (int i = 0; i < warmUpCheck; i++) {
            setToCheck.contains(checkVal);
        }
    }

    /* test for how long does its takes for the sets in the analyzedSets array that are rebooting with the
    given data to check if the contain a carton value*/
    private static void containTest(SimpleSet[] analyzedSets, String checkVal, String dataName) {
        for (int i = 0; i < NUMBER_OF_SETS; i++) {
            SimpleSet checkedSet = analyzedSets[i];
            if (i == LINKED_LIST_SET_INDEX) {
                warmUp(checkedSet, checkVal, WARM_UP_INSERTIONS_LINKED_LIST);// the warm up
                long before = System.nanoTime();
                warmUp(checkedSet, checkVal, AVRAGE_CONSTANT);// the actual check
                long timeResult = (System.nanoTime() - before) / (long) AVRAGE_CONSTANT;
                System.out.println("it has taken to " + nameOfSet(i) + " " + timeResult + " nanoseconds" +
                        " to check if " + nameOfSet(i) + " contains " + checkVal + " when reboot  "
                        + dataName);
                System.out.println();
            } else {
                warmUp(checkedSet, checkVal, WARM_UP_INSERTIONS_NOT_LINKED_LIST);// the warm up
                long before = System.nanoTime();
                warmUp(checkedSet, checkVal, AVRAGE_CONSTANT);// the actual check
                long timeResult = ((System.nanoTime() - before) / (long) AVRAGE_CONSTANT);
                System.out.println("it has taken to " + nameOfSet(i) + " " + timeResult + " nanoseconds" +
                        " to check " + "if " + nameOfSet(i) + " contains " + checkVal + " when reboot with " +
                        " " + dataName);
                System.out.println();
            }
        }
    }

    /* convert time from nano to mili seconds */
    private static int convertTimeToMiliSeconds(long time) {
        return (int) (time / Math.pow(BASE, POW));
    }

    /*  test for how long does its takes for the sets in the analyzedSets array to add all the strings in the
    data to the set.
     */
    private static void checkAdd(SimpleSet[] analyzedSets, String[] data, String dataName) {
        for (int i = 0; i < NUMBER_OF_SETS; i++) {
            SimpleSet checkedSet = analyzedSets[i];
            long before = System.nanoTime();
            for (String val : data) {
                checkedSet.add(val);
            }
            long timeResult = (System.nanoTime() - before);
            int finalResult = convertTimeToMiliSeconds(timeResult);
            System.out.println("it has taken to "+ nameOfSet(i)+ " " + finalResult + " milliseconds to " +
                    "add all the value" + " from " + dataName);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        String data1[] = Ex3Utils.file2array("data1.txt");
        String data2[] = Ex3Utils.file2array("data2.txt");
        SimpleSet[] analyzedSets = rebootAnalyzedSets();
        checkAdd(analyzedSets,data1,DATA_1_PATH);
        System.out.println(DEFERENCE);
        containTest(analyzedSets,STRING_1,DATA_1_PATH);
        System.out.println(DEFERENCE);
        containTest(analyzedSets, STRING_2,DATA_1_PATH);
        System.out.println(DEFERENCE);
        analyzedSets = rebootAnalyzedSets();
        checkAdd(analyzedSets,data2,DATA_2_PATH);
        System.out.println(DEFERENCE);
        containTest(analyzedSets,STRING_3,DATA_2_PATH);
        System.out.println(DEFERENCE);
        containTest(analyzedSets,STRING_1,DATA_2_PATH);
        System.out.println(DEFERENCE);
    }
}



