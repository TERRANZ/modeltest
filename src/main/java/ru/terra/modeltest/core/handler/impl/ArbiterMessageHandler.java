package ru.terra.modeltest.core.handler.impl;

import ru.terra.modeltest.core.WorldExecutor;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.handler.MessageHandler;
import ru.terra.modeltest.core.message.impl.AskArbiterMessage;

import java.util.HashMap;
import java.util.Map;

public class ArbiterMessageHandler implements MessageHandler<AskArbiterMessage> {
    @Override
    public void apply(Agent agent, AskArbiterMessage message) {
        //забираем всех агентов
        //фильтруем вопрошаюшего и его друзей
        Map<String, Agent> agents = new HashMap<>();
        WorldExecutor.getInstance().getAgentsWorld().getAgents()
                .values()
                .stream()
                .filter(a -> !a.getUid().equals(message.getSenderUID()))
                .forEach(a -> agents.put(a.getUid(), a));

        //у оставшихся проверяем условия
        //возвращаем мапу uid <> друг\нет
    }
}
