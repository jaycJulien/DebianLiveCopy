package ch.fhnw.dlcopy;

import ch.fhnw.dlcopy.gui.DLCopyGUI;
import ch.fhnw.filecopier.FileCopier;
import ch.fhnw.util.StorageDevice;
import java.util.List;
import javax.swing.SwingWorker;

/**
 * An abstract base class for Installer and Upgrader
 *
 * @author Ronny Standtke <ronny.standtke@gmx.net>
 */
public abstract class InstallerOrUpgrader
        extends SwingWorker<Void, Void> {

    /**
     * the system source
     */
    protected final SystemSource source;

    /**
     * the list of storage devices to handle
     */
    protected final List<StorageDevice> deviceList;

    /**
     * the label of the exchange partition
     */
    protected String exchangePartitionLabel;

    /**
     * the graphical user interface
     */
    protected final DLCopyGUI dlCopyGUI;

    /**
     * the FileCopier to use for copying files
     */
    protected final FileCopier fileCopier = new FileCopier();

    /**
     * the LogindInhibit for preventing to switch into power saving mode
     */
    protected LogindInhibit inhibit;

    /**
     * the size of the device list
     */
    protected int deviceListSize;

    private final String exhangePartitionFileSystem;
    private final String dataPartitionFileSystem;
    
    private final String selectedMethod;
    private final String personalPassword;
    private final String masterPassword;
    private final String initialPassword;

    /**
     * creates a new InstallerOrUpgrader
     *
     * @param source the system source
     * @param deviceList the list of storage devices to handle
     * @param exchangePartitionLabel the label of the exchange partition
     * @param exhangePartitionFileSystem the file system of the exchange
     * partition
     * @param dataPartitionFileSystem the file system of the data partition
     * @param dlCopyGUI the graphical user interface
     * @param selectedMethod
     * @param personalPassword
     * @param masterPassword
     * @param initialPassword
     */
    public InstallerOrUpgrader(SystemSource source,
            List<StorageDevice> deviceList, String exchangePartitionLabel,
            String exhangePartitionFileSystem, String dataPartitionFileSystem,
            DLCopyGUI dlCopyGUI, String selectedMethod, String personalPassword,
            String masterPassword, String initialPassword) {
        this.source = source;
        this.deviceList = deviceList;
        this.exchangePartitionLabel = exchangePartitionLabel;
        this.exhangePartitionFileSystem = exhangePartitionFileSystem;
        this.dataPartitionFileSystem = dataPartitionFileSystem;
        this.dlCopyGUI = dlCopyGUI;
        deviceListSize = deviceList.size();
        this.selectedMethod = selectedMethod;
        this.personalPassword = personalPassword;
        this.masterPassword = masterPassword;
        this.initialPassword = initialPassword;
        
    }

    /**
     * returns the partition sizes on a storage device
     *
     * @param storageDevice the StorageDevice to check
     * @return the partition sizes on a storage device
     */
    public abstract PartitionSizes getPartitionSizes(
            StorageDevice storageDevice);

    /**
     * shows that file systems are being created
     */
    public abstract void showCreatingFileSystems();

    /**
     * shows that files are being copied
     *
     * @param fileCopier the fileCopier used to copy files
     */
    public abstract void showCopyingFiles(FileCopier fileCopier);

    /**
     * shows that file systems are being unmounted
     */
    public abstract void showUnmounting();

    /**
     * shows that the boot sector is written
     */
    public abstract void showWritingBootSector();

    /**
     * returns the selected file system of the exchange partition
     *
     * @return the selected file system of the exchange partition
     */
    public String getExhangePartitionFileSystem() {
        return exhangePartitionFileSystem;
    }

    /**
     * returns the selected file system of the data partition
     *
     * @return the selected file system of the data partition
     */
    public String getDataPartitionFileSystem() {
        return dataPartitionFileSystem;
    }

    /**
     * returns the size of the source system
     *
     * @return the size of the source system
     */
    public long getSourceSystemSize() {
        return source.getSystemSize();
    }
    
        // Getter and Setter methods
      public String getInitialPassword() {
        return initialPassword;
    }

    public String getSelectedMethod() {
        return selectedMethod;
    }

    public String getPersonalPassword() {
        return personalPassword;
    }

    public String getMasterPassword() {
        return masterPassword;
    }
}
