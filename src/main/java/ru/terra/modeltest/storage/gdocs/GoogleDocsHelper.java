package ru.terra.modeltest.storage.gdocs;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.ServiceException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Date: 14.02.15
 * Time: 12:05
 */
public class GoogleDocsHelper {

    private Logger logger = Logger.getLogger(this.getClass());
    private static final String PROJECT_NAME = "ru-terra-md";

    private SpreadsheetService spreadsheetService;
    private FeedURLFactory factory;
    private DocsService docsService;
    private Drive drive;

    public GoogleDocsHelper() {
        factory = FeedURLFactory.getDefault();
        spreadsheetService = new SpreadsheetService(PROJECT_NAME);
        docsService = new DocsService(PROJECT_NAME);
        spreadsheetService.setOAuth2Credentials(getGoogleCredential());
        docsService.setOAuth2Credentials(getGoogleCredential());
        drive = new Drive.Builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), getGoogleCredential()).setApplicationName(PROJECT_NAME).build();
    }

    private GoogleCredential getGoogleCredential() {
        try {
            GoogleCredential credential = new GoogleCredential.Builder()
                    .setServiceAccountId("ru-terra-md@ru-terra-md.iam.gserviceaccount.com")
                    .setServiceAccountPrivateKeyFromP12File(new File(this.getClass().getResource("/md.p12").getFile()))
                    .setServiceAccountScopes(Arrays.asList("https://spreadsheets.google.com/feeds", "https://docs.google.com/feeds", "https://www.googleapis.com/auth/drive"))
                    .setTransport(new NetHttpTransport())
                    .setJsonFactory(JacksonFactory.getDefaultInstance())
                    .build();
            return credential;
        } catch (Exception e) {
            logger.error("Unable to login to google docs", e);
        }
        return null;
    }

    public List<String> listSpreadsheets() throws IOException, ServiceException {
        return spreadsheetService.getFeed(factory.getSpreadsheetsFeedUrl(), SpreadsheetFeed.class).getEntries().stream().map(s -> new String(s.getId())).collect(Collectors.toList());
    }

    public String createSpreadSheet(String name) throws Exception {
        DocumentListEntry newDocument = new com.google.gdata.data.docs.SpreadsheetEntry();
        newDocument.setTitle(new PlainTextConstruct(name));
        URL GOOGLE_DRIVE_FEED_URL = new URL("https://docs.google.com/feeds/default/private/full/");
        newDocument = docsService.insert(GOOGLE_DRIVE_FEED_URL, newDocument);
        final String newDocId = newDocument.getDocId();
        com.google.api.services.drive.model.File newFile = drive.files().get(newDocId).execute();
        if (newFile != null) {
            logger.debug("link for new spreadsheet = " + newFile.getWebViewLink());
            Permission permission = new Permission();
            permission.setType("anyone");
            permission.setRole("writer");
            drive.permissions().create(newDocId, permission).execute();
        }
        return newFile.getId();
    }

    public SpreadsheetEntry openSpreadSheet(String id) throws Exception {
        SpreadsheetFeed feed = spreadsheetService.getFeed(factory.getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        if (spreadsheets.size() == 0) {
            logger.warn("No spreadsheets in storage");
            return null;
        }

        Optional<SpreadsheetEntry> targetSpreadsheet = spreadsheets.stream().filter(s -> s.getId().contains(id)).findFirst();
        if (!targetSpreadsheet.isPresent()) {
            logger.warn("No spreadsheets for url " + id);
            return null;
        }

        SpreadsheetEntry spreadsheet = targetSpreadsheet.get();
        logger.debug(spreadsheet.getTitle().getPlainText());
        return spreadsheet;
    }

    public WorksheetEntry openSheet(String name, SpreadsheetEntry spreadsheet) throws IOException, ServiceException {
        WorksheetFeed worksheetFeed = spreadsheetService.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class);
        Optional<WorksheetEntry> wo = worksheetFeed.getEntries().stream().filter(w -> w.getTitle().getPlainText().equals(name)).findFirst();
        return wo.isPresent() ? wo.get() : null;
    }

    public Map<Integer, List<String>> getRows(WorksheetEntry worksheet) throws IOException, ServiceException {
        URL listFeedUrl = worksheet.getListFeedUrl();
        ListFeed listFeed = spreadsheetService.getFeed(listFeedUrl, ListFeed.class);

        // Iterate through each row, printing its cell values.
        Map<Integer, List<String>> ret = new HashMap<>();
        final Integer[] rowId = {0};
        listFeed
                .getEntries()
                .stream()
                .forEach(
                        r -> ret.put(
                                rowId[0]++,
                                r.getCustomElements()
                                        .getTags()
                                        .stream()
                                        .map(
                                                tag -> {
                                                    String val = r.getCustomElements().getValue(tag);
                                                    return val != null ? val : "";
                                                }
                                        ).collect(Collectors.toList())
                        )
                );
        return ret;
    }

    public SpreadsheetService getSpreadsheetService() {
        return spreadsheetService;
    }
}
