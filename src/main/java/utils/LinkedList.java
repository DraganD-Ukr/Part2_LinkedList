package utils;

import business.Song;

/**
 *
 * @author michelle
 */
public class LinkedList {

    private Node head;
    private int numElements;
    private String validArtist;

    private Node tail;
    private boolean isFirstAdd = true;


    public LinkedList(){
        head = null;
        numElements = 0;
    }

    /**
     * Returns a size of the list.
     * @return a size of the list.
     */
    public int size(){
        return numElements;
    }

    /**
     * Returns a first added artist to the list.
     * @return
     */
    public String getValidArtist(){
        return validArtist;
    }

    /**
     * Sets a first added artist to the list.
     * @return
     */
    private void setValidArtist(String newValidArtist){
        validArtist = newValidArtist;
    }

    /**
     * Checks if the list is empty.
     * @return boolean indicating if the list is empty.
     */
    public boolean isEmpty(){
        if (this.numElements==0){
            return true;
        }
        return false;
    }

    /**
     * Adds a song to list. A song must have a same artist as whatever the first added song's tittle is.
     * @param song song to be added.
     * @throws IllegalArgumentException
     */
    public void add(Song song) throws IllegalArgumentException{

        Node newNode = new Node(song);

//        Case for adding a very first song in playlist
        if (isFirstAdd){
            if (isEmpty()){
                head = newNode;
            } else {
                Node current = head;

                while(current.getNext() != null){
                    current = current.getNext();
                }
                current.setNext(newNode);
            }
            setValidArtist(newNode.getSong().getArtist());
            isFirstAdd = false;
        } else {

//        Case for adding NOT a first song in playlist
//           First, checking if artist of a new song is the same as initial song's(very first song) artist
            if (!isSameArtist(newNode)){
                throw new IllegalArgumentException("Artist of song trying to be added does not equal to initial one: " +
                        "Initial: " + getValidArtist() + ", New: " + newNode.getSong().getArtist());
            }

//            If it is the same artist do the usual actions for linked list
            if (isEmpty()){
                head = newNode;
            } else{
                Node current = head;

                while(current.getNext() != null){
                    current = current.getNext();
                }
                current.setNext(newNode);
            }
        }
        tail = newNode;
        numElements++;
    }

    /**
     * Compares the artists of songs.
     * @param newNode the node to be checked.
     * @return
     */
    private boolean isSameArtist(Node newNode) {
        return newNode.getSong().getArtist().equalsIgnoreCase(getValidArtist());
    }

    /**
     * Gets a song from provided index.
     * @param pos position of the song to get.
     * @return a song.
     * @throws IndexOutOfBoundsException
     */
    public Song get(int pos) throws IndexOutOfBoundsException{

        if(pos < 0 || pos >= numElements){
            throw new IndexOutOfBoundsException("Illegal position supplied: " + pos);
        }

//        Checking if it's first or last element in the list, to skip the loop(since we have head and tail references)
        if (pos == 0){
            return head.getSong();
        } else if (pos == numElements-1){
            return tail.getSong();
        }

        Node current = head;

        for(int i = 0; i < pos; i++){
            current = current.getNext();
        }
        return current.getSong();
    }

    /**
     * Gets index of provided song in the list.
     * @param song song to be found.
     * @return integer indicating index of song.
     */
    public int indexOf(Song song){
        Node current = head;

        for(int i = 0; i < numElements; i++){

            if(current.getSong().equals(song)){
                return i;
            }
            current = current.getNext();
        }
        return -1;
    }

    /**
     * Removes all matches of provided song in the list. Songs are equal if their artists and song are the same.
     * @param song song/s to be deleted.
     * @return a boolean indicating success of action.
     * @throws IllegalArgumentException
     */
    public boolean removeAll(Song song) throws IllegalArgumentException {

        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }

        boolean deleted = false;

        // Remove nodes from the beginning of the list that match the given song
        while (head != null && head.getSong().equals(song)) {
            head = head.getNext();
            numElements--;
            deleted = true;
        }

        Node current = head;

        // Iterate through the list to remove matching songs
        while (current != null && current.getNext() != null) {
            if (current.getNext().getSong().equals(song)) {
                current.setNext(current.getNext().getNext());
                numElements--;
                deleted = true;
            } else {
                current = current.getNext();
            }
        }

        // Update the tail reference if needed
        if (numElements == 0) {
            tail = null;
        } else if (tail.getSong().equals(song)) {
            tail = current;
        }

        return deleted;
    }


    protected static class Node{
        private Song song;
        private Node next;

        public Node(Song song){
            this.song = song;
            next = null;
        }

        public void setNext(Node next){
            this.next = next;
        }

        public Node getNext(){
            return this.next;
        }

        public void setSong(Song song){
            this.song = song;
        }

        public Song getSong(){
            return this.song;
        }
    }



}
