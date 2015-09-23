/* Copyright (c) 2009, Nathan Freitas, Orbot / The Guardian Project - http://openideals.com/guardian */
/* See LICENSE for licensing information */

package xyz.gwh.tor.installer;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import xyz.gwh.tor.exception.PermissionsNotSetException;
import xyz.gwh.tor.exception.ResourceNotInstalledException;
import xyz.gwh.tor.util.IOUtils;
import xyz.gwh.tor.util.ShellUtils;

/**
 * Manages the installation of Tor resources.
 */
public final class TorInstaller {

    public static final String FILENAME_TOR = "tor";
    private static final String CMD_DELETE_FILES_IN_DIR = "rm -rf -f %s/*";
    private static final String PERMISSION_EXECUTABLE = "770";

    private String pathInstallDir;
    private Context context;

    //TODO: pass a Resources object instead of Context
    public TorInstaller(Context context, String pathInstallDir) {
        this.pathInstallDir = pathInstallDir;
        this.context = context;
    }

    /**
     * Clears installation directory, installs binaries and sets them as executable.
     */
    public void cleanInstall() throws ResourceNotInstalledException, PermissionsNotSetException {
        uninstall();
        File file = new File(pathInstallDir, FILENAME_TOR);
//        installRawResource(file, R.raw.tor);
        setBinaryAsExecutable(file);
    }

    /**
     * Clears installation directory.
     */
    private void uninstall() throws ResourceNotInstalledException {
        try {
            deleteFilesInPath(pathInstallDir);
        } catch (Exception e) {
            throw new ResourceNotInstalledException("Unable to delete files in the installation directory: " + e.getMessage());
        }
    }

    /**
     * Deletes all files in directory at given path.
     */
    private void deleteFilesInPath(String path) throws Exception {
        String cmd = String.format(CMD_DELETE_FILES_IN_DIR, path);
        ShellUtils.runCommand(cmd);
    }

    /**
     * Installs resource for given id.
     */
    private void installRawResource(File file, int resId) throws ResourceNotInstalledException {
        InputStream is = context.getResources().openRawResource(resId);

        try {
            IOUtils.writeToFile(is, file, false, true);
        } catch (IOException e) {
            throw new ResourceNotInstalledException("Unable to install " + file.getAbsolutePath());
        }
    }

    /**
     * Attempts to change permissions on file to executable.
     */
    private void setBinaryAsExecutable(File file) throws PermissionsNotSetException {
        try {
//            Shell shell = Shell.startShell();
//            Toolbox toolbox = new Toolbox(shell);
//            toolbox.setFilePermissions(file.getAbsolutePath(), PERMISSION_EXECUTABLE);
//            shell.close();
        } catch (Exception e) {
            throw new PermissionsNotSetException("Unable to set executable permissions on " + file.getAbsolutePath());
        }
    }
}