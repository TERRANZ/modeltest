package ru.terra.modeltest.core.agent;

import java.util.Map;

public class AgentInfo {
    private String uid;
    private String name;
    private Map<String, Boolean> friends;//uid -> male or not

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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
}
