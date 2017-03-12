package moe.lyrebird.model.threading;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Application-wide background task execution manager
 */
@Slf4j
public class BackgroundService {
    
    private final ScheduledExecutorService executorService;
    
    public BackgroundService() {
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }
    
    public void run(@NonNull final Runnable runnable) {
        log.info("Submitted a task for execution.");
        this.executorService.submit(runnable);
    }
    
    public <V> Future<V> run(@NonNull final Callable<V> callable) {
        log.info("Submitted call for computation.");
        return this.executorService.submit(callable);
    }
    
    public void runlater(@NonNull final Runnable runnable, final long time, final TimeUnit timeUnit) {
        log.info("Submitted task for execution in {} {}", runnable, time, timeUnit.toString());
        this.executorService.schedule(runnable, time, timeUnit);
    }
    
    public <V> ScheduledFuture<V> runLater(@NonNull final Callable<V> callable, final long time, final TimeUnit timeUnit) {
        log.info("Submitted call for computation in {} {}", callable, time, timeUnit.toString());
        return this.executorService.schedule(callable, time, timeUnit);
    }
    
    public void cleanUp() {
        this.executorService.shutdown();
    }
}
