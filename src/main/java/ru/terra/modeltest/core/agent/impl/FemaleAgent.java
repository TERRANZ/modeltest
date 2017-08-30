package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.activity.impl.FriendshipAcceptedActivitiy;
import ru.terra.modeltest.core.activity.impl.MaleToFemaleActivity;
import ru.terra.modeltest.core.agent.Agent;

public class FemaleAgent extends Agent {
    public FemaleAgent() {
        getActivities().add(new MaleToFemaleActivity());
        getActivities().add(new FriendshipAcceptedActivitiy());
    }
}
