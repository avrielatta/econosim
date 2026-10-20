package com.onionsoap.econosim.core.agent.market.labor;

import com.onionsoap.econosim.core.agent.Employer;

public class Job {
    private final String title;
    private final JobCategory category;
    private Employer location;
    private int skillLevel;

    public Job(String title, JobCategory category, int skillLevel) {
        this.title = title;
        this.category = category;
        this.skillLevel = skillLevel;
    }

    public Job(String title, JobCategory category, Employer location) {
        this.title = title;
        this.category = category;
        this.location = location;
    }

    public String getTitle() { return this.title; }
    public JobCategory getCategory() { return this.category; }
    public Employer getLocation() { return this.location; }
    public int getSkillLevel() { return this.skillLevel; }

    public void setLocation(Employer location) { this.location = location;}

    public enum JobCategory {
        MANAGEMENT_AND_BUSINESS,
        PROFESSIONAL_AND_TECH,
        HEALTH_AND_SOCIAL,
        OFFICE_AND_ADMIN,
        MANUFACTURING_AND_INDUSTRY,
        CONSTRUCTION_AND_TRADES,
        TRANSPORT_AND_LOGISTICS,
        AGRICULTURE_AND_NATURAL,
        SALES_AND_COMMERCE,
        HOSPITALITY_AND_PERSONAL,
        ART_MEDIA_AND_ENTERTAINMENT,
        PUBLIC_AND_PROTECTIVE,
        DOMESTIC_AND_OTHERS
    }
}
