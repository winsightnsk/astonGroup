package groupone.domain;

import groupone.model.UserABC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SearchAsync implements SearchInterface {

    private static final Logger logger = LoggerFactory.getLogger(SearchAsync.class.getName());
    private static final Lock lock = new ReentrantLock();

    private final int threadCount;
    private final List<UserABC> userList;

    private final List<UserABC> found = new ArrayList<>();

    @Override
    public List<UserABC> getFound() { return found; }

    private int calculator(int n) {
        return Math.min(n, Runtime.getRuntime().availableProcessors());
    }

    public SearchAsync(List<UserABC> list) {
        userList = list;
        threadCount = calculator(list.size());
    }

    @Override
    public int matches(String text) {
        if (text == null || text.isEmpty() || userList.isEmpty()) return 0;

        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            int part = userList.size() / threadCount;
            int a = 0;
            for (int i = 0; i < threadCount; i++) {
                int b = a + part;
                if (b > userList.size()) throw new RuntimeException("b > userList.size()");
                if (i == threadCount - 1) b = userList.size();
                executor.execute(new Finder(text, a, b, userList, found));
                a = b;
            }
            executor.shutdown();
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return found.size();
    }

    static class Finder implements Runnable {
        private final List<UserABC> found;
        private final List<UserABC> userList;
        private final int origin;
        private final int bound;
        private final String txt;

        public Finder(String txt, int origin, int bound, List<UserABC> userList, List<UserABC> found) {
            this.txt = txt;
            this.origin = origin;
            this.bound = bound;
            this.userList = userList;
            this.found = found;
        }

        @Override
        public void run() {
            for (int i = origin; i < bound; i++)
                if (userList.get(i).getUsername().toLowerCase().contains(txt.toLowerCase())) {
                    lock.lock();
                    found.add(userList.get(i));
                    lock.unlock();
                }
        }
    }
}
