package ru.netology.javateam;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test
    public void shouldThrowIllegalArgumentExceptionTestCase1() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000,
                    1_000,
                    10_000,
                    -5);
        });
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionTestCase2() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000,
                    20_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionTestCase3() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2_000,
                    -20_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionTestCase4() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(1_000,
                    2_000,
                    10_000,
                    5);
        });
    }

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(3_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }


    @Test
    public void shouldAddMoreThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(20_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddToBalanceTestCase1() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(0);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldNotAddToBalanceTestCase2() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(-1_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayTestCase1() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        account.pay(2_000);

        Assertions.assertEquals(2_000, account.getBalance());
    }

    @Test
    public void shouldPayTestCase2() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        account.pay(3_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldPayTestCase3() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        account.pay(4_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldPayTestCase4() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        account.pay(-1_000);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldPayTestCase5() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        account.pay(-0);

        Assertions.assertEquals(4_000, account.getBalance());
    }

    @Test
    public void shouldShowYearChange() {
        SavingAccount account = new SavingAccount(
                4_000,
                1_000,
                10_000,
                5
        );

        Assertions.assertEquals(200, account.yearChange());
    }

    @Test
    public void initialBalanceMoreMaxBalance() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(3_000,
                    0,
                    2_000,
                    5);
        });
    }

}

