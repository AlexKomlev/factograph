package org.comlev.factograph.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Helper for files' searching.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class FileSearchHelper {

    public static List<Path> scanFiles(Path root, String ext) throws IOException {
        List<Path> result = new ArrayList<>();
        if (Files.exists(root)) {
            Stream<Path> ps = Files.find(root, Integer.MAX_VALUE, (filepath, fileAttr) -> fileAttr.isRegularFile() && endsWith(filepath, ext));
            ps.forEach(result::add);
        }
        return result;
    }

    private static boolean endsWith(Path path, String ext) {
        if(ext == null || ext.contains("*")){
            return true;
        }
        String[] exts = ext.split(",");
        for (String e : exts) {
            if (path.toString().endsWith("." + e.trim())) {
                return true;
            }
        }
        return false;
    }
}
