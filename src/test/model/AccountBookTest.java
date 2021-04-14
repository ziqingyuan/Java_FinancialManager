package model;

import exception.InvalidNumberEntered;
import exception.InvalidSpent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AccountBookTest {
    AccountBook testBook1;
    AccountBook testBook2;
    AccountBook testBook3;
    AccountBook testBook4;
    Purchase purchase1;

    @BeforeEach
    public void runBefore() {
        testBook1 = new AccountBook();
        testBook2 = new AccountBook();
        testBook4 = new AccountBook();
        purchase1 = new Purchase(100000, "accommodation", "mehotel");

        try {
            testBook2.addPurchase(12, "Apple", "");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
        try {
            testBook2.addPurchase(39.99, "accommodation", "house");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
    }

    @Test
    public void testConstructor() {
        testBook3 = new AccountBook();
        assertEquals(0, testBook3.numberOfPurchases());
        assertEquals(4, testBook3.getTypeList().size());
    }

    @Test
    public void testAddPurchase() {
        try {
            testBook1.addPurchase(10, "Book", "");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
        assertEquals(5, testBook1.getTypeList().size());
        assertEquals(1, testBook1.numberOfPurchases());
        assertEquals(10.0, testBook1.moneySpent());


        testBook4.addPurchase(purchase1);
        assertEquals(4, testBook4.getTypeList().size());
        assertEquals(1, testBook4.numberOfPurchases());
        assertEquals(1000.0, testBook4.moneySpent());

        try {
            testBook1.addPurchase(32.947, "Apple", "gift for my son jerry");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
        assertEquals(6, testBook1.getTypeList().size());
        assertEquals(2, testBook1.numberOfPurchases());
        assertEquals(42.94, testBook1.moneySpent());

        try {
            testBook1.addPurchase(0, "Apple", "");
            fail();
        } catch (InvalidSpent invalidSpent) {
        }
        assertEquals(6, testBook1.getTypeList().size());
        assertEquals(2, testBook1.numberOfPurchases());
        assertEquals(42.94, testBook1.moneySpent());

        try {
            testBook1.addPurchase(-100, "Apple", "");
            fail();
        } catch (InvalidSpent invalidSpent) {
        }
        assertEquals(6, testBook1.getTypeList().size());
        assertEquals(2, testBook1.numberOfPurchases());
        assertEquals(42.94, testBook1.moneySpent());

        try {
            testBook1.addPurchase(-100.74, "Apple", "");
            fail();
        } catch (InvalidSpent invalidSpent) {
        }
        assertEquals(6, testBook1.getTypeList().size());
        assertEquals(2, testBook1.numberOfPurchases());
        assertEquals(42.94, testBook1.moneySpent());

        try {
            testBook1.addPurchase(20, "Book", "");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
        assertEquals(6, testBook1.getTypeList().size());
        assertEquals(3, testBook1.numberOfPurchases());
        assertEquals(62.94, testBook1.moneySpent());

    }

    @Test
    public void testGetPurchaseList() {
        assertTrue(testBook1.getPurchaseList().isEmpty());
        assertFalse(testBook2.getPurchaseList().isEmpty());
    }

    @Test
    public void testDelete() {
        try {
            testBook2.delete(3);
            fail("unable to delete");
        } catch (InvalidNumberEntered invalidNumberEntered) {
        }
        try {
            testBook2.delete(0);
        } catch (InvalidNumberEntered invalidNumberEntered) {
            fail();
        }
        try {
            testBook2.delete(-1);
            fail();
        } catch (InvalidNumberEntered invalidNumberEntered) {
        }

        assertEquals(39.99, testBook2.moneySpent());
        assertEquals(1, testBook2.numberOfPurchases());
    }
}
