package model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class PurchaseTest {
    Purchase testPurchase1;
    //Purchase testPurchase2;
    //Purchase testPurchase3;

    @Test
    public void testConstructor() {
        testPurchase1 = new Purchase(10, "apple", "lunch for my son jerry");
        assertEquals(10, testPurchase1.getMoneyPurchased());
        assertEquals("apple", testPurchase1.getType());
        assertEquals("lunch for my son jerry", testPurchase1.getCommand());
    }
}
