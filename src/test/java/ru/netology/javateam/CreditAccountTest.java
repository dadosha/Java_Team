package ru.netology.javateam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.javateam.CreditAccount;

public class CreditAccountTest {

    @Test
    public void testInitialBalanceLessZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           new CreditAccount(-100, 5000, 15);
        });
    }

    @Test
    public void testInitialCreditLimitLessZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, -5000, 15);
        });
    }

    @Test
    public void testInitialRateLessZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new CreditAccount(0, 5000, -15);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "0,1",
            "0,2500",
            "0,4999",
            "0,5000",
            "1000,1",
            "1000,2500",
            "1000,5999",
            "1000,6000"
    })
    public void shouldPayToPositiveAmount(int startBalance, int addAmount) {
        CreditAccount account = new CreditAccount(
                startBalance,
                5_000,
                15
        );

        Assertions.assertTrue(account.pay(addAmount));

        Assertions.assertEquals(startBalance - addAmount, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "0,-1",
            "0,-2000",
            "1000,0",
            "1000,-1000",
            "1000,-2000"
    })
    public void shouldNotPayZeroAndLessAmount(int startBalance, int addAmount) {
        CreditAccount account = new CreditAccount(
                startBalance,
                5_000,
                15
        );

        Assertions.assertFalse(account.pay(addAmount));

        Assertions.assertEquals(startBalance, account.getBalance());
    }

    @Test
    public void shouldNotPayMoreLimitBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        Assertions.assertFalse(account.pay(6000));

        Assertions.assertEquals(0, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "0,1",
            "0,2500",
            "0,5000",
            "1000,1",
            "1000,2500",
            "1000,5000"
    })
    public void shouldAddToPositiveBalance(int startBalance, int addAmount) {
        CreditAccount account = new CreditAccount(
                startBalance,
                5_000,
                15
        );

        Assertions.assertTrue(account.add(addAmount));

        Assertions.assertEquals(startBalance + addAmount, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "0,0",
            "0,-1",
            "0,-2000",
            "1000,0",
            "1000,-1",
            "1000,-2000"
    })
    public void shouldNotAddZeroAndLessBalance(int startBalance, int addAmount) {
        CreditAccount account = new CreditAccount(
                startBalance,
                5_000,
                15
        );

        Assertions.assertFalse(account.add(addAmount));

        Assertions.assertEquals(startBalance, account.getBalance());
    }

    @ParameterizedTest
    @CsvSource({
            "0",
            "1",
            "99",
            "200",
    })
    public void testYearChangeZeroAndMoreWork(int startBalance) {
        CreditAccount account = new CreditAccount(
                startBalance,
                5_000,
                15
        );

        Assertions.assertEquals(0, account.yearChange());
    }

    @ParameterizedTest
    @CsvSource({
            "1,-2",
            "99,-198",
            "100,-200",
            "200,-400",
    })
    public void testYearChangeLessZero(int payAmount, int expected) {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                200
        );

        Assertions.assertTrue(account.pay(payAmount));

        Assertions.assertEquals(expected, account.yearChange());
    }
}
