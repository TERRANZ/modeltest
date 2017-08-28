package ru.terra.modeltest.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.terra.modeltest.core.agent.Agent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private ObjectMapper mapper = new ObjectMapper();

    public List<Agent> loadAgents() {
        try {
            return mapper.readValue(new File("agents.json"), new TypeReference<List<Agent>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void persistAgents(List<Agent> agents) {
        try {
            mapper.writeValue(new File("agents.json"), agents);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
