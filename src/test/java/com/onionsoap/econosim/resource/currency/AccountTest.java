package com.onionsoap.econosim.resource.currency;

import com.onionsoap.econosim.core.nation.Nation;
import com.onionsoap.econosim.core.resource.currency.Account;
import com.onionsoap.econosim.core.resource.currency.Currency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountTest {
    @Test
    void depositIncreasesTotal() {
        Account account = new Account();
        account.deposit(new Currency(null, 50_000L));

        assertEquals(50_000L, account.checkPrimaryHolding().getCount());
    }

    @Test
    void withdrawDecreasesTotal() {
        Account account = new Account();
        account.deposit(new Currency(null, 50_000L));
        Currency cur = account.withdraw(null, 25_000L);

        assertEquals(25_000L, account.checkPrimaryHolding().getCount());
        assertEquals(25_000L, cur.getCount());
    }

    @Test
    void transferAllFullyTransfers() {
        Account account1 = new Account();
        Account account2 = new Account();
        account1.deposit(new Currency(null, 50_000L));
        account1.transferAllHoldingsTo(account2);

        assertEquals(0, account1.checkPrimaryHolding().getCount());
        assertEquals(50_000L, account2.checkPrimaryHolding().getCount());
    }

    @Test
    void transferDenominationWorks() {
        Account account1 = new Account();
        Account account2 = new Account();
        account1.deposit(new Currency(null, 50_000L));
        account1.transferDenominationHoldingTo(account2, null);

        assertEquals(0, account1.checkPrimaryHolding().getCount());
        assertEquals(50_000L, account2.checkPrimaryHolding().getCount());
    }

    @Test
    void transferPartialOnlyTransfersSetAmount() {
        Account account1 = new Account();
        Account account2 = new Account();
        account1.deposit(new Currency(null, 50_000L));
        account1.transferPartialHoldingTo(account2, null, 25_000L);

        assertEquals(25_000L, account1.checkPrimaryHolding().getCount());
        assertEquals(25_000L, account2.checkPrimaryHolding().getCount());
    }

    @Test
    void transferPartialFailsIfAmountUnavailable() {
        Account account1 = new Account();
        Account account2 = new Account();
        account1.deposit(new Currency(null, 50_000L));
        account1.transferPartialHoldingTo(account2, null, 51_000L);

        assertEquals(50_000L, account1.checkPrimaryHolding().getCount());
        assertEquals(0,  account2.checkPrimaryHolding().getCount());
    }

    @Test
    void depositOnlyAffectsOneDenomination() {
        Account account = new Account();
        Nation us = new Nation("United States", 100_000);

        account.deposit(new Currency(null, 50_000L));
        account.deposit(new Currency(us, 25_000L));

        assertEquals(50_000L, account.checkPrimaryHolding().getCount());
        assertEquals(25_000L, account.checkHolding(us).getCount());
    }

    @Test
    void withdrawOnlyAffectsOneDenomination() {
        Account account = new Account();
        Nation us = new Nation("United States", 100_000);

        account.deposit(new Currency(null, 50_000L));
        account.deposit(new Currency(us, 50_000L));
        account.withdraw(us, 25_000L);

        assertEquals(50_000L, account.checkPrimaryHolding().getCount());
        assertEquals(25_000L, account.checkHolding(us).getCount());
    }
}
