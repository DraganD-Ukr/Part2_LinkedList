package utils;

import business.Song;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    private LinkedList instance;

    @BeforeEach
    public void setUp() {
        instance = new LinkedList();
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testSize_EmptyList() {
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    @Test
    public void testSize_PopulatedList() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);

        int expResult = 3;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    @Test
    public void testGet_ValidInput() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);

        Song result = instance.get(1);
        assertEquals(s2, result);
    }

    @Test
    public void testGet_BreakingLowerBound() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            instance.get(-1);
        });
    }

    @Test
    public void testGet_BreakingUpperBound_GreaterThanSize() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            instance.get(4);
        });
    }

    @Test
    public void testGet_BreakingUpperBound_EqualToSize() {
        Song s1 = new Song("Title 0", "Artist 1");
        Song s2 = new Song("Title 1", "Artist 1");
        Song s3 = new Song("Title 2", "Artist 1");

        instance.add(s1);
        instance.add(s2);
        instance.add(s3);

        assertThrows(IndexOutOfBoundsException.class, () -> {
            instance.get(3);
        });
    }

    @Test
    public void testIndexOf_SongInList() {
        Song s = new Song("Temp Title", "Temp Artist1");
        instance.add(new Song("Temp title0", "Temp Artist1"));
        instance.add(new Song("Temp title1", "Temp Artist1"));
        instance.add(new Song("Temp Title", "Temp Artist1"));

        int expResult = 2;
        int result = instance.indexOf(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testIndexOf_SongNotInList() {
        Song s = new Song();
        instance.add(new Song("Temp title0", "Temp Artist1"));
        instance.add(new Song("Temp title1", "Temp Artist1"));
        instance.add(new Song("Temp Title", "Temp Artist1"));

        int expResult = -1;
        int result = instance.indexOf(s);
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty() {
        boolean expResult = true;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    @Test
    public void testIsEmpty_PopulatedList() {
        instance.add(new Song("Temp title0", "Temp Artist1"));
        instance.add(new Song("Temp title1", "Temp Artist1"));
        instance.add(new Song("Temp Title", "Temp Artist1"));

        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }




}
