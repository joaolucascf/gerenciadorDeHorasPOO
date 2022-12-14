package poofinal.application;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;
public class DriveUpdate {
    private static final String APPLICATION_NAME = "hoursManager";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";
    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE);
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/credentials.json";
    //private static final java.io.File UPLOAD_FILE = new java.io.File(UPLOAD_FILE_PATH);
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = new FileInputStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public static void sendReport(String filePath) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        String driveFolder = idLoader();
        if(driveFolder == null) {
            driveFolder = createFolder(service);
        }
        insertFileinFolder(service, driveFolder, filePath);
    }

    public static String createFolder(Drive service) throws IOException {

        // File's metadata.
        File fileMetadata = new File();
        fileMetadata.setName("Relat??rios de horas complementares");
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
        try {
            File file = service.files().create(fileMetadata)
                    .setFields("id")
                    .execute();
            System.out.println("Folder ID: " + file.getId());
            idSaver(file.getId());
            return file.getId();
        } catch (GoogleJsonResponseException e) {
            System.err.println("Unable to create folder: " + e.getDetails());
            throw e;
        }
    }

    private static void idSaver(String folderID) throws IOException {
        java.io.File id = new java.io.File("src/main/resources/files/idDoc.txt");
        ObjectOutputStream fileID = new ObjectOutputStream(new FileOutputStream(id));
        fileID.writeUTF(folderID);
        fileID.close();
    }

    private static String idLoader() throws IOException {
        Path pathID = Path.of("src/main/resources/files/idDoc.txt");
        if(Files.exists(pathID)){
            java.io.File id = new java.io.File(pathID.toString());
            ObjectInputStream fileID = new ObjectInputStream(new FileInputStream(id));
            return fileID.readUTF();
        }
        return null;
    }
    public static void insertFileinFolder(Drive service, String realFolderId, String filePath) throws IOException {
        final java.io.File UPLOAD_FILE = new java.io.File(filePath);
        File fileMetaData = new File();
        fileMetaData.setName(UPLOAD_FILE.getName());
        FileContent mediaContent = new FileContent("application/pdf", UPLOAD_FILE);
        fileMetaData.setParents(Collections.singletonList(realFolderId));
        try {
            File file = service.files().create(fileMetaData, mediaContent)
                    .setFields("id, parents")
                    .execute();
            System.out.println("File ID: " + file.getId());
        } catch (IOException e) {
            System.err.println("Unable to upload file: " + e.getMessage());
            throw e;
        }
    }
}