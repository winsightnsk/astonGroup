package groupone.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    static final String path = "resources/users.txt";

    @Test
    void allRead(){
        new FileReader(1, path).forEach(System.out::println);
    }

    @Test
    void asserDate(){
        var data = new FileReader(1, path);
        assertEquals("user1   , passw%ord1     user1@gmail.com", data.next());
        assertEquals("user2   , pass$word2    user2@gmail.com", data.next());
        assertEquals("user3 , gh34@3fd  user3@gmail.com", data.next());
        assertEquals("user4; 1234431, user4@gmail.com", data.next());
        assertEquals("user5. 43233, user5@gmail.com", data.next());

        data.reset();
        System.out.println(data.next());
    }
}