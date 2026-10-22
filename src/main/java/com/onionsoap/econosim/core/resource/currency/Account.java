package com.onionsoap.econosim.core.resource.currency;

import com.onionsoap.econosim.core.nation.Nation;

import java.util.HashMap;

/**
 * Represents a banking account with financial holdings.
 *
 * Only one account is necessary per agent, as <code>holdings</code> is a {@link HashMap} with an arbitrary number
 * of denominations of {@link Currency}.
 */
public class Account {
    /**
     * Mapping of all currently held {@link Currency} objects.
     */
    private final HashMap<Nation, Currency> holdings;
    private Nation primaryIssuer;

    /**
     * Blank constructor for new <code>Account</code> object with no initial holdings or denominations.
     */
    public Account() {
        this(new HashMap<>(), null);
    }

    /**
     * Constructor for new <code>Account</code> object with no initial holdings but a single declared denomination.
     *
     * This constructor initializes holding {@link Currency} count to <code>0</code> for declared denomination.
     * @param nation Denomination account initially intended to hold
     */
    public Account(Nation nation) {
        this(nation, new Currency(nation, 0));
    }

    /**
     * Constructor for new <code>Account</code> object with initial holding of a single denomination.
     * @param nation Denomination of initial holding
     * @param currency {@link Currency} object representing the initial holdings, themselves
     */
    public Account(Nation nation, Currency currency) {
        var map = new HashMap<Nation, Currency>();
        map.put(nation, currency);
        this(map, nation);
    }

    /**
     * Constructor for new <code>Account</code> with preexisting holdings.
     * @param holdings {@link HashMap} of preexisting holdings
     */
    public Account(HashMap<Nation, Currency> holdings, Nation primaryIssuer) {
        this.holdings = holdings;
        this.primaryIssuer = primaryIssuer;
    }

    public HashMap<Nation, Currency> getHoldings() { return this.holdings; }
    public Nation getPrimaryIssuer() { return this.primaryIssuer; }

    public void setPrimaryIssuer(Nation primaryIssuer) { this.primaryIssuer = primaryIssuer; }

    /**
     * Deposit a {@link Currency} object into this <code>Account</code>'s <code>holdings</code>.
     * @param currency <code>Currency</code> to deposit
     */
    public void deposit(Currency currency) {
        if (this.holdings.containsKey(currency.getIssuer())) {
            this.holdings.replace(
                    currency.getIssuer(),
                    new Currency(
                            currency.getIssuer(),
                            this.holdings.get(currency.getIssuer()).getCount() + currency.getCount()
                    )
            );
        } else {
            this.holdings.put(currency.getIssuer(), currency);
        }
    }

    /**
     * Withdraw a {@link Currency} object from this <code>Account</code>'s <code>holdings.</code>
     * @param denomination Issuing {@link Nation}
     * @param amount Amount of <code>Currency</code> to withdraw
     * @return A new <code>Currency</code> object based on parameters, or <code>null</code> if unavailable.
     */
    public Currency withdraw(Nation denomination, long amount) {
        // check for the specified denomination
        if (this.holdings.containsKey(denomination)) {
            // check if there is enough available
            if (this.holdings.get(denomination).getCount() >= amount) {
                // drain balance
                this.holdings.replace(
                        denomination,
                        new Currency(
                                denomination,
                                this.holdings.get(denomination).getCount() - amount
                        )
                );
                // and return the currency
                return new Currency(denomination, amount);
            }
        }

        return null;
    }

    public Currency checkPrimaryHolding() {
        return this.checkHolding(this.primaryIssuer);
    }

    public Currency checkHolding(Nation nation) {
        if (this.holdings.containsKey(nation)) {
            return this.holdings.get(nation);
        } else {
            return new Currency(nation, 0);
        }
    }

    /**
     * Transfers all {@link Currency} holdings, regardless of denomination, to specified <code>Account</code>.
     * @param destination <code>Account</code> to transfer to
     */
    public void transferAllHoldingsTo(Account destination) {
        destination.receiveAllHoldingsFrom(this);
    }

    /**
     * Transfers all {@link Currency} holdings, regardless of denomination, from specified <code>Account</code>.
     * @param source <code>Account</code> to transfer from
     */
    private void receiveAllHoldingsFrom(Account source) {
        // iterate over source holding keys
        for (Nation n : source.getHoldings().keySet()) {
            Currency cur = source.withdraw(n, source.getHoldings().get(n).getCount());
            if (cur != null) {
                this.deposit(cur);
            }
        }
    }

    /**
     * Transfers all {@link Currency} holdings of a single denomination to specified <code>Account</code>.
     * @param destination <code>Account</code> to transfer to
     * @param denomination Issuing {@link Nation} to target
     */
    public void transferDenominationHoldingTo(Account destination, Nation denomination) {
        destination.receiveDenominationHoldingFrom(this, denomination);
    }

    /**
     * Transfers all {@link Currency} holdings of a single denomination from specified <code>Account</code>.
     * @param source <code>Account</code> to transfer from
     * @param denomination Issuing {@link Nation} to target
     */
    private void receiveDenominationHoldingFrom(Account source, Nation denomination) {
        long amount = source.getHoldings().get(denomination).getCount();
        Currency cur = source.withdraw(denomination, amount);
        if (cur != null) {
            this.holdings.put(denomination, cur);
        }
    }

    /**
     * Transfers {@link Currency} holding, in the denomination and amount provided, to specified <code>Account</code>.
     * @param destination <code>Account</code> to transfer to
     * @param denomination Issuing {@link Nation} to target
     * @param amount <code>Currency</code> amount to transfer
     */
    public void transferPartialHoldingTo(Account destination, Nation denomination, long amount) {
        destination.receivePartialHoldingFrom(this, denomination, amount);
    }

    /**
     * Transfers {@link Currency} holding, in the denomination and amount provided, from specified <code>Account</code>.
     * @param source <code>Account</code> to transfer to
     * @param denomination Issuing {@link Nation} to target
     * @param amount <code>Currency</code> amount to transfer
     */
    private void receivePartialHoldingFrom(Account source, Nation denomination, long amount) {
        Currency cur = source.withdraw(denomination, amount);
        if (cur != null) {
            this.deposit(cur);
        }
    }
}
