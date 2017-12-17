package org.comlev.factograph.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Container for scanned results.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class ScanResult {

    private List<ElementResult> results = new ArrayList<>();

    public void print() {
        if (results != null && results.size() > 0) {
            results.forEach(ElementResult::print);
        }
    }

    public void addResult(List<LineResult> matchedLines, String rootFile, String fileName) {
        results.add(new ElementResult(matchedLines, rootFile, fileName));
    }

    public void toList(List<String> result) {
        if (results != null && results.size() > 0) {
            results.forEach(s -> s.toList(result));
        }
    }
}
