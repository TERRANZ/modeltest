package ru.terra.modeltest.core.agent.impl;

import ru.terra.modeltest.core.activity.impl.CheckFriendActivity;
import ru.terra.modeltest.core.activity.impl.FemaleToMaleFriendsActivity;
import ru.terra.modeltest.core.activity.impl.MaleToMaleFriendsActivity;
import ru.terra.modeltest.core.agent.Agent;

public class MaleAgent extends Agent {

    public MaleAgent() {
        getActivities().add(new MaleToMaleFriendsActivity());
        getActivities().add(new FemaleToMaleFriendsActivity());
        getActivities().add(new CheckFriendActivity());
    }

}
