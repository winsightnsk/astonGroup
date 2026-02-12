package groupone.data;

import java.util.Iterator;

public abstract class DataABC implements DataInterface, Iterator<String> {

    protected int i = 0;
    protected int count = 0;

//    @Override
//    public boolean hasNext() {
//        return i < count;
//    }

}
