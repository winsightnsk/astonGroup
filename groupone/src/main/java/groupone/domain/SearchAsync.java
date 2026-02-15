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

public class SearchAsync implements SearchInterface {
    private static final Logger logger = LoggerFactory.getLogger(SearchAsync.class.getName());


    private final int threadCount;
    private final List<UserABC> userList;

    private int calculator(int n) {
        return Math.min(n, Runtime.getRuntime().availableProcessors());
    }

    public SearchAsync(List<UserABC> list) {
        logger.info(String.valueOf(Runtime.getRuntime().availableProcessors()));
        userList = list;
        threadCount = calculator(list.size());
    }

    @Override
    public int matchesCount(String text) {
        if (text == null || text.isEmpty()) return 0;
        int count = 0;

        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount)) {
            List<Callable<Integer>> tasks = new ArrayList<>();
            int part = userList.size() / threadCount;
            int a = 0;
            for (int i = 0; i < threadCount; i++) {
                int b = a + part;
                if (b > userList.size()) throw new RuntimeException("b > userList.size()");
                if (i == threadCount - 1) b = userList.size();
                tasks.add(new Finder(text, a, b, userList));
                a = b;
            }
            List<Future<Integer>> futures = executor.invokeAll(tasks);
            for (Future<Integer> future : futures) {
                try {
                    count += future.get();
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        } catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return  count;
    }

    class Finder implements Callable<Integer> {
        private int count = 0;
        private final List<UserABC> userList;
        private final int origin;
        private final int bound;
        private final String txt;

        public Finder(String txt, int origin, int bound, List<UserABC> userList) {
            this.txt = txt;
            this.origin = origin;
            this.bound = bound;
            this.userList = userList;
        }

        @Override
        public Integer call() throws Exception {
            for (int i=origin; i<bound; i++) if (userList.get(i).getUsername().toLowerCase().contains(txt.toLowerCase())) count++;
            return count;
        }
    }

}
