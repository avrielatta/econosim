package com.onionsoap.econosim;

import com.onionsoap.econosim.core.agent.Person;
import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.simulation.SimRandom;
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
            Person avrie = new Person("Avrie", 1);
            Person tori = new Person("Tori", 2);

            Job machineOperatorJob = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 1);
            Job accountantJob = new Job("Accountant", Job.JobCategory.OFFICE_AND_ADMIN, 2);

            Market ironMarket = new Market("Iron");
            ironMarket.addNewJob(machineOperatorJob, 1, 50_000L);
            ironMarket.addNewJob(accountantJob, 1, 85_000L);
            ironMarket.hireEmployee(avrie, machineOperatorJob);
            ironMarket.hireEmployee(tori, accountantJob);

            ironMarket.fireEmployee(avrie, false);

            avrie.getFunds().addCount(50_000L);

            logger.log();

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
        System.out.println("Funds: " + person.getFunds().getCount());
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
