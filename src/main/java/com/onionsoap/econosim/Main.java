package com.onionsoap.econosim;

import com.onionsoap.econosim.core.agent.Person;
import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.event.TimeStepEvent;
import com.onionsoap.econosim.core.event.bus.Buses;
import com.onionsoap.econosim.core.registry.Registries;
import com.onionsoap.econosim.core.registry.bus.MarketRegistry;
import com.onionsoap.econosim.core.resource.currency.Currency;
import com.onionsoap.econosim.core.simulation.SimRandom;
import com.onionsoap.econosim.core.simulation.Simulation;
import com.onionsoap.econosim.util.DayLogger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        SimRandom random = new SimRandom();
        try (DayLogger logger = new DayLogger(1, "log.csv")) {
            // create 2 new people
            Person avrie = new Person("Avrie", 1);
            Person tori = new Person("Tori", 2);
            // create 2 new jobs
            Job stocker = new Job("Stocker", Job.JobCategory.SALES_AND_COMMERCE, 1);
            Job cashier = new Job("Cashier", Job.JobCategory.SALES_AND_COMMERCE, 2);
            // create 1 market
            Market walmart = new Market("Walmart");
            // give the market some starting capital
            walmart.getAccount().deposit(new Currency(null, 50_000L));
            // add jobs to the market
            walmart.addNewJob(stocker, 1, 45_000L);
            walmart.addNewJob(cashier, 1, 55_000L);
            // hire our 2 people to work those jobs
            walmart.hireEmployee(avrie, stocker);
            walmart.hireEmployee(tori, cashier);
            // setup event bus
            Buses.TIME_STEP_EVENT_BUS.subscribeRegistry(Registries.MARKET);
            Buses.TIME_STEP_EVENT_BUS.subscribe(Simulation.time);
            // fire 5 time step events
            for (var i = 0; i < 336; i++) {
                Buses.TIME_STEP_EVENT_BUS.propagateEvent(new TimeStepEvent(TimeStepEvent.DAY));
                logger.log();
            }

            Platform.exit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void displayPerson(Person person) {
        System.out.println("--- " + person.getName() + " ---");
        System.out.println("Funds: " + person.getPrimaryHolding().getCount());
        if (person.getJob() != null) { System.out.println("Job: " + person.getJob().getTitle()); }
        else { System.out.println("Job: null"); }
        System.out.println();
    }

    private static void displayMarket(Market market) {
        System.out.println("===== " + market.getName() + " Market =====");
        System.out.println("Employed: " + market.getEmployed());
        System.out.println("Vacancies: " + market.getTotalVacancies());
        System.out.println("Total Labor Cost: $" + market.getLaborCost());
        System.out.println();
    }
}
