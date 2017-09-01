package ru.terra.modeltest.util;

import ru.terra.modeltest.core.agent.Agent;

import java.util.Map;

public class AgentUtil {
    public static String parseAgentInfo(Agent agent, Map<String, Agent> agentsMap) {
        StringBuilder sb = new StringBuilder().append("Agent: ").append(agent.getInfo().getName()).append(" friends: ").append(agent.getInfo().getFriends().size());
        agent.getInfo().getFriends().keySet().forEach(uid -> sb.append(", ").append(agentsMap.get(uid).getInfo().getName()));
        sb.append(" possible friends: ");
        sb.append(agent.getInfo().getPossibleFriends().values().stream().filter(p -> p).count());
        agent
                .getInfo()
                .getPossibleFriends()
                .forEach(
                        (uid, p) -> {
                            if (p)
                                sb.append(", ").append(agentsMap.get(uid).getInfo().getName());
                        }
                );
        return sb.toString();
    }
}
