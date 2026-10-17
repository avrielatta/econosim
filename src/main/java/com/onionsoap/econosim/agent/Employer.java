package com.onionsoap.econosim.agent;

import com.onionsoap.econosim.agent.market.labor.Job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Employer {
    protected int totalVacancies;
    protected int employed;
    protected long laborCost;

    protected List<Person> employees = new ArrayList<>();
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

    public void hire(Person person, Job job) {
        // check if job vacancy exists
        if (vacancies.containsKey(job) && vacancies.get(job) > 0) {
            if (person.getSkill() >= job.getSkillLevel()) {
                this.fillPosition(job);
                this.employees.add(person);
                person.setJob(job);
                // adjust counts
                this.totalVacancies--;
                this.employed++;
            }
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

    /**
     * Gets called once weekly to pay all employees' wages
     */
    public void payEmployees() {
        for (Person person : this.employees) {
            long amount = this.wages.get(person.getJob()) / 52;
            person.receivePayment(amount);
        }
    }
}
