package xyz.gwh.tor.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a torrc configuration file.
 */
public final class Torrc implements Parcelable {

    private static final String KEY_RUN_AS_DAEMON = "RunAsDaemon";
    private static final String KEY_AVOID_DISK_WRITES = "AvoidDiskWrites";
    private static final String KEY_SOCKS_PORT = "SOCKSPort";
    private static final String KEY_SAFE_SOCKS = "SafeSocks";
    private static final String KEY_TEST_SOCKS = "TestSocks";
    private static final String KEY_WARN_UNSAFE_SOCKS = "WarnUnsafeSocks";
    private static final String KEY_TRANS_PORT = "TransPort";
    private static final String KEY_DNS_PORT = "DNSPort";
    private static final String KEY_VIRTUAL_ADDR_NETWORK = "VirtualAddrNetwork";
    private static final String KEY_AUTOMAP_HOSTS_ON_RESOLVE = "AutomapHostsOnResolve";
    private static final String KEY_CIRCUIT_STREAM_TIMEOUT = "CircuitStreamTimeout";
    private static final String KEY_CONTROL_PORT_WRITE_TO_FILE = "ControlPortWriteToFile";
    private static final String KEY_TRANS_LISTEN_ADDRESS = "TransListenAddress";
    private static final String KEY_DNS_LISTEN_ADDRESS = "DNSListenAddress";

    private List<String> entries = new ArrayList<String>();

    private Torrc(Builder builder) {
        entries = builder.entries;
    }

    /**
     * Default Torrc configuration.
     */
    public static final Builder DEFAULT_BUILDER = new Builder()
            .setRunAsDaemon("1")
            .setAvoidDiskWrites("1")
            .setSocksPorts("auto")
            .setSafeSocks("0")
            .setTestSocks("0")
            .setWarnUnsafeSocks("1")
            .setTransPort("auto")
            .setDnsPort("auto")
            .setVirtualAddrNetwork("10.192.0.0/10")
            .setAutomapHostsOnResolve("1")
            .setCircuitStreamTimeout("60");

    @NonNull
    public List<String> asList() {
        return entries;
    }

    @Override
    public String toString() {
        String torConfig = "";
        for (String entry : entries) {
            torConfig += entry;
        }
        return torConfig;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(this.entries);
    }

    protected Torrc(Parcel in) {
        this.entries = in.createStringArrayList();
    }

    public static final Creator<Torrc> CREATOR = new Creator<Torrc>() {
        public Torrc createFromParcel(Parcel source) {
            return new Torrc(source);
        }

        public Torrc[] newArray(int size) {
            return new Torrc[size];
        }
    };

    /**
     * Builder class for creating a Torrc object.
     */
    public static class Builder {

        private List<String> entries = new ArrayList<>();

        public Builder() {
            // default empty Builder
        }

        public Builder setRunAsDaemon(String runAsDaemon) {
            addEntry(KEY_RUN_AS_DAEMON, runAsDaemon);
            return this;
        }

        public Builder setAvoidDiskWrites(String avoidDiskWrites) {
            addEntry(KEY_AVOID_DISK_WRITES, avoidDiskWrites);
            return this;
        }

        public Builder setSocksPorts(String... socksPorts) {
            for (String socksPort : socksPorts) {
                addEntry(KEY_SOCKS_PORT, socksPort);
            }
            return this;
        }

        public Builder setSafeSocks(String safeSocks) {
            addEntry(KEY_SAFE_SOCKS, safeSocks);
            return this;
        }

        public Builder setTestSocks(String testSocks) {
            addEntry(KEY_TEST_SOCKS, testSocks);
            return this;
        }

        public Builder setWarnUnsafeSocks(String warnUnsafeSocks) {
            addEntry(KEY_WARN_UNSAFE_SOCKS, warnUnsafeSocks);
            return this;
        }

        public Builder setTransPort(String transPort) {
            addEntry(KEY_TRANS_PORT, transPort);
            return this;
        }

        public Builder setDnsPort(String dnsPort) {
            addEntry(KEY_DNS_PORT, dnsPort);
            return this;
        }

        public Builder setVirtualAddrNetwork(String virtualAddrNetwork) {
            addEntry(KEY_VIRTUAL_ADDR_NETWORK, virtualAddrNetwork);
            return this;
        }

        public Builder setAutomapHostsOnResolve(String automapHostsOnResolve) {
            addEntry(KEY_AUTOMAP_HOSTS_ON_RESOLVE, automapHostsOnResolve);
            return this;
        }

        public Builder setCircuitStreamTimeout(String circuitStreamTimeout) {
            addEntry(KEY_CIRCUIT_STREAM_TIMEOUT, circuitStreamTimeout);
            return this;
        }

        public Builder setControlPortWriteToFile(String controlPortWriteToFile) {
            addEntry(KEY_CONTROL_PORT_WRITE_TO_FILE, controlPortWriteToFile);
            return this;
        }

        public Builder setTransListenAddress(String transListenAddress) {
            addEntry(KEY_TRANS_LISTEN_ADDRESS, transListenAddress);
            return this;
        }

        public Builder setDnsListenAddress(String dnsListenAddress) {
            addEntry(KEY_DNS_LISTEN_ADDRESS, dnsListenAddress);
            return this;
        }

        public Torrc build() {
            return new Torrc(this);
        }

        private void addEntry(String key, String value) {
            entries.add(key + ' ' + value + '\n');
        }
    }
}