import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayTest {

    Display display = new Display();

    //verifyPicasso Tests

    @Test //Test 1
    public void verifyPiccassosTestOne(){
        Picasso picasso = new Picasso(10, 10);
        Picasso picassoTwo = new Picasso(10, 101);

        List<Painting> picassos = new ArrayList<>();
        picassos.add(picasso);
        picassos.add(picassoTwo);

        assertTrue(display.verifyPicasso(picassos));
    }

    @Test //Test 2
    public void verifyPiccassosTestTwo(){
        List<Painting> paintings = null;
        assertThrows(NullPointerException.class, () -> {
            display.verifyPicasso(paintings);
        });
    }

    @Test // Test 3
    void verifyPiccassosTestThree(){
        List<Painting> paintings = new ArrayList<>();
        Painting painting = new Painting(1, 1);
        paintings.add(painting);

        assertThrows(RuntimeException.class, () -> {
            display.verifyPicasso(paintings);
        });
    }

    @Test // Test 4
    void verifyPicassosTestFour(){
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Picasso(1, 1));

        assertTrue(display.verifyPicasso(paintings));
    }

    @Test //Test 5
    void verifyPicassosTestFive(){
        List<Painting> paintings = daliPaintings();
        assertThrows(RuntimeException.class, () -> {
            display.verifyPicasso(paintings);
        });
    }

    //verify Dali Tests

    @Test //Test 1
    public void verifyDaliTestOne(){
        Dali dali = new Dali(10, 10);
        Dali daliOne = new Dali(10, 101);

        List<Painting> dalis = new ArrayList<>();
        dalis.add(dali);
        dalis.add(daliOne);

        assertTrue(Display.verifyDali(dalis));
    }

    @Test //Test 2
    public void verifyDaliTestTwo(){
        List<Painting> paintings = null;
        assertThrows(NullPointerException.class, () -> {
            Display.verifyDali(paintings);
        });
    }

    @Test // Test 3
    void verifyDaliTestThree(){
        List<Painting> paintings = new ArrayList<>();
        Painting painting = new Painting(1, 1);
        paintings.add(painting);

        assertThrows(RuntimeException.class, () -> {
            Display.verifyDali(paintings);
        });
    }

    @Test // Test 4
    void verifyDaliTestFour(){
        List<Painting> paintings = new ArrayList<>();
        paintings.add(new Dali(1, 1));
        assertTrue(new Display().verifyDali(paintings));
    }

    @Test //Test 5
    void verifyDaliTestFive(){
        List<Painting> paintings = piccasoPaintings();
        assertThrows(RuntimeException.class, () -> {
            new Display().verifyDali(paintings);
        });
    }

    public static List<Painting> daliPaintings(){
        Dali dali = new Dali(3, 1);
        Dali daliTwo = new Dali(4,2);
        Dali daliThree = new Dali (5, 3);

        List<Painting> daliPaintings = new ArrayList<>();
        daliPaintings.add(dali);
        daliPaintings.add(daliTwo);
        daliPaintings.add(daliThree);

        return daliPaintings;
    }

    public static List<Painting> piccasoPaintings(){
        return createCustomPicassoList(1, 1, 2, 2, 3, 3);
    }

    //merge

    @Test
    public void mergeTestOne(){
        Painting painting = new Painting(10, 34);
        Painting painting1 = new Painting(8, 38);
        List<Painting> left = new ArrayList<>();
        left.add(painting);
        left.add(painting1);

        Painting painting2 = new Painting(10, 12);
        Painting painting3 = new Painting(8, 55);
        List<Painting> right = new ArrayList<>();
        right.add(painting2);
        right.add(painting3);

        List<Painting> whole = new ArrayList<>(4);
        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting2);
        wholeOne.add(painting);
        wholeOne.add(painting1);
        wholeOne.add(painting3);

        whole.add(painting);
        whole.add(painting1);
        whole.add(painting2);
        whole.add(painting3);

        System.out.println("Whole 1: " + wholeOne);
        System.out.println("Whole Two: " + whole);

        Display.merge(left, right, whole);


        assertEquals(wholeOne, whole);
    }

    @Test
    public void mergeTestTwo(){
        List<Painting> left = null;
        List<Painting> right = new ArrayList<>();
        List<Painting> whole = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> {
            Display.merge(left, right, whole);
        });
    }

    @Test
    public void mergeTestThree(){
        List<Painting> left = new ArrayList<>();
        List<Painting> right = null;
        List<Painting> whole = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> {
            Display.merge(left, right, whole);
        });
    }

    @Test
    public void mergeTestFour(){
        List<Painting> paintings = new ArrayList<>();
        List<Painting> paintings1 = new ArrayList<>();

        Painting painting = new Painting(1, 10);
        Painting painting1 = new Painting(2, 10);

        paintings.add(painting);
        paintings1.add(painting1);

        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting);
        wholeOne.add(painting1);

        List <Painting> whole = new ArrayList<>();
        Display.merge(paintings, paintings1, whole);

        assertEquals(whole, wholeOne);
    }

    @Test
    public void mergeTestFive(){
        List<Painting> paintings = new ArrayList<>();
        List<Painting> paintings1 = new ArrayList<>();

        Painting painting = new Painting(1, 10);
        Painting painting1 = new Painting(2, 10);

        paintings.add(painting);
        paintings1.add(painting1);

        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting);
        wholeOne.add(painting1);

        List <Painting> whole = new ArrayList<>();
        Display.merge(paintings, paintings1, whole);

        assertEquals(whole, wholeOne);
    }

    //sortPaintingsByPrice

    @Test
    public void sortPaintingsByPriceTestOne(){
        Picasso picasso = new Picasso(1, 1);
        Picasso picassoOne = new Picasso(2,2);
        Picasso picassoTwo = new Picasso (3, 3);

        List<Painting> unorderedPicassoPaintings = new ArrayList<>();
        unorderedPicassoPaintings.add(picasso);
        unorderedPicassoPaintings.add(picassoOne);
        unorderedPicassoPaintings.add(picassoTwo);

        List<Painting> orderPicassoPaintings;
        orderPicassoPaintings = new ArrayList<>();
        orderPicassoPaintings.add(picassoTwo);
        orderPicassoPaintings.add(picassoOne);
        orderPicassoPaintings.add(picasso);

        Display.sortPaintingsByPrice(unorderedPicassoPaintings);

        assertEquals(orderPicassoPaintings, unorderedPicassoPaintings);
    }

    @Test
    public void sortPaintingsByPriceTestTwo(){
        List<Painting> paintings = new ArrayList<>();
        Picasso picasso = new Picasso(1, 1);
        paintings.add(picasso);

        assertEquals(paintings, Display.sortPaintingsByPrice(paintings));
    }

    @Test
    public void sortPaintingsByPriceTestThree(){
        List<Painting> paintings = null;
        assertThrows(NullPointerException.class, () -> {
            Display.sortPaintingsByPrice(paintings);
        });
    }

    public static List<Painting> createCustomPicassoList(int heightOne, int priceOne, int heightTwo, int priceTwo, int heightThree, int priceThree){
        Picasso picasso = new Picasso(heightOne, priceOne);
        Picasso picassoOne = new Picasso(heightTwo,priceTwo);
        Picasso picassoTwo = new Picasso (heightThree, priceThree);

        List<Painting> piccasoPaintings = new ArrayList<>();
        piccasoPaintings.add(picasso);
        piccasoPaintings.add(picassoOne);
        piccasoPaintings.add(picassoTwo);

        return piccasoPaintings;
    }



    //display Tests

    @Test
    public void displayTestOne(){
        List<Painting> picassoPaintings = piccasoPaintings();
        List<Painting> daliPaintings = daliPaintings();


    }


    
    
}