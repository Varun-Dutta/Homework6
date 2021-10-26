import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaintingTest {

    @Test
    public void height(){
        Painting painting = new Painting(10, 1);
        assertEquals(10, painting.height());
    }

    @Test
    public void price(){
        Painting painting = new Painting(10, 1);
        assertEquals(1, painting.price());
    }
}