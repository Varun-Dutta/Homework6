import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Display {

    public List<Pair> display(List<Painting> daliPaintings, List<Painting> picassoPaintings){

        verifyDali(daliPaintings);
        verifyPicasso(picassoPaintings);

        sortPaintingsByPrice(daliPaintings);
        sortPaintingsByPrice(picassoPaintings);

        List<List<Painting>> daliDisplayCombinations = new ArrayList<>();

        for(int i = 1; i < daliPaintings.size(); i++){
            List<List<Painting>> combinationsOfI =  new ArrayList<>();
            combinationsOfI  = generateCombinations(daliPaintings, i);
            daliDisplayCombinations.addAll(combinationsOfI);
        }

        ArrayList<Integer> resultsList = new ArrayList<>();

        for (List<Painting> daliDisplayCombination : daliDisplayCombinations){
            resultsList.add(matchDali(daliDisplayCombination, picassoPaintings));
        }

        List<Painting> optimalDaliCombination = daliDisplayCombinations.get(bestCombination(resultsList));

        return buildDisplay(optimalDaliCombination, picassoPaintings);
    }

    public static List<Painting> sortPaintingsByPrice(List<Painting> paintingList) {
        Objects.requireNonNull(paintingList);

        List<Painting> left = new ArrayList<>();
        List<Painting> right = new ArrayList<>();
        int center;

        if(paintingList.size() == 1) {
            return paintingList;
        }

        else{
            center = paintingList.size() / 2;

            for(int i = 0; i < center; i++){
                left.add(paintingList.get(i));
            }

            for(int i = center; i < paintingList.size(); i++){
                right.add(paintingList.get(i));
            }

            left = sortPaintingsByPrice(left);
            right = sortPaintingsByPrice(right);

            merge(left, right, paintingList);
        }

        return paintingList;
    }

    public static void merge(List<Painting> left, List<Painting> right, List<Painting> whole){
        Objects.requireNonNull(left);
        Objects.requireNonNull(right);
        Objects.requireNonNull(whole);

        int leftIndex = 0;
        int rightIndex = 0;
        int indexWhole = 0;

        while(leftIndex < left.size() && rightIndex < right.size()){
            if(left.get(leftIndex).price() > right.get(rightIndex).price()){
                whole.set(indexWhole, left.get(leftIndex));
                leftIndex++;
            }
            else{
                whole.set(indexWhole, right.get(rightIndex));
                rightIndex++;
            }
            indexWhole++;
        }

        List<Painting> rest;
        int restIndex = 0;
        if(leftIndex > left.size()){
            rest = right;
            restIndex = rightIndex;
        }
        else{
            rest = left;
            restIndex = leftIndex;
        }

        for(int i = restIndex; i < rest.size(); i++){
            whole.set(indexWhole, rest.get(i));
            indexWhole++;
        }
    }

    public List<List<Painting>> generateCombinations(List<Painting> daliPaintings, int numberOfPaintingsDisplayed){
        Objects.requireNonNull(daliPaintings);
        List<List<Painting>> combinations = new ArrayList<>();
        recursiveGeneration(combinations, daliPaintings, 0, daliPaintings.size() - 1, 0);
        return combinations;
    }

    public void recursiveGeneration(List<List<Painting>> combinations, List<Painting> data, int start, int end, int index){
        Objects.requireNonNull(combinations);
        Objects.requireNonNull(data);

        if(index == data.size()){
            combinations.add(data);
        }

        else if(start <= end){
            data.set(index,  data.get(start));
            recursiveGeneration(combinations, data, start + 1, end, index + 1);
            recursiveGeneration(combinations, data, start + 1, end,  index);
        }
    }

    public int matchDali(List<Painting> daliPainting, List<Painting> picassoPaintings) {
        int numberOfMatches = 0;
        int lastPicassoUsed = 0;


            for (Painting dali : daliPainting) {
                for (int i = lastPicassoUsed; i < picassoPaintings.size(); i++) {
                    if (dali.height() > picassoPaintings.get(i).height()) {
                        lastPicassoUsed = i;
                        numberOfMatches = numberOfMatches + 1;
                        break;
                    }
                }
            }

        return numberOfMatches;
    }


    public int bestCombination (List<Integer> numberOfPossibleMatches){
        int mostPicassosDisplayed = 0;
        int indexOfBestCombination = 0;

        for(int i = 0; i < numberOfPossibleMatches.size(); i++){
            if (numberOfPossibleMatches.get(i) > mostPicassosDisplayed){
                mostPicassosDisplayed = numberOfPossibleMatches.get(i);
                indexOfBestCombination = i;
            }
        }

        return indexOfBestCombination;
    }

    public List<Pair> buildDisplay(List<Painting> optimalDaliCombination, List<Painting> picassoPaintings){
        Objects.requireNonNull(optimalDaliCombination);
        Objects.requireNonNull(picassoPaintings);

        List<Pair> display = new ArrayList<>();

        int indexOfLastPicassoUsed = 0;
        for(Painting dali : optimalDaliCombination){
            for(int i = indexOfLastPicassoUsed; i < picassoPaintings.size(); i++){
                if(picassoPaintings.get(i).height() < dali.height()){
                    indexOfLastPicassoUsed = i;
                    Pair pair = new Pair((Dali)dali, (Picasso)picassoPaintings.get(i));
                    display.add(pair);
                    break;
                }
            }
        }

        return display;
    }

    public static boolean verifyDali(List<Painting> daliPaintings){
        Objects.requireNonNull(daliPaintings);

        for (Painting painting : daliPaintings){
            if (!(painting instanceof Dali))
                throw new RuntimeException("This List contains non-Dali paintings");
        }
        return true;
    }

    public boolean verifyPicasso(List<Painting> picassoPaintings){
        Objects.requireNonNull(picassoPaintings);

        for (Painting painting : picassoPaintings){
            if(!(painting instanceof Picasso)) {
                throw new RuntimeException("This List contains non-Picasso paintings");
            }
        }

        return true;
    }
}




