package com.onionsoap.econosim.agent;

import com.onionsoap.econosim.core.agent.Person;
import com.onionsoap.econosim.core.agent.market.Market;
import com.onionsoap.econosim.core.agent.market.labor.Job;
import com.onionsoap.econosim.core.registry.Registries;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployerTest {
    @Test
    void hiringFillsVacancyAndUpdatesCounts() {
        Market market = new Market("Iron");
        Job job = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 1);
        market.addNewJob(job, 1, 50_000L);
        Person p = new Person("Avrie", 1);

        market.hireEmployee(p, job);

        assertEquals(1, market.getEmployed());
        assertEquals(0, market.getTotalVacancies());
        assertSame(job, p.getJob());

        // remove person from registry once completed
        Registries.PERSON.unregister(p);
    }

    @Test
    void firingRestoresVacancyAndUpdatesCounts() {
        Market market = new Market("Iron");
        Job job = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 1);
        market.addNewJob(job, 1, 50_000L);
        Person p = new Person("Avrie", 1);

        market.hireEmployee(p, job);
        market.fireEmployee(p, false);

        assertEquals(0, market.getEmployed());
        assertEquals(1, market.getTotalVacancies());
        assertNull(p.getJob());

        // remove person from registry once completed
        Registries.PERSON.unregister(p);
    }

    @Test
    void layoffDoesNotRestoreVacancy() {
        Market market = new Market("Iron");
        Job job = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 1);
        market.addNewJob(job, 1, 50_000L);
        Person p = new Person("Avrie", 1);

        market.hireEmployee(p, job);
        market.fireEmployee(p, true);

        assertEquals(0, market.getTotalVacancies());

        // remove person from registry once completed
        Registries.PERSON.unregister(p);
    }

    @Test
    void hiringUnqualifiedFails() {
        Market market = new Market("Iron");
        Job job = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 2);
        market.addNewJob(job, 1, 50_000L);
        Person p = new Person("Avrie", 1);

        market.hireEmployee(p, job);

        assertEquals(0, market.getEmployed());
        assertEquals(1, market.getTotalVacancies());
        assertNull(p.getJob());

        // remove person from registry once completed
        Registries.PERSON.unregister(p);
    }

    @Test
    void hiringWithoutVacancyFails() {
        Market market = new Market("Iron");
        Job job = new Job("Machine Operator", Job.JobCategory.MANUFACTURING_AND_INDUSTRY, 1);
        Person p = new Person("Avrie", 1);

        market.hireEmployee(p, job);

        assertEquals(0, market.getEmployed());
        assertEquals(0, market.getTotalVacancies());
        assertNull(p.getJob());

        // remove person from registry once completed
        Registries.PERSON.unregister(p);
    }
}
