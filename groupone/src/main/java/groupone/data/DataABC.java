package groupone.data;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public abstract class DataABC implements DataInterface, Iterator<String> {

    protected int i = 0;
    protected int count = 0;

    @Override
    public boolean hasNext() {
        return i < count;
    }

    @Override
    public String next() {
        this.i += 1;
        throw new RuntimeException("Необходимо переопределить метод next()");
    }

    public Stream<String> stream() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        this,
                        Spliterator.IMMUTABLE | Spliterator.NONNULL
                ),
                false
        );
    }

    @Override
    public Spliterator<String> spliterator() {
        return Spliterators.spliterator(
                this,
                count,
                Spliterator.IMMUTABLE | Spliterator.NONNULL
        );
    }

    @Override
    @NotNull
    public Iterator<String> iterator() {
        return this;
    }

}
