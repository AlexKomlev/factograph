package org.comlev.factograph.engine;

import org.apache.commons.io.FileUtils;
import org.comlev.factograph.common.AbstractScanner;
import org.comlev.factograph.common.ScanResult;
import org.comlev.factograph.common.SearchCriteria;
import org.comlev.factograph.io.Fb2FileScanner;
import org.comlev.factograph.io.FileSearchHelper;
import org.comlev.factograph.io.ZipFileScanner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Factograph main class.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class FactoEngine {

    private ArrayList<String> paths;
    private List<AbstractScanner> scanners = new ArrayList<>();

    public List<SearchCriteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<SearchCriteria> criteria) {
        this.criteria = criteria;
    }

    private List<SearchCriteria> criteria;


    public static void main(String[] args) throws IOException {
        FactoEngine engine = new FactoEngine();
        ArrayList<String> paths = new ArrayList<>();
//        paths.add("F:\\Development\\projects\\factograph\\data");
        paths.add("F:\\Books\\Fiction\\!TMP\\скачано_17.07.08");
        paths.add("F:\\Books\\Fiction\\!TMP\\скачано_17.07.17");
        paths.add("F:\\Books\\Fiction\\!TMP\\скачано_17.07.21");
        paths.add("F:\\Books\\Fiction\\!TMP\\скачано_17.09.09");
        engine.setPaths(paths);
        ScanResult result =  engine.process();
        File rpath = new File("F:\\Development\\projects\\factograph\\result.txt");

        List<String> rlines = new ArrayList<>();

        result.toList(rlines);

        FileUtils.writeLines(rpath, rlines);
    }

    private ScanResult process() {
        List<Path> files = new ArrayList<>();
        paths.forEach(s -> {
            try {
                files.addAll(FileSearchHelper.scanFiles(Paths.get(s), "*"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ScanResult result = new ScanResult();

        files.forEach(f -> {
            scanners.forEach(s -> {
                s.scan(f, null, result);
            });
        });
        return result;
    }

    public FactoEngine() {
        scanners.add(new Fb2FileScanner(scanners));
        scanners.add(new ZipFileScanner(scanners));
    }

    public void setPaths(ArrayList<String> paths) {
        this.paths = paths;
    }

    public ArrayList<String> getPaths() {
        return paths;
    }
}
