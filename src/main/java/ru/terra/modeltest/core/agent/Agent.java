package ru.terra.modeltest.core.agent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import ru.terra.modeltest.core.condition.Condition;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Agent {
    @JsonIgnore
    protected Logger logger = Logger.getLogger(this.getClass());
    @JsonIgnore
    private List<Condition> conditions = new ArrayList<>();
    @JsonIgnore
    private Map<Class<? extends Message>, List<MessageHandler>> handlers = new HashMap<>();
    private AgentInfo agentInfo;
    private String uid;

    public Agent() {
    }

    public AgentInfo getInfo() {
        return agentInfo;
    }

    public void setInfo(AgentInfo info) {
        this.agentInfo = info;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public Map<Class<? extends Message>, List<MessageHandler>> getHandlers() {
        return handlers;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void processMessage(Message message) {
        logger.info(getInfo().getName() + " Processing message " + message.getClass());
        boolean allConditionsOk = true;
        for (Condition condition : getConditions()) {
            if (!condition.check(this, message)) {
                allConditionsOk = false;
                logger.info(getInfo().getName() + " Condition " + condition.getClass() + " failed");
            }
        }
        if (allConditionsOk) {
            if (handlers.containsKey(message.getClass())) {
                handlers.get(message.getClass()).forEach(c -> c.apply(this, message));
            }
            processMessageInt(message);
        }
    }

    protected void processMessageInt(Message m) {

    }

    protected void addHandler(Class<? extends Message> messageClass, MessageHandler messageHandler) {
        if (!handlers.containsKey(messageClass))
            handlers.put(messageClass, new ArrayList<>());

        handlers.get(messageClass).add(messageHandler);
    }
}
