package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PurchaseTypesTest {
    PurchaseTypes testType1;
    PurchaseTypes testType2;


    @BeforeEach
    public void runBefore() {
        testType1 = new PurchaseTypes();

    }

    @Test
    public void testConstructor() {
        testType2 = new PurchaseTypes();
        assertEquals(4, testType2.getSize());
    }

    @Test
    public void testIfContain() {
        testType1.ifContain("accommodation");
        testType1.ifContain("book");
    }
}
