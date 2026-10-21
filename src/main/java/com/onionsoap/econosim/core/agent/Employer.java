package com.onionsoap.econosim.core.agent;

import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.resource.currency.Account;
import com.onionsoap.econosim.core.resource.currency.Currency;

import java.util.HashMap;
import java.util.Map;

public abstract class Employer {
    protected int totalVacancies;
    protected int employed;
    protected long laborCost;
    protected Account holdings;

    protected Map<Person, Job> employees = new HashMap<>();
    protected Map<Job, Integer> vacancies = new HashMap<>();
    protected Map<Job, Integer> filledPositions = new HashMap<>();
    protected Map<Job, Long> wages = new HashMap<>();

    protected abstract void calculateLaborCost();

    public int getTotalVacancies() { return this.totalVacancies; }
    public int getEmployed() { return this.employed; }
    public long getLaborCost() { return this.laborCost; }

    public void setTotalVacancies(int total) { this.totalVacancies = total; }
    public void setEmployed(int total) { this.employed = total; }

    public void addNewJob(Job job, int quantity, long salary) {
        this.vacancies.put(job, quantity);
        this.filledPositions.put(job, 0);
        this.wages.put(job, salary);
        this.totalVacancies += quantity;

        if (job.getLocation() == null) job.setLocation(this);
    }

    public void hireEmployee(Person person, Job job) {
        // check if job vacancy exists
        if (vacancies.containsKey(job) && vacancies.get(job) > 0) {
            if (person.getSkill() >= job.getSkillLevel()) {
                this.fillPosition(job);
                this.employees.put(person, job);
                person.setJob(job);
                // adjust counts
                this.totalVacancies--;
                this.employed++;
            }
        }
    }

    public void fireEmployee(Person person, boolean isLayoff) {
        // remove key, if it exists
        var job = employees.remove(person);
        if (job != null) {
            this.emptyPosition(job, isLayoff);
            person.setJob(null);
            // adjust counts
            if (!isLayoff) this.totalVacancies++; // only adjusted based on firing arguments
            this.employed--;
        }
    }

    private void fillPosition(Job job) {
        // decrement the vacancies for this position
        this.vacancies.replace(job, this.vacancies.get(job), this.vacancies.get(job) - 1);
        // check if this position is already in the filled list
        if (this.filledPositions.containsKey(job)) {
            // increment the filled value for this position
            this.filledPositions.replace(job, this.filledPositions.get(job), this.filledPositions.get(job) + 1);
        } else {
            // otherwise, add it to the list
            this.filledPositions.put(job, 1);
        }
    }

    private void emptyPosition(Job job, boolean isLayoff) {
        // check if this is a layoff
        if (!isLayoff) {
            // if not, check if vacancies already exist
            if (this.vacancies.containsKey(job)) {
                // if so, increment
                this.vacancies.replace(job, this.vacancies.get(job), this.vacancies.get(job) + 1);
            } else {
                // if not, add it to the map
                this.vacancies.put(job, 1);
            }
        } // if this isn't a layoff, nothing needs to be done w vacancies mapping

        // check for filled position
        if (this.filledPositions.containsKey(job)) {
            // check if the number of individuals working this position is 1
            if (this.filledPositions.get(job) == 1) {
                // if so, remove the entire mapping
                this.filledPositions.remove(job);
            } else {
                // if not, decrement the value
                this.filledPositions.replace(
                        job,
                        this.filledPositions.get(job),
                        this.filledPositions.get(job) - 1
                );
            }
        }

        // if this is somehow both a layoff and the position was never filled, this method does nothing lol
    }

    /**
     * Gets called once weekly to pay all employees' wages
     */
    public void payEmployees() {
        for (Person person : this.employees.keySet()) {
            long amount = this.wages.get(person.getJob()) / 52;
            person.getAccount().deposit(new Currency(null, amount));
        }
    }
}
