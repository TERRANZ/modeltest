package ru.terra.modeltest.gui.beans;

import java.time.Instant;

public class FriendshipInfo {
    public String from;
    public String to;
    public Instant when;

    public FriendshipInfo(String from, String to, Instant when) {
        this.from = from;
        this.to = to;
        this.when = when;
    }
}
