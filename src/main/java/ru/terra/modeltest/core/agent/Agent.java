package ru.terra.modeltest.core.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import ru.terra.modeltest.core.AgentsWorld;
import ru.terra.modeltest.core.activity.Activity;
import ru.terra.modeltest.core.condition.Condition;
import ru.terra.modeltest.core.message.Message;

import java.util.ArrayList;
import java.util.List;

public class Agent {
    @JsonIgnore
    protected Logger logger = Logger.getLogger(this.getClass());
    @JsonIgnore
    private List<Condition> conditions = new ArrayList<>();
    @JsonIgnore
    private List<Activity> activities = new ArrayList<>();
    @JsonIgnore
    private AgentsWorld world;
    private AgentInfo context;
    private AgentState state = AgentState.INIT;

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
        logger.info(getInfo().getName() + " Processing message " + message.getClass());
        boolean allConditionsOk = true;
        for (Condition condition : getConditions()) {
            logger.info(getInfo().getName() + " Checking condition " + condition.getClass());
            if (!condition.check(this, message)) {
                allConditionsOk = false;
                logger.info(getInfo().getName() + " Condition " + condition.getClass() + " failed");
            }
        }
        if (allConditionsOk) {
            logger.info(getInfo().getName() + " All conditions is OK, processing activitiess");
            activities.forEach(c -> {
                if (c.applicable(message)) c.apply(this, message);
            });
            processMessageInt(message);
        }
    }

    protected void processMessageInt(Message m) {

    }
}
