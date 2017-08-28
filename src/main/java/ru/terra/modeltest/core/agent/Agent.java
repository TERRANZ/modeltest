package ru.terra.modeltest.core.agent;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.condition.Condition;
import ru.terra.modeltest.core.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    protected Logger logger = Logger.getLogger(this.getClass());
    private AgentInfo context;
    private List<Condition> conditions = new ArrayList<>();
    private List<Activity> activities = new ArrayList<>();
    private AgentState state = AgentState.INIT;
    private AgentsWorld world;

    public Agent() {
    }

    public AgentInfo getInfo() {
        return context;
    }

    public void setInfo(AgentInfo info) {
        this.context = info;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void setConditions(List<Condition> conditions) {
        this.conditions = conditions;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public void changeState(AgentState newState) {
        state = newState;
    }

    public AgentState getState() {
        return state;
    }

    public AgentsWorld getWorld() {
        return world;
    }

    public void setWorld(AgentsWorld world) {
        this.world = world;
    }

    public void processMessage(Message message) {
        logger.info("Processing message " + message.getClass());
        boolean allConditionsOk = true;
        for (Condition condition : getConditions()) {
            logger.info("Checkfing condition " + condition.getClass());
            if (!condition.check(this, message)) {
                allConditionsOk = false;
                logger.info("Condition " + condition.getClass() + " failed");
            }
        }
        if (allConditionsOk) {
            logger.info("All conditions is OK, processing activitiess");
            activities.forEach(c -> c.apply(this, message));
            processMessageInt(message);
        }
    }

    protected void processMessageInt(Message m) {

    }
}
