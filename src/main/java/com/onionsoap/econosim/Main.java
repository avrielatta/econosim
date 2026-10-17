package com.onionsoap.econosim;

import com.onionsoap.econosim.agent.Person;
import com.onionsoap.econosim.agent.market.Market;
import com.onionsoap.econosim.agent.market.labor.Job;
import com.onionsoap.econosim.input.InputHandler;
import com.onionsoap.econosim.input.KeyInputHandler;
import com.onionsoap.econosim.nation.Nation;
import com.onionsoap.econosim.nation.trade.Payload;
import com.onionsoap.econosim.nation.trade.Trade;
import com.onionsoap.econosim.resource.Currency;
import com.onionsoap.econosim.simulation.SimTime;
import com.onionsoap.econosim.ui.UIModuleNation;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Person avrie = new Person("Avrie", 1);
        Person tori = new Person("Tori", 2);

        Job machineOperatorJob = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY);
        Job accountantJob = new Job("Accountant", Job.JobCategory.OFFICE_AND_ADMIN);

        Market ironMarket = new Market("Iron");
        ironMarket.addNewJob(machineOperatorJob, 1, 1, 50_000L);
        ironMarket.addNewJob(accountantJob, 1, 2, 85_000L);
        ironMarket.hire(avrie, machineOperatorJob);
        ironMarket.hire(tori, accountantJob);

        displayPerson(avrie);
        displayPerson(tori);

        displayMarket(ironMarket);

        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void displayPerson(Person person) {
        System.out.println("--- " + person.getName() + " ---");
        System.out.println("Funds: " + person.getFunds());
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
