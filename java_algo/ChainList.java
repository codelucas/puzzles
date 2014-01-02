package JavaAlgo;

//Name: Lucas Ou-Yang     ID: 27404511

// ChainList.java
// Skeleton written by Norman Jacobson, March 2006

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ChainList {
    private ArrayList<Chain> chainList;
    private Scanner mainScanner;
    private AbstractMapKey mapKey;

    public ChainList(AbstractMapKey mapKeyToUse, String masterFileName)
            throws IOException {
        this.mainScanner = new Scanner(new File(masterFileName));
        this.mapKey = mapKeyToUse;

        this.chainList = new ArrayList<Chain>();
        for (int i = 0; i < mapKey.computeLargestMapKey() + 1; i++) // *************ERRORPRONE
        {
            chainList.add(new Chain());
        }
        this.buildList(masterFileName);
    }

    // Fake initializer for testing purposes
    public void fakeInitializer(AbstractMapKey mapKeyToUse,
            String masterFileName) throws FileNotFoundException {
        // Nothing
    }

    // buildList() builds the chain list from the file masterFileName
    // (with the help of insert()).
    private void buildList(String masterFileName) {
        while (mainScanner.hasNextLine()) {
            String currentLine = mainScanner.nextLine();
            Scanner disector = new Scanner(currentLine);
            int areaCode = disector.nextInt();
            int phonePrefix = disector.nextInt();
            int zipCode = disector.nextInt();
            disector.close();
            this.insert(new ZipcodeGroup(zipCode, new PhoneGroup(areaCode,
                    phonePrefix)));
        }
    }

    // insert() takes a ZIP code group and places it into the correct
    // location of the appropriate chain
    private void insert(ZipcodeGroup zipGroup) {
        Chain altarThisChain = chainList.get(mapKey.computeMapKey(zipGroup
                .getZipcode()));
        altarThisChain.insert(zipGroup);
        chainList.set(mapKey.computeMapKey(zipGroup.getZipcode()),
                altarThisChain);
    }

    // lookup() takes a key (zip code) as a parameter and returns the list
    // of values associated with it (the list of phone groups in that zip code).
    // If the key does not appear in the list, a NoSuchZipException is thrown.

    public ArrayList<PhoneGroup> lookup(int key) throws NoSuchZipException {
        return chainList.get(mapKey.computeMapKey(key)).find(key)
                .getPhoneGroups();
    }

    public void fakeLookup(int key) {
        // Nothing.
    }

    // toString() builds an easy-to-interpret String representation
    // of the chain list structure. I suggest printing out each chain
    // on its own line (and using the toString() in Chain to handle
    // printing the chain itself). An *excellent* debugging tool!
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < chainList.size(); i++) {
            buffer.append("Chain #" + i + "\r\n" + chainList.get(i).toString());
        }
        return buffer.toString();
    }

    // ----------------------------- Chains ---------------------------------
    // A Chain is just a singly-linked list of Nodes containing ZipcodeGroups.
    // We use our own linked-list and node structures for speed: Java's
    // LinkedList
    // class has a lot of overhead and features we don't need. Chains and
    // their nodes are manipulated in ChainList directly, so the need for
    // Chain methods is minimal.
    private class Chain {
        public Node startOfChain; // start of key list
        public Node current; // current node
        public Node prev;

        // Create a new chain; it's null to start
        public Chain() {
            startOfChain = null;
            current = null;
            prev = null;
        }

        // Insert a node with a ZipcodeGroup into the chain at the point needed
        // to preserve key order, or, if the key (Zip code) already exists, add
        // the phone group of this ZipcodeGroup to the zipcode's list.
        public void insert(ZipcodeGroup zipGroup) {
            current = startOfChain;

            // If the chain is empty, insert the node and set current, starting
            // positions.
            if (startOfChain == null) {
                startOfChain = new Node(zipGroup, null);
            }
            // My order algorithm will be stored so bigger ZIP's in the front of
            // the list.
            else {
                Node toBeInserted = new Node(zipGroup, null);
                // If it is the case that we have an existing Zip code, no new
                // nodes are
                // added, we just append a phone group entry, thats it.
                boolean zipEntryPointFound = false;
                while (!zipEntryPointFound) {
                    if (current == null) {
                        prev.next = toBeInserted;
                        zipEntryPointFound = true;
                    }
                    // If it's larger, swap.
                    else if (zipGroup.getZipcode() > current.getZipcode()) {
                        prev = current;
                        current = current.next;
                    }
                    // If its equal, replace and leave.
                    else if (current.getZipcode() == zipGroup.getZipcode()) {
                        current.addPhoneGroup(zipGroup.getPhoneGroupList().get(
                                0));
                        zipEntryPointFound = true;
                    }
                    // If its smaller, add it in!
                    else if (zipGroup.getZipcode() < current.getZipcode()) {
                        if (prev == null) {
                            toBeInserted.next = startOfChain;
                            startOfChain = toBeInserted;
                        } else {
                            toBeInserted.next = current;
                            prev.next = toBeInserted;
                        }
                        zipEntryPointFound = true;
                    }
                }
            }
        }

        // Return the node in this chain that contains the 'key to find'.
        // If no such node, return null
        public Node find(int keyToFind) throws NoSuchZipException {
            current = startOfChain;

            while (current != null) {
                // Because my chains are sorted in ascending order, if our zip
                // is smaller
                // than the current value, we know our zip wont be present in
                // the list.
                if (keyToFind < current.getZipcode()) {
                    throw new NoSuchZipException();
                } else if (current.getZipcode() == keyToFind) {
                    return current;
                }
                current = current.next;
            }
            throw new NoSuchZipException();
        }

        // toString() builds a String representation of this chain
        // I suggest printing out each key on its own line and printing
        // with each key its value list. An *excellent* debugging tool!
        public String toString() {
            StringBuffer returnBuffer = new StringBuffer();
            current = startOfChain;

            while (current != null) {
                StringBuffer smallBuffer = new StringBuffer();
                for (int i = 0; i < current.getPhoneGroups().size(); i++) {
                    smallBuffer.append("Area: "
                            + current.getPhoneGroups().get(i).getAreaCode()
                            + " Prefix: "
                            + current.getPhoneGroups().get(i).getPrefix()
                            + " || ");
                }

                returnBuffer.append("     Key: " + current.getZipcode()
                        + "    Values: " + smallBuffer.toString() + "\r\n");
                current = current.next;
            }
            return returnBuffer.toString();
        }
    }

    // A Node holds a reference to a ZipcodeGroup and a reference to
    // the next item in the list.
    private static class Node {
        public ZipcodeGroup zipGroup; // zip code with its phone group
        public Node next; // next node

        // Make a node with a given ZipcodeGroup
        public Node(ZipcodeGroup zipGroup, Node next) {
            this.zipGroup = zipGroup;
            this.next = next;
        }

        // getZipcode() returns the key (zip code) stored in this node.
        public int getZipcode() {
            return zipGroup.getZipcode();
        }

        // getPhoneGroups() returns the list of phone groups stored is this
        // node.
        public ArrayList<PhoneGroup> getPhoneGroups() {
            return zipGroup.getPhoneGroupList();
        }

        // addPhoneGroup() adds a phone group to the phone group list.
        private void addPhoneGroup(PhoneGroup item) {
            zipGroup.getPhoneGroupList().add(item);
        }

        // compareToSearchKey() compares the zip code in this node to the
        // given zip for which we are searching, returning a negative
        // number if this key is less than the search key, zero if they
        // are equal, and a positive number if this key is greater than
        // the search key.
        // THIS METHOD IS INTENTIONALLY NEVER USED
        /*
         * public int compareToSearchKey(int searchKey) { int nodeZipcode =
         * getZipcode(); if (nodeZipcode < searchKey) { return -1; } else if
         * (nodeZipcode > searchKey) { return 1; } else { return 0; } }
         */
    }
}

