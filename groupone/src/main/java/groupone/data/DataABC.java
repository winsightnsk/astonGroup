package groupone.data;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

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

    @Override
    @NotNull
    public Iterator<String> iterator() {
        return this;
    }

}
