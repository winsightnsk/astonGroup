package groupone.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileReaderTest {

    static final String path = "resources/users.txt";

    @Test
    void allRead(){
        // в файле: 186 строк
        // результат: выводит все 186 строк
        //BUILD SUCCESSFUL
        new FileReader(path).forEach(System.out::println);
    }

    @Test
    void readCount500(){
        // в файле: 186 строк
        // результат: выводит 186 строк
        //BUILD SUCCESSFUL
        new FileReader(500, path).forEach(System.out::println);
    }

    @Test
    void readCount10(){
        // в файле: 186 строк
        // результат: выводит 10 строк
        //BUILD SUCCESSFUL
        new FileReader(10, path).forEach(System.out::println);
    }

    @Test
    void readCount186(){
        // в файле: 186 строк
        // результат: выводит 186 строк
        //BUILD SUCCESSFUL
        new FileReader(186, path).forEach(System.out::println);
    }

    @Test
    void testEqualsCount5(){
        // получить 5 строк
        //BUILD SUCCESSFUL
        DataInterface di = new FileReader(5, path);
        String[] lines = di.stream().toArray(String[]::new);
        assertEquals(5, lines.length);
    }

    @Test
    void testEqualsCountAll(){
        // получить все строки
        //BUILD SUCCESSFUL
        DataInterface di = new FileReader(path);
        String[] lines = di.stream().toArray(String[]::new);
        assertEquals(186, lines.length);
    }
}