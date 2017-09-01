package ru.terra.modeltest.core;

import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;
import ru.terra.modeltest.core.message.Message;
import ru.terra.modeltest.storage.Storage;
import ru.terra.modeltest.util.AgentUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AgentsWorld {
    private Logger logger = Logger.getLogger(this.getClass());
    private Storage storage;
    private Board board;
    private Map<String, Agent> agentMap;

    public AgentsWorld() {
        agentMap = new ConcurrentHashMap<>();
        board = new Board(this);
    }

    public void addAgent(Agent agent) {
        synchronized (agentMap) {
            logger.info("Adding agent " + agent.getInfo().getName() + " of class " + agent.getClass());
            agentMap.put(agent.getUid(), agent);
        }
    }

    public void postMessageToBoard(Message message) {
        board.postMessage(message);
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }

    public void loadState() {
    }

    public void saveState() {
    }

    public synchronized Map<String, Agent> getAgents() {
        return agentMap;
    }

    public synchronized void recalcAbleFriends() {
        synchronized (agentMap) {
            for (Agent senderAgent : agentMap.values()) {
                //забираем всех агентов
                //фильтруем вопрошаюшего и его друзей
                Map<String, Agent> agents = new HashMap<>();
                Map<String, Boolean> possibleFriends = new HashMap<>();
                logger.info("Before processing possible friends for " + AgentUtil.parseAgentInfo(senderAgent, WorldExecutor.getInstance().getAgentsWorld().getAgents()));
                WorldExecutor.getInstance().getAgentsWorld().getAgents()
                        .values()
                        .stream()
                        .filter(a -> !a.getUid().equals(senderAgent.getUid()))
                        .filter(a -> !senderAgent.getInfo().getFriends().containsKey(a.getUid()))
                        .forEach(a -> agents.put(a.getUid(), a));
                //у оставшихся проверяем условия
                agents.forEach((uid, a) -> possibleFriends.put(uid, checkFriending(WorldExecutor.getInstance().getAgentsWorld().getAgents(), senderAgent, a)));
                //вопрошающему проставляем возможных друзей uid <> друг\нет
                senderAgent.getInfo().setPossibleFriends(possibleFriends);
                logger.info("Processed possible friends for " + AgentUtil.parseAgentInfo(senderAgent, WorldExecutor.getInstance().getAgentsWorld().getAgents()));
            }
        }
    }

    //проверяем смогли бы подружиться вопрошающий и кто-то ещё
    public Boolean checkFriending(Map<String, Agent> agents, Agent asker, Agent agent) {
//        String debug = asker.getUid() + " пробуем подружить с " + agent.getUid();
//        logger.info(debug);

        if (asker instanceof MaleAgent && agent instanceof MaleAgent) {
            return true;
        }

        if (asker instanceof FemaleAgent && agent instanceof FemaleAgent) {
            //девушка с девушкой не могут дружить
            return false;
        }

        if (asker instanceof MaleAgent && agent instanceof FemaleAgent) {
            //если парень хочет знакомиться с девушкой, то надо спросить у друзей-парней, знакома ли она им
            List<String> maleFriends = new ArrayList<>();
            asker.getInfo().getFriends().forEach((uid, m) -> {
                if (m) maleFriends.add(uid);
            });
            //если нет друзей-парней, то можно знакомиться
            if (maleFriends.isEmpty()) {
                logger.info(asker.getUid() + " не имеет друзей парней, return true");
                return true;
            }
            boolean allFriendsAreEmpty = true;

            for (String fuid : maleFriends) {
                if (!agents.get(fuid).getInfo().getFriends().isEmpty() && allFriendsAreEmpty) {
                    allFriendsAreEmpty = false;
                }
            }

            if (allFriendsAreEmpty) {
                //если у друзей нет друзей, то можно
                logger.info(asker.getUid() + " имеет друзей без друзей, return true");
                return true;
            } else {
                //если у друзей есть друзья
                boolean allFriendsDoesntHaveFemaleInFriends = true;
                for (String fuid : maleFriends) {
                    if (!agents.get(fuid).getInfo().getFriends().containsKey(agent.getUid()) && allFriendsDoesntHaveFemaleInFriends)
                        allFriendsDoesntHaveFemaleInFriends = false;
                }
                //если ни у кого в друзьях нет этой девушки то проверяем других девушек у друзей
                if (!allFriendsDoesntHaveFemaleInFriends) {
                    boolean friendHaveOtherFemaleInFriends = false;
                    for (String fuid : maleFriends) {
                        if (agents.get(fuid).getInfo().getFriends().containsValue(false) && !friendHaveOtherFemaleInFriends)
                            friendHaveOtherFemaleInFriends = true;
                    }
                    //если у друзей есть друзья и нет этой девушки, но есть другие девушки, то нельзя
                    if (friendHaveOtherFemaleInFriends) {
                        logger.info(asker.getUid() + " имеет друзей с другой девушкой и не имеет " + agent.getUid() + " в друзьях, return false");
                        return false;
                    } else {
                        logger.info(asker.getUid() + " не имеет друзей с другой девушкой и не имеет " + agent.getUid() + " в друзьях, return true");
                        return true;
                    }
                } else {
                    //если у друзей есть друзья и есть эта девушка, то можно
                    logger.info(asker.getUid() + " имеет друзей с " + agent.getUid() + " в списке");
                    return true;
                }
            }
        }

        return true;
    }
}