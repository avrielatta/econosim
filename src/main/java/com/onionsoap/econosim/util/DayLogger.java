package com.onionsoap.econosim.util;

import com.onionsoap.econosim.core.agent.Person;
import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.registry.Registries;
import com.onionsoap.econosim.core.simulation.Simulation;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DayLogger implements AutoCloseable {
    private final PrintWriter writer;
    private final int logLevel;

    public DayLogger(int logLevel, String path) throws IOException {
        this.writer = new PrintWriter(new FileWriter(path));
        this.logLevel = logLevel;
        if (logLevel <= 0) {
            this.writer.println("day,total_wages_paid,avg_person_funds");
        } else if (logLevel == 1) {
            this.writer.println("day,employed,vacancies,total_wages_paid,avg_person_funds");
        } else {
            this.writer.println("day,population,employed,vacancies,total_wages_paid,avg_person_funds,market_count");
        }
    }

    public void log() {
        if (this.logLevel <= 0) {
            this.logZero();
        } else if (this.logLevel == 1) {
            this.logOne();
        } else {
            this.logTwo();
        }
    }

    private void logZero() {
        int day = Simulation.time.getDays();
        long totalWagesPaid = this.getTotalWagesPaid();
        long avgPersonFunds = this.getAvgPersonFunds();
        this.writer.println(day + "," + totalWagesPaid + "," + avgPersonFunds);
    }

    private void logOne() {
        int day = Simulation.time.getDays();
        int employed = this.getEmployed();
        int vacancies = this.getVacancies();
        long totalWagesPaid = this.getTotalWagesPaid();
        long avgPersonFunds = this.getAvgPersonFunds();
        this.writer.println(day + "," + employed + "," + vacancies + "," + totalWagesPaid + "," + avgPersonFunds);
    }

    private void logTwo() {
        int day = Simulation.time.getDays();
        int population = Registries.PERSON.getEntries().size();
        int employed = this.getEmployed();
        int vacancies = this.getVacancies();
        long totalWagesPaid = this.getTotalWagesPaid();
        long avgPersonFunds = this.getAvgPersonFunds();
        int marketCount = Registries.MARKET.getEntries().size();
        this.writer.println(
                day + "," +
                population + "," +
                employed + "," +
                vacancies + "," +
                totalWagesPaid + "," +
                avgPersonFunds + "," +
                marketCount
        );
    }

    private long getTotalWagesPaid() {
        long wages = 0;
        for (Market m : Registries.MARKET.getEntries()) {
            wages += m.getLaborCost();
        }
        return wages;
    }

    private long getAvgPersonFunds() {
        long funds = 0;
        int count = 0;
        for (Person p : Registries.PERSON.getEntries()) {
            funds += p.getPrimaryHolding().getCount();
            count++;
        }
        return funds / count;
    }

    private int getEmployed() {
        int total = 0;
        for (Market m : Registries.MARKET.getEntries()) {
            total += m.getEmployed();
        }
        return total;
    }

    private int getVacancies() {
        int total = 0;
        for (Market m : Registries.MARKET.getEntries()) {
            total += m.getTotalVacancies();
        }
        return total;
    }

    @Override
    public void close() throws Exception { writer.close(); }
}
