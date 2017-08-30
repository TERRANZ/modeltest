package ru.terra.modeltest.storage;

import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.AgentInfo;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AgentsLoader {
    public List<Agent> loadAgents(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.forName("UTF-8"));
        return lines.stream().map(l -> {
            String[] parsedLine = l.split(",");
            Agent ret;
            if (parsedLine[1].equalsIgnoreCase("m"))
                ret = new MaleAgent();
            else
                ret = new FemaleAgent();
            AgentInfo ai = new AgentInfo();
            ai.setName(parsedLine[0]);
            ai.setUid(UUID.randomUUID().toString());
            ret.setInfo(ai);
            return ret;
        }).collect(Collectors.toList());
    }
}
