package org.comlev.factograph.common;

import org.apache.commons.io.FilenameUtils;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;

/**
 * .
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 11.11.2017
 */
public abstract class AbstractScanner {
    private List<AbstractScanner> scanners;

    protected boolean canProcess(String path) {
        String ext = FilenameUtils.getExtension(path);
        return getExt().equalsIgnoreCase(ext);
    }

    protected abstract String getExt();

    public abstract void scan(Path path, List<SearchCriteria> criteria, ScanResult result);

    public abstract void scan(InputStream stream, Path path, String fileName, List<SearchCriteria> criteria, ScanResult result);

    public List<AbstractScanner> getScanners() {
        return scanners;
    }

    public void setScanners(List<AbstractScanner> scanners) {
        this.scanners = scanners;
    }

    public AbstractScanner(List<AbstractScanner> scanners) {
        this.scanners = scanners;
    }

}
