package groupone.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileReader extends DataABC {
    private final BufferedReader _bufferedReader;
    private String _nextLine;
    private String[] _lines;
    private int _index = 0;

    public FileReader(int count, String path) {
        if (count <= 0) throw new RuntimeException("Ошибка входных параметров");
        this.count = count;

        try {
            this._bufferedReader = new BufferedReader(new java.io.FileReader(path));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        readLines();
        readLine();
    }

    private void readLines(){
        try{
            List<String> lines = new ArrayList<>();
            String line;
            while((line = _bufferedReader.readLine()) != null){
                lines.add(line);
            }
            _lines = lines.toArray(new String[0]);
            _bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void readLine(){
        if(_index < _lines.length)
            _nextLine = _lines[_index++];
        else
            _nextLine = null;
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

    public void reset(){
        this._index = 0;
        readLine();
    }
}
