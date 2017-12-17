package org.comlev.factograph.io;

import org.comlev.factograph.common.*;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

/**
 * Implementation of {@link AbstractScanner} for FB2.
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 17.12.2017
 */
public class Fb2FileScanner extends AbstractScanner {

    private Path currentPath;

    public Fb2FileScanner(List<AbstractScanner> scanners) {
        super(scanners);
    }

    @Override
    protected String getExt() {
        return "FB2";
    }

    @Override
    public void scan(Path path, List<SearchCriteria> criteria, ScanResult result) {
        if (!canProcess(path.toString())) {
            return;
        }
        //todo Process
    }

    @Override
    public void scan(InputStream stream, Path path, String fileName, List<SearchCriteria> criteria, ScanResult result) {
        Stream<String> lines;
        List<LineResult> matchedLines = new ArrayList<>();
        Map<String, InputStream> aa = copyIS(stream, path.toString());
        if(aa == null) {
            return;
        }

        BufferedReader utf = new BufferedReader(new InputStreamReader(aa.get("1")));
        BufferedReader cp1251 = new BufferedReader(new InputStreamReader(aa.get("2"), Charset.forName("windows-1251")));

        lines = utf.lines(); //, Charset.forName("windows-1251")
        Optional<String> rs = lines.filter(s -> s.contains("utf-8") || s.contains("UTF-8")).findAny();
        if (!rs.isPresent()) {

            lines = cp1251.lines(); //
            rs = lines.filter(s -> s.contains("windows-1251") || s.contains("Windows-1251")).findAny();
            if (!rs.isPresent()) {
                System.err.println("Can't read file " + path.toString());
                return;
            } else {
                lines = cp1251.lines();
            }
        } else {
            lines = utf.lines();
        }
        final int[] lnCnt = {0};
        try {
            lines.forEach(s -> {
                checkLine(matchedLines, s, null, lnCnt[0]++);
            });
            if (matchedLines.size() > 0) {
                result.addResult(matchedLines, path.toString(), fileName);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }

        try {
            aa.get("1").close();
            aa.get("2").close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ScanResult scan(Path path, SearchCriteria criteria) {
        ScanResult result = new ScanResult();
/*
        result.setPath(path);

        Stream<String> lines;
        try {
            lines = Files.lines(path);
            try {
                lines.forEach(s -> {checkLine(result.getLines(), s, criteria, 0);});
            } catch (Exception ignored) {}
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        return result;
    }

    private void checkLine(List<LineResult> result, String line, SearchCriteria criteria, int cnt) {
//        if (text.contains(" жены ") && text.contains(" мои ")) {
//        if (text.contains(" мой гарем ")) {
//        if (line.contains(" жена ") && line.contains(" Дроу ")) {
        if (line.contains(" жена ")) {
            if (line.length() <= 3000) {
                result.add(new LineResult(cnt, line));
            }
        }
    }

    private Map<String, InputStream> copyIS(InputStream source, String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = source.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
                baos.flush();
            }
        } catch (IOException e) {
            System.err.println("Can't copy " + fileName);
            return null;
        }
        InputStream is1 = new ByteArrayInputStream(baos.toByteArray());
        InputStream is2 = new ByteArrayInputStream(baos.toByteArray());
        Map<String, InputStream> result = new HashMap<>();
        result.put("1", is1);
        result.put("2", is2);
        return result;
    }
}
