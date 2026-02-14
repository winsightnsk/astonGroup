package groupone.data;

import java.util.Spliterator;
import java.util.stream.Stream;

public interface DataInterface extends Iterable<String> {

    boolean hasNext();
    String next();
    Stream<String> stream();
    Spliterator<String> spliterator();

}
