package ru.terra.modeltest.storage.gdocs;

import com.google.gdata.client.spreadsheet.CellQuery;
import com.google.gdata.data.spreadsheet.*;
import org.apache.log4j.Logger;
import ru.terra.modeltest.core.agent.Agent;
import ru.terra.modeltest.core.agent.impl.FemaleAgent;
import ru.terra.modeltest.core.agent.impl.MaleAgent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GDocsSaver {
    private Logger logger = Logger.getLogger(this.getClass());
    public static final String DOC_ID = "1Z2Sa0Yy5c5PGnAW-MW-MKKZLPfX8XPV9btEMCO5h3Q4";

    public void saveToGdocs(Map<String, Agent> agentMap) throws Exception {
        GoogleDocsHelper helper = new GoogleDocsHelper();
        SpreadsheetEntry spreadsheetEntry = helper.openSpreadSheet(DOC_ID);
        WorksheetEntry ws = spreadsheetEntry.getWorksheets().get(0);
        URL listFeedUrl = ws.getListFeedUrl();
        ListFeed listFeed = helper.getSpreadsheetService().getFeed(listFeedUrl, ListFeed.class);
        for (ListEntry lfr : listFeed.getEntries()) {
            lfr.delete();
        }

        //cleared

        Map<String, Agent> males = new HashMap<>();
        Map<String, Agent> females = new HashMap<>();
        agentMap.forEach((uid, a) -> {
            if (a instanceof MaleAgent)
                males.put(uid, a);
            else
                females.put(uid, a);
        });


        CellQuery cellQuery = new CellQuery(ws.getCellFeedUrl());
        CellFeed cellFeed = helper.getSpreadsheetService().query(cellQuery, CellFeed.class);

        //vertical - female, horizontal male
        final int[] c = {2};
        final int[] r = {2};

        Map<String, Integer> malesColumIds = new HashMap<>();
        Map<String, Integer> femalesRowIds = new HashMap<>();

        males.keySet().forEach(uid -> {
            try {
                malesColumIds.put(uid, c[0]);
                CellEntry cellEntry = new CellEntry(1, c[0]++, uid);
                cellFeed.insert(cellEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        females.keySet().forEach(uid -> {

            try {
                femalesRowIds.put(uid, r[0]);
                CellEntry cellEntry = new CellEntry(r[0]++, 1, uid);//name
                cellFeed.insert(cellEntry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        males.forEach((uid, a) -> {
            logger.info("Writing " + uid + " friends to table");
            a.getInfo().getPossibleFriends().forEach((possibleFriendUid, isFriend) -> {
                if (agentMap.get(possibleFriendUid) instanceof FemaleAgent) {
                    Integer friendRow = femalesRowIds.get(possibleFriendUid);
                    Integer myColumn = malesColumIds.get(uid);
                    String ps = !isFriend ? " NOT " : "";
                    CellEntry cellEntry = new CellEntry(friendRow, myColumn, uid + ps + " possible with " + possibleFriendUid);//name
                    try {
                        cellFeed.insert(cellEntry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            a.getInfo().getFriends().forEach((friendUid, isFriend) -> {
                if (agentMap.get(friendUid) instanceof FemaleAgent) {
                    Integer friendRow = femalesRowIds.get(friendUid);
                    Integer myColumn = malesColumIds.get(uid);
                    CellEntry cellEntry = new CellEntry(friendRow, myColumn, uid + " friend with " + friendUid);//name
                    try {
                        cellFeed.insert(cellEntry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        });
    }
}
