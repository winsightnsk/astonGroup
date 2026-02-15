package groupone.data;

import org.junit.jupiter.api.Test;

class FileReaderTest {

    static final String path = "resources/users.txt";

    @Test
    void allRead(){
        new FileReader(5, path).forEach(System.out::println);
    }
}