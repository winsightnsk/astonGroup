package groupone.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    static final String path = "resources/users.txt";

    @Test
    void allRead(){
        // в файле: 186 строк
        // результат: получает все 186 строк
        //BUILD SUCCESSFUL
        int count = (int)new FileReader(path).stream().count();
        assertEquals(186, count);
    }

    @Test
    void readCount500(){
        // в файле: 186 строк
        // результат: получает 186 строк
        //BUILD SUCCESSFUL
        int count = (int)new FileReader(500, path).stream().count();
        assertEquals(186, count);
    }

    @Test
    void readCount10(){
        // в файле: 186 строк
        // результат: получает 10 строк
        //BUILD SUCCESSFUL
        int count = (int)new FileReader(10, path).stream().count();
        assertEquals(10, count);
    }

    @Test
    void readCount186(){
        // в файле: 186 строк
        // результат: получает 186 строк
        //BUILD SUCCESSFUL
        int count = (int)new FileReader(186, path).stream().count();
        assertEquals(186, count);
    }

    @Test
    void testEqualsCount5(){
        // в файле: 186 строк
        // получить 5 строк
        //BUILD SUCCESSFUL
        DataInterface di = new FileReader(5, path);
        String[] lines = di.stream().toArray(String[]::new);
        assertEquals(5, lines.length);
    }

    @Test
    void testEqualsCountAll(){
        // в файле: 186 строк
        // получить все строки
        //BUILD SUCCESSFUL
        DataInterface di = new FileReader(path);
        String[] lines = di.stream().toArray(String[]::new);
        assertEquals(186, lines.length);
    }

    @Test
    void resultEquals(){
        // Сравнить с первой строкой: john_doe, 123456, john.doe@gmail.com
        //BUILD SUCCESSFUL
        DataInterface di = new FileReader(path);
        String line = di.next();
        assertEquals("john_doe, 123456, john.doe@gmail.com", line);
    }

    @Test
    void testPath(){
        new FileReader("users.txt").forEach(System.out::println);
    }
}