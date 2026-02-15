package groupone.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class FileReader extends DataABC {
    private final BufferedReader _bufferedReader;
    private String _nextLine;

    public FileReader(int count, String path) {
        if (count <= 0) throw new RuntimeException("FileReader: Ошибка входных параметров: " + count);
        this.count = count;

        try {
            _bufferedReader = new BufferedReader(new java.io.FileReader(path));
            readLine();
        } catch (IOException e) {
            throw new RuntimeException("FileReader: Файл не найден: " + path, e);
        }
    }

    private void readLine() {
        try {
            if(i++ >= count){
                _nextLine = null;
                _bufferedReader.close();
                return;
            }
            _nextLine = _bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("FileReader: Ошибка чтения файла");
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
}