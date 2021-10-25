import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Painting {
    private int height;
    private long price;

    public Painting(int height, long price){
        Objects.requireNonNull(height);
        Objects.requireNonNull(price);
        this.height = height;
        this.price = price;
    }

    public int height(){
        return height;
    }

    public long price(){
        return price;
    }
}
