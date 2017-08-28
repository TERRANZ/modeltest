package ru.terra.modeltest.core.activity;

import ru.terra.modeltest.core.agent.Agent;

public abstract class Activity {
    private Activity nextActivity;

    public abstract void apply(Agent agent);

    public Activity getNextActivity() {
        return nextActivity;
    }

    public void setNextActivity(Activity nextActivity) {
        this.nextActivity = nextActivity;
    }
}