class ZipcodeGroup {
    int zipcode; // the zipcode key
    private ArrayList<PhoneGroup> phoneGroupList; // the list of phone groups

    // Build a new zipcode group from a given zip and its first phone group
    public ZipcodeGroup(int zip, PhoneGroup thisGroup) {
        zipcode = zip;
        phoneGroupList = new ArrayList<PhoneGroup>();
        phoneGroupList.add(thisGroup);
    }

    // Access the zipcode
    public int getZipcode() {
        return zipcode;
    }

    // Access the phone group list
    public ArrayList<PhoneGroup> getPhoneGroupList() {
        return phoneGroupList;
    }

    // Add to the ZipcodeGroup's phone list. --ONLY USED IN PROXMAP--
    public void addToList(PhoneGroup addGroup) {
        phoneGroupList.add(addGroup);
    }
}

class PhoneGroup {
    private int areaCode; // the area code
    private int prefix; // the prefix

    // Build a phone group froma given area code and phone prefix
    public PhoneGroup(int a, int p) {
        areaCode = a;
        prefix = p;
    }

    // Access the area code
    public int getAreaCode() {
        return areaCode;
    }

    // Access the prefix
    public int getPrefix() {
        return prefix;
    }
}

class NoSuchZipException extends Exception {
    private static final long serialVersionUID = 1L;
}

abstract class AbstractMapKey {
    // The largest possible key -- the largest possible 5-digit ZIP code
    public static final int LARGEST_KEY_VALUE = 99999;

    // Computes and returns the mapKey for a given key
    public abstract int computeMapKey(int key);

    // Computes and returns the mapKey for the largest possible key;
    public abstract int computeLargestMapKey();
}
