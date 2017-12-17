package org.comlev.factograph.common;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 21.09.2017
 */
public class ScanResult {

    private List<ElemetResult> results = new ArrayList<>();

    public void print() {
        if (results != null && results.size() > 0) {
            results.forEach(ElemetResult::print);
        }
    }

    public void addResult(List<LineResult> matchedLines, String rootFile, String fileName) {
        results.add(new ElemetResult(matchedLines, rootFile, fileName));
    }

    public void toList(List<String> result) {
        if (results != null && results.size() > 0) {
            results.forEach(s-> s.toList(result));
        }
    }
}
