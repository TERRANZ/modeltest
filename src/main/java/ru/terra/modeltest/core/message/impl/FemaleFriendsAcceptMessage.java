package ru.terra.modeltest.core.message.impl;

import ru.terra.modeltest.core.message.Message;

import java.util.Set;

public class FemaleFriendsAcceptMessage extends Message {
    private Set<String> uids;

    public FemaleFriendsAcceptMessage(String senderUID, String targetUID, Set<String> uids) {
        super(senderUID, targetUID);
        this.uids = uids;
    }

    public Set<String> getUids() {
        return uids;
    }

    public void setUids(Set<String> uids) {
        this.uids = uids;
    }
}
