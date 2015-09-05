package xyz.gwh.tor.service.jtorctl;

/**
 * Wraps exceptions thrown by jtorctl.
 */
public class JtorctlException extends Exception {
    public JtorctlException(String message) {
        super(message);
    }
}
