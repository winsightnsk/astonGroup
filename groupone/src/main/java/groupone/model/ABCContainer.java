package groupone.model;

import java.util.ArrayList;
import java.util.List;

public abstract class ABCContainer<T1,T2,T3,TC extends ABC<T1,T2,T3>> {

    protected List<TC> list = new ArrayList<>();

    public List<TC> getList() { return list; }
    public void add(TC instance) { list.add(instance); };

}
