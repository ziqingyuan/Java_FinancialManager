package Persistence;

import exception.InvalidSpent;
import model.AccountBook;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Writer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class WriterTest {
    private static final String TEST_FILE = "./data/testAccountBook.txt";
    private Writer testWriter;
    private AccountBook testBook;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        testBook = new AccountBook();
        try {
            testBook.addPurchase(100, "car", "gas fee");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
        try {
            testBook.addPurchase(74637, "clothes", "dress fee");
        } catch (InvalidSpent invalidSpent) {
            fail();
        }
    }

    @Test
    void testWriteAccounts() {
        // save account book to file
        testWriter.write(testBook);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            AccountBook testBookLoad = Reader.readAccounts(new File(TEST_FILE));
            assertEquals(747.37, testBookLoad.moneySpent());
            assertEquals(2, testBookLoad.numberOfPurchases());
            assertEquals(6, testBookLoad.getTypeList().size());
            assertEquals("car", testBookLoad.getPurchaseList().get(0).getType());
            assertEquals("clothes", testBookLoad.getPurchaseList().get(1).getType());
        } catch (IOException | Error e) {
            fail("Exception should not have been thrown");
        }
    }
}
