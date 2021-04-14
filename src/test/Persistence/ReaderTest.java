package Persistence;

import model.AccountBook;
import org.junit.jupiter.api.Test;
import persistence.Reader;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    void testConstructor() {
        new Reader();
    }

    @Test
    void testParseAccountsFile1() {
        try {
            AccountBook accountBook1 = Reader.readAccounts(new File("./data/testAccountBook1.txt"));
            assertEquals(2, accountBook1.numberOfPurchases());
            assertEquals(138.47, accountBook1.moneySpent());
            assertEquals("accommodation", accountBook1.getPurchaseList().get(0).getType());
            assertEquals("grocery", accountBook1.getPurchaseList().get(1).getType());

        } catch (IOException | Error e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testParseAccountsFile2() {
        try {
            AccountBook accountBook2 = Reader.readAccounts(new File("./data/testAccountBook2.txt"));
            assertEquals(2, accountBook2.numberOfPurchases());
            assertEquals(1100.37, accountBook2.moneySpent());
            assertEquals("education", accountBook2.getPurchaseList().get(0).getType());
            assertEquals("accommodation", accountBook2.getPurchaseList().get(1).getType());
            assertEquals(5, accountBook2.getTypeList().size());

        } catch (IOException | Error e) {
            fail("Exceptions should not have been thrown");
        }
    }

    @Test
    void testIOException() {
        try {
            Reader.readAccounts(new File("./path/does/not/exist/testAccount.txt"));
        } catch (IOException e) {
            // expected
        }

        try {
            Reader.readAccounts(new File(".data/testAccountBook3.txt"));
        } catch (IOException e) {
        }
    }
}
