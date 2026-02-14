package groupone.data;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileReader extends DataABC implements AutoCloseable {
    private final BufferedReader _bufferedReader;
    private String _nextLine;


    public FileReader(int count, String path) throws IOException {
        if (count <= 0) throw new RuntimeException("Ошибка входных параметров");
        this.count = count;

        this._bufferedReader = new BufferedReader(new java.io.FileReader(path));
        readLine();
    }

    private void readLine(){
        try {
            _nextLine = _bufferedReader.readLine();
            Pattern pattern = Pattern.compile("([^\\s,;.]+)[\\s,;.]+([^\\s,;]+)[\\s,;]+([\\w.-]+@[\\w.-]+\\.[a-z]{2,})");
            if(hasNext()){
                Matcher matcher = pattern.matcher(_nextLine);
                if(matcher.find())
                    _nextLine = String.join(", ", matcher.group(1), matcher.group(2), matcher.group(3));
                else
                    throw new RuntimeException("Regex error");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return _nextLine != null;
    }

    @Override
    public String next() {
        String line = _nextLine;
        readLine();
        return line;
    }

    @Override
    @NotNull
    public Iterator<String> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return FileReader.this.hasNext();
            }

            @Override
            public String next() {
                return FileReader.this.next();
            }
        };
    }

    @Override
    public void close() throws IOException {
        _bufferedReader.close();
    }
}
