package groupone.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReader extends DataABC {

    private static final Logger logger = LoggerFactory.getLogger(FileReader.class.getName());
    private BufferedReader _bufferedReader = null;
    private String _nextLine;
    private boolean finish = false;

    public FileReader(String path){
        this(-1, path);
    }

    public FileReader(int count, String path) {
        if (count <= 0 && count != -1)
            throw new RuntimeException("FileReader: Ошибка входных параметров: " + count);
        this.count = count;

        try {
            _bufferedReader = new BufferedReader(new java.io.FileReader(path));
            readLine();
        } catch (IOException ex) {
            System.out.println("Файл не найден.");
            _nextLine = null;
            finish = true;
            logger.error("Текущий рабочий каталог: {}", System.getProperty("user.dir"));
            logger.error("Ошибка доступа к {}: {}", path, ex.getMessage());
        }
    }


    private void readLine() {
        if(finish)
            return;

        try {
            if(count != -1)
                if(i++ >= count){
                    _nextLine = null;
                    _bufferedReader.close();
                    finish = true;
                    return;
                }
            _nextLine = _bufferedReader.readLine();
            if(_nextLine == null){
                _bufferedReader.close();
                finish = true;
            }
        } catch (IOException e) {
            throw new RuntimeException("FileReader: Ошибка чтения файла", e);
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
