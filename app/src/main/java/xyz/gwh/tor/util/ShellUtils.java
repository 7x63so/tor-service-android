package xyz.gwh.tor.util;

/**
 * Utility class for Android processes.
 */
public final class ShellUtils {

    private ShellUtils() {
        // restrict instantiation
    }

    /**
     * Runs a command in a new shell.
     */
    public static CommandResult runCommand(String commandStr) throws Exception {
//        Shell shell = Shell.startShell();
//        SimpleCommand command = new SimpleCommand(commandStr);
//
//        shell.add(command).waitForFinish();
//        shell.close();
//
//        return new CommandResult(command.getExitCode(), command.getOutput());
        return new CommandResult(-1, "");
    }
}