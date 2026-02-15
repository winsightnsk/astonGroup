package groupone.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class FileReader extends DataABC {
    private String _nextLine;
    private String[] _lines;
    private int _index = 0;

    public FileReader(int count, String path) {
        if (count <= 0) throw new RuntimeException("FileReader: Ошибка входных параметров: " + count);
        this.count = count;

        try(BufferedReader bufferedReader = new BufferedReader(new java.io.FileReader(path))){
            List<String> lines = new ArrayList<>();
            String line;
            while((line = bufferedReader.readLine()) != null) {
                if(i++ >= count)
                    break;
                lines.add(line);
            }
            _lines = lines.toArray(new String[0]);
        }catch(IOException e){
            throw new RuntimeException("FileReader: Ошибка чтения файла: " + path, e);
        }

        readLine();
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
}