package xyz.gwh.tor.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipInputStream;

/**
 * Utility class for file manipulation.
 */
public final class IOUtils {

    private static final int FILE_WRITE_BUFFER_SIZE = 1024;

    private IOUtils() {
        // restrict instantiation
    }

    /**
     * Writes the InputStream to the outFile.
     */
    public static void writeToFile(InputStream stm, File outFile, boolean append, boolean zip) throws IOException {
        int bytecount;
        byte[] buffer = new byte[FILE_WRITE_BUFFER_SIZE];

        OutputStream stmOut = new FileOutputStream(outFile.getAbsolutePath(), append);
        ZipInputStream zis = null;

        if (zip) {
            zis = new ZipInputStream(stm);
            zis.getNextEntry();
            stm = zis;

        }

        while ((bytecount = stm.read(buffer)) > 0) {
            stmOut.write(buffer, 0, bytecount);
        }

        stmOut.close();
        stm.close();

        if (zis != null) {
            zis.close();
        }
    }
}