package org.comlev.factograph.common;

import java.util.List;

/**
 * Container for results from file.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 18.11.2017
 */
public class ElementResult {
    private final String rootFile;
    private final String fileName;
    private List<LineResult> matchedLines;

    public ElementResult(List<LineResult> matchedLines, String rootFile, String fileName) {
        this.matchedLines = matchedLines;
        this.rootFile = rootFile;
        this.fileName = fileName;
    }

    public void print() {
        if (rootFile.equalsIgnoreCase(fileName)) {
            System.out.println(fileName);
        } else {
            System.out.println(rootFile + "->" + fileName);
        }
        matchedLines.forEach(LineResult::print);
    }

    public void toList(List<String> result) {
        if (rootFile.equalsIgnoreCase(fileName)) {
            result.add(fileName);
        } else {
            result.add(rootFile + "->" + fileName);
        }
        matchedLines.forEach(s -> s.toList(result));
    }
}
