import org.junit.jupiter.api.Test;

import java.awt.*;
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
        List<Painting> left = new ArrayList<>();
        List<Painting> right = new ArrayList<>();

        Painting painting = new Painting(1, 10);
        Painting painting1 = new Painting(2, 10);

        left.add(painting);
        right.add(painting1);

        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting1);
        wholeOne.add(painting);

        List <Painting> whole = new ArrayList<>();
        Display.merge(left, right, whole);

        assertEquals(wholeOne, whole);
    }

    @Test
    public void mergeTestSix(){
        List<Painting> left = new ArrayList<>();
        List<Painting> right = new ArrayList<>();

        Painting painting = new Painting(1, 15);
        Painting painting1 = new Painting(2, 10);

        left.add(painting);
        right.add(painting1);

        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting);
        wholeOne.add(painting1);

        List <Painting> whole = new ArrayList<>();
        Display.merge(left, right, whole);

        assertEquals(wholeOne, whole);
    }

    @Test
    public void mergeTestSeven(){
        List<Painting> left = new ArrayList<>();
        List<Painting> right = new ArrayList<>();

        Painting painting = new Painting(1, 10);
        Painting painting1 = new Painting(2, 15);

        left.add(painting);
        right.add(painting1);

        List<Painting> wholeOne = new ArrayList<>();
        wholeOne.add(painting1);
        wholeOne.add(painting);

        List <Painting> whole = new ArrayList<>();
        Display.merge(left, right, whole);

        assertEquals(wholeOne, whole);
    }

    @Test
    public void mergeTestEight(){
        List<Painting> left = new ArrayList<>();
        List<Painting> right = new ArrayList<>();
        List<Painting> whole = null;

        assertThrows(NullPointerException.class, () -> {
            Display.merge(left, right, whole);
        });
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

    //recursiveGenerations Test
    @Test
    public void recursiveGenerationTestOne(){
        List<List<Painting>> combination = generateCombinations();
        List<Painting> data = createCustomPicassoList(3,3, 4, 4, 5,5);
        List<List<Painting>> combinationOriginal = combination;
        combinationOriginal.add(data);

        display.recursiveGeneration(combination, data, 0, combination.size(), 3);

        assertEquals(combinationOriginal, combination);
    }

    @Test
    public void recursiveGenerationTestTwo(){
        List<List<Painting>> combination = generateCombinations();
        List<Painting> data = createCustomPicassoList(3,3, 4, 4, 5,5);
        data.add(new Picasso(1, 1));
        List<List<Painting>> combinationOriginal = combination;

        display.recursiveGeneration(combination, data, 0, combination.size(), 0);

        assertEquals(combinationOriginal, combination);
    }

    @Test
    public void recursiveGenerationTestThree(){
        List<List<Painting>> combination = generateCombinations();
        List<Painting> data = createCustomPicassoList(3,3, 4, 4, 5,5);
        data.add(new Picasso(1, 1));
        List<List<Painting>> combinationOriginal = combination;

        display.recursiveGeneration(combination, data, 5, combination.size(), 0);

        assertEquals(combinationOriginal, combination);
    }

    @Test
    public void recursiveGenerationTestFour(){
        List<List<Painting>> combination = null;
        List<Painting> data = createCustomPicassoList(3,3, 4, 4, 5,5);
        data.add(new Picasso(1, 1));
        List<List<Painting>> combinationOriginal = combination;

        assertThrows(NullPointerException.class, () -> {
            display.recursiveGeneration(combination, data, 5, combination.size(), 0);
        });
    }

    @Test
    public void recursiveGenerationTestFive(){
        List<List<Painting>> combination = generateCombinations();
        List<Painting> data = null;

        assertThrows(NullPointerException.class, () -> {
            display.recursiveGeneration(combination, data, 5, combination.size(), 0);
        });
    }


    //generateCombinations Tests

    @Test
    public void generateCombinationsTestOne(){
        List<Painting> picassos = piccasoPaintings();
        List<List<Painting>> paintingCombinations = display.generateCombinations(picassos, 2);
        List<List<Painting>> actualCombinations = new ArrayList<>();

        List<Painting> paintingOne = new ArrayList<>();
        paintingOne.add(picassos.get(0));
        paintingOne.add(picassos.get(1));
        actualCombinations.add(paintingOne);

        List<Painting> paintingTwo = new ArrayList<>();
        paintingTwo.add(picassos.get(0));
        paintingTwo.add(picassos.get(2));
        actualCombinations.add(paintingTwo);

        List<Painting> paintingThree = new ArrayList<>();
        paintingThree.add(picassos.get(1));
        paintingThree.add(picassos.get(2));

        assertEquals(actualCombinations, paintingCombinations);
    }

    @Test
    public void generateCombinationsTestTwo(){
        List<Painting> paintings = null;
        assertThrows(NullPointerException.class, () -> {
           display.generateCombinations(paintings, 3);
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

    public static List<Painting> piccasoPaintings(){
        return createCustomPicassoList(1, 1, 2, 2, 3, 3);
    }

    //matchDali Tests

    @Test
    public void matchDaliTestOne(){
        List<Painting> picassos = piccasoPaintings();
        List<Painting> dalis = daliPaintings();

        assertEquals(3, display.matchDali(dalis, picassos));
    }

    @Test
    public void matchDaliTestTwo(){
        List<Painting> picasso = piccasoPaintings();
        List<Painting> dalis = null;
        assertThrows(NullPointerException.class, () -> {
            display.matchDali(dalis, picasso);
        });
    }

    @Test
    public void matchDaliTestThree(){
        List<Painting> picassos = null;
        List<Painting> dalis = daliPaintings();
        assertThrows(NullPointerException.class, () -> {
            display.matchDali(dalis, picassos);
        });
    }

    @Test
    public void matchDaliTestFour(){
        List<Painting> picasso = new ArrayList<>();
        picasso.add(new Picasso(10, 10));
        List <Painting> dali = new ArrayList<>();
        dali.add(new Dali(1, 1));
        assertEquals(0, display.matchDali(dali, picasso));
    }

    //build DisplayTests
    @Test
    public void buildDisplayTestOne(){
        List<Painting> optimalDali = new ArrayList<>();
        Dali dali = new Dali (3, 12);
        Dali daliTwo = new Dali (7, 3);
        Dali daliThree = new Dali (9, 12);
        optimalDali.add(dali);
        optimalDali.add(daliTwo);
        optimalDali.add(daliThree);

        List<Painting> picassos = new ArrayList<>();
        Picasso picasso = new Picasso(1, 12);
        Picasso picassoTwo = new Picasso(8, 12);
        Picasso picassoThree = new Picasso(19, 12);
        Picasso picassoFour = new Picasso(21, 2);

        List<Pair> pairs = new ArrayList<>();
        Pair pairOne = new Pair(dali, picasso);
        Pair pairTwo = new Pair(daliThree, picassoTwo);


        assertEquals(pairs, display.buildDisplay(optimalDali, picassos));
    }

    @Test
    public void buildDisplayTestTwo(){
        List<Painting> dali = new ArrayList<>();
        List<Painting> picassos = new ArrayList<>();
        Picasso picasso = new Picasso(1, 12);
        Picasso picassoTwo = new Picasso(8, 12);
        Picasso picassoThree = new Picasso(19, 12);
        Picasso picassoFour = new Picasso(21, 2);

        List <Pair> pair = new ArrayList<>();
        assertEquals(pair, display.buildDisplay(dali, picassos));
    }

    @Test
    public void buildDisplayTestThree(){
        List<Painting> dalis = new ArrayList<>();
        List<Painting> picassos = new ArrayList<>();
        Dali dali = new Dali (1, 12);
        dalis.add(dali);

        List <Pair> pair = new ArrayList<>();
        assertEquals(pair, display.buildDisplay(dalis, picassos));
    }

    @Test
    public void buildDisplayTestFour(){
        List<Painting> dalis = new ArrayList<>();
        List<Painting> picassos = new ArrayList<>();

        Dali dali = new Dali (1, 12);
        dalis.add(dali);

        Picasso picasso = new Picasso(2, 3);
        picassos.add(picasso);

        List <Pair> pair = new ArrayList<>();
        assertEquals(pair, display.buildDisplay(dalis, picassos));
    }

    @Test
    public void buildDisplayTestFive(){
        List<Painting> dali = null;
        List<Painting> picasso = new ArrayList<>();
        assertThrows(NullPointerException.class, () ->{
            display.buildDisplay(dali, picasso);
        });
    }

    @Test
    public void buildDisplayTestSix(){
        List<Painting> dali = new ArrayList<>();
        List<Painting> picasso = null;
        assertThrows(NullPointerException.class, () ->{
            display.buildDisplay(dali, picasso);
        });
    }

    //displayTests

    @Test
    public void displayTestOne(){
       List<Painting> picassos = new ArrayList<>();

       Picasso picasso = new Picasso(5, 1);
       picassos.add(picasso);

       Picasso picassoOne = new Picasso(7,2);
       picassos.add(picassoOne);

       Picasso picassoTwo = new Picasso (9, 3);
       picassos.add(picassoTwo);

       List<Painting> dalis = new ArrayList<>();

       Dali dali = new Dali(6, 1);
       dalis.add(dali);

       Dali daliOne = new  Dali (7,11);
       dalis.add(daliOne);

       Dali daliTwo = new Dali(12, 7);
       dalis.add(daliTwo);

       List<Pair> pairs = new ArrayList<>();
       Pair pairOne = new Pair(dali, picasso);
       pairs.add(pairOne);

       Pair pairTwo = new Pair(daliTwo, picassoOne);
       pairs.add(pairTwo);

       assertEquals(pairs, display.display(dalis, picassos));
    }

    @Test
    public void displayTestTwo(){
        List<Painting> picassos = piccasoPaintings();
        List<Painting> dalis = new ArrayList<>();
        List<Pair> pairs = new ArrayList<>();

        assertEquals(pairs, display.display(dalis, picassos));
    }


    @Test
    public void displayTestThree(){
        List<Painting> picassos = new ArrayList<>();

        Picasso picasso = new Picasso(5, 1);
        picassos.add(picasso);

        Picasso picassoOne = new Picasso(7,2);
        picassos.add(picassoOne);

        Picasso picassoTwo = new Picasso (9, 3);
        picassos.add(picassoTwo);

        List<Painting> dalis = new ArrayList<>();

        Dali dali = new Dali(6, 1);
        dalis.add(dali);

        List<Pair> pairs = new ArrayList<>();
        Pair pairOne = new Pair(dali, picasso);
        pairs.add(pairOne);

        assertEquals(pairs, display.display(dalis, picassos));
    }

    @Test
    public void displayTestFour(){
        List<Painting> picassos = piccasoPaintings();
        List<Painting> dali = daliPaintings();
        dali.addAll(piccasoPaintings());
        assertThrows(RuntimeException.class, () -> {
            display.display(dali, picassos);
        });
    }
    @Test
    public void displayTestFive(){
        List<Painting> picassos = piccasoPaintings();
        List<Painting> dalis = null;
        assertThrows(RuntimeException.class, () -> {
            display.display(dalis, picassos);
        });
    }


    public List<List<Painting>> generateCombinations(){
        List<Painting> picassos = piccasoPaintings();
        List<List<Painting>> actualCombinations = new ArrayList<>();

        List<Painting> paintingOne = new ArrayList<>();
        paintingOne.add(picassos.get(0));
        paintingOne.add(picassos.get(1));
        actualCombinations.add(paintingOne);

        List<Painting> paintingTwo = new ArrayList<>();
        paintingTwo.add(picassos.get(0));
        paintingTwo.add(picassos.get(2));
        actualCombinations.add(paintingTwo);

        List<Painting> paintingThree = new ArrayList<>();
        paintingThree.add(picassos.get(1));
        paintingThree.add(picassos.get(2));

        return actualCombinations;
    }

    
    
}