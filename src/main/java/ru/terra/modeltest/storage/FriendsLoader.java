package ru.terra.modeltest.storage;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.gui.beans.FriendshipInfo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FriendsLoader {
    private Logger logger = Logger.getLogger(this.getClass());

    public List<FriendshipInfo> loadFriendships(String fileName, List<Agent> agents) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.defaultCharset());
        Map<String, Agent> agentMap = new HashMap<>();
        agents.forEach(a -> agentMap.put(a.getInfo().getName(), a));
        return lines.stream().map(l -> {
            String[] parsedLine = l.split(",");
            logger.info("Friending " + parsedLine[0] + " to " + parsedLine[1]);
            Agent from = agentMap.get(parsedLine[0]);
            Agent to = agentMap.get(parsedLine[1]);
            if (from == null) {
                logger.info("Agent " + parsedLine[0] + " not found");
            }
            if (to == null) {
                logger.info("Agent " + parsedLine[1] + " not found");
            }
            if (from != null && to != null) {
                return new FriendshipInfo(from.getInfo().getUid(), to.getInfo().getUid(), Instant.now());
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
