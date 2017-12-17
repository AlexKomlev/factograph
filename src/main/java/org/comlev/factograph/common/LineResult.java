package org.comlev.factograph.common;

import java.util.List;

/**
 * Container for matched lines.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class LineResult {

    private int lineCount;

    private String line;

    public LineResult(int lineCount, String line) {
        this.lineCount = lineCount;
        this.line = line;
    }

    public int getLineCount() {
        return lineCount;
    }

    public String getLine() {
        return line;
    }

    public void print() {
        System.out.println(lineCount + " >> " + line);
    }

    public void toList(List<String> result) {
        result.add(lineCount + " >> " + line);
    }
}
