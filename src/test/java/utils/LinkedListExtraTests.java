package utils;
import business.Song;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class LinkedListExtraTests {

    private LinkedList instance;

    @BeforeEach
    public void setUp() {
        instance = new LinkedList();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


//    Testing Add
    @Test
    public void testAddWithEmptyList() {
        Song s1 = new Song("Title 0", "Artist 1");

        instance.add(s1);

        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
        assertEquals(s1, instance.get(0));
    }

    @Test
    public void testAddWithDifferentArtist() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 2");;

        instance.add(s1);

        assertThrows(IllegalArgumentException.class, () -> {
            instance.add(s2);
        });
    }

    @Test
    public void testAddWithDifferentArtist_AfterRemoval() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 2");;

        instance.add(s1);
        instance.removeAll(s1);

        assertThrows(IllegalArgumentException.class, () -> {
            instance.add(s2);
        });
    }


//    Testing RemoveAll
    @Test
    public void testRemoveAll_WithEmptyList() {
        Song s1 = new Song("Title 0", "Artist 1");

        assertFalse(instance.removeAll(s1));
        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveAll_NullSong() {
        Song s1 = null;

        assertThrows(IllegalArgumentException.class, () -> {
            instance.removeAll(s1);
        });
        assertEquals(0, instance.size());
    }

    @Test
    public void testRemoveAll_RemovingOneElement() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");

        instance.add(s1);
        instance.add(s2);

        assertTrue(instance.removeAll(s2));
        assertEquals(1, instance.size());
        assertEquals(instance.get(0), s1);
    }

    @Test
    public void testRemoveAll_RemovingMultipleElements() {
        Song s1 = new Song("Title 2", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");
        Song s4 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);
        instance.add(s4);

        assertTrue(instance.removeAll(s4));
        assertEquals(1, instance.size());

        assertEquals(instance.get(0), s2);
    }


}
