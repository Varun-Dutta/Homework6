import java.util.Objects;

public record Pair(Dali daliPainting, Picasso picassoPainting) {

    public Dali daliPainting(){
        return daliPainting;
    }

    public Picasso picassoPainting(){
        return picassoPainting;
    }
}
