package dsa.dc;

import java.io.*;
import java.nio.file.*;

public class CsvWriter implements Closeable {
    private final BufferedWriter out;
    public CsvWriter(String path, String header) throws IOException {
        Path p = Path.of(path);
        if (p.getParent() != null) Files.createDirectories(p.getParent());
        out = Files.newBufferedWriter(p);
        out.write(header); out.newLine();
    }
    public void writeRow(String row) throws IOException { out.write(row); out.newLine(); out.flush(); }
    @Override public void close() throws IOException { out.close(); }
}