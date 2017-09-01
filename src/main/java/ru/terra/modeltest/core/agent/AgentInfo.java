package ru.terra.modeltest.core.agent;

import java.util.HashMap;
import java.util.Map;

public class AgentInfo {
    private String name;
    private Map<String, Boolean> friends = new HashMap<>();//uid -> male or not
    private Map<String, Boolean> possibleFriends = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Boolean> getFriends() {
        return friends;
    }

    public void setFriends(Map<String, Boolean> friends) {
        this.friends = friends;
    }

    public Map<String, Boolean> getPossibleFriends() {
        return possibleFriends;
    }

    public void setPossibleFriends(Map<String, Boolean> possibleFriends) {
        this.possibleFriends = possibleFriends;
    }
}
