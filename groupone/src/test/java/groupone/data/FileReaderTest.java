package groupone.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

    @Test
    void Test(){
        try(FileReader reader = new FileReader(1, "resources/users.txt")){
            for(String line : reader)
                System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}