package org.comlev.factograph.io;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.tika.parser.txt.CharsetDetector;
import org.comlev.factograph.common.*;

import java.io.*;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;


/**
 * .
 *
 * @author <a href="mailto:AlexKomlev@gmail.com">Aleksey Komlev</a>
 * @version 21.09.2017
 */
public class ZipFileScanner extends AbstractScanner {

    @Override
    protected boolean canProcess(String path) {
        return super.canProcess(path);
    }

    @Override
    protected String getExt() {
        return "ZIP";
    }

    @Override
    public void scan(Path path, List<SearchCriteria> criteria, ScanResult result) {
        if (!canProcess(path.toString())){
            return;
        }

        try {
            ZipFile zipFile = new ZipFile(path.toFile());
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            while(entries.hasMoreElements()){
                ZipArchiveEntry entry = entries.nextElement();
                String fileName = CharsetHelper.byteToStr(entry.getRawName());
                if(!entry.isDirectory()) {
                    InputStream stream = zipFile.getInputStream(entry);
                    getScanners().forEach(s->s.scan(stream, path, fileName, criteria, result));
                }
            }
            zipFile.close();
        } catch (IOException e) {
            System.err.println("Can't read file " + path.toString());
        }
    }

    @Override
    public void scan(InputStream stream, Path path, String fileName, List<SearchCriteria> criteria, ScanResult result) {
        //do nothing
    }


    public ZipFileScanner(List<AbstractScanner> scanners) {
        super(scanners);
    }

    public ScanResult scan(Path path, SearchCriteria criteria) throws FileNotFoundException, ArchiveException {
        ScanResult result = new ScanResult();
        try {
            ZipFile zipFile = new ZipFile(path.toFile());
            Enumeration<ZipArchiveEntry> entries = zipFile.getEntries();
            while(entries.hasMoreElements()){
                ZipArchiveEntry entry = entries.nextElement();
/*
                CharsetDetector charDetect = new CharsetDetector();
                String fName = entry.getName();
                charDetect.setText(fName.getBytes());
                String charSet = charDetect.detect().getName();
                System.out.println(charSet);
                System.out.println(URLEncoder.encode(fName, "windows-1251"));
*/
                if(!entry.isDirectory()) {
                    InputStream stream = zipFile.getInputStream(entry);
                    Stream<String> lines;
//                    InputStreamReader i = new InputStreamReader(stream);
//                    System.out.println(i.getEncoding());
                    lines = new BufferedReader(new InputStreamReader(stream, Charset.forName("windows-1251"))).lines();
                    try {
//                        lines.forEach(s -> {
//                            checkLine(result.getLines(), s, criteria);});
                    } catch (Exception ignored) {}
                }
            }
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        ArchiveInputStream input = new ArchiveStreamFactory().createArchiveInputStream(new FileInputStream(fileName));
//        input.
        return result;
    }



    public void unzip(String file) throws FileNotFoundException, IOException, ArchiveException {

        File inputFile = new File(file);

        InputStream is = new FileInputStream(inputFile);
        ArchiveInputStream ais = new ArchiveStreamFactory().createArchiveInputStream("zip", is);
        ZipEntry entry = null;

        while ((entry = (ZipArchiveEntry) ais.getNextEntry()) != null) {

            if (entry.getName().endsWith("/")) {
                File dir = new File("" + File.separator + entry.getName());
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                continue;
            }

            File outFile = new File("" + File.separator + entry.getName());

            if (outFile.isDirectory()) {
                continue;
            }

            if (outFile.exists()) {
                continue;
            }

            FileOutputStream out = new FileOutputStream(outFile);
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = ais.read(buffer)) > 0) {
                out.write(buffer, 0, length);
                out.flush();
            }

        }
    }
}
