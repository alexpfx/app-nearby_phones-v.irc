package br.com.alexpfx.irctest.app;

import android.content.Context;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by alexandre on 07/07/15.
 */
public class AppIdentity {
    private static final String UID_FILE = "APPUIDX";

    public String getUid(Context context) {
        File f = new File(context.getFilesDir(), UID_FILE);
        String sid = null;
        try {
            if (!f.exists()) {
                UUID uuid = UUID.randomUUID();
                writeToFile(uuid, f);
            }
            sid = readFromFile(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sid;

    }

    private String readFromFile(File f) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(f, "r");
        byte[] bytes = new byte[(int) f.length()];
        randomAccessFile.readFully(bytes);
        randomAccessFile.close();
        return StringUtils.toEncodedString(bytes, Charset.defaultCharset());
    }

    private void writeToFile(UUID uuid, File f) throws IOException {
        FileOutputStream out = new FileOutputStream(f);
        String suuid = uuid.toString();
        out.write(suuid.getBytes(Charset.defaultCharset().name()));

        out.close();
    }

}
