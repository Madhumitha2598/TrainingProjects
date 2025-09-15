package day6;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
// Utility class for storing request ID per thread
class RequestContext {
    private static final ThreadLocal<String> requestIdHolder = new ThreadLocal<>();
 
    public static void setRequestId(String requestId) {
        requestIdHolder.set(requestId);
    }
 
    public static String getRequestId() {
        return requestIdHolder.get();
    }
 
    public static void clear() {
        requestIdHolder.remove();
    }
}
 
// Simulates request processing
class RequestProcessor implements Runnable {
    private final String requestId;
 
    public RequestProcessor() {
        // Generate unique Request ID
        this.requestId = UUID.randomUUID().toString();
    }
 
    @Override
    public void run() {
        try {
            // Store request ID in ThreadLocal
            RequestContext.setRequestId(requestId);
 
            log("Starting request");
            simulateWork();
            log("Processing request");
            simulateWork();
            log("Finished request");
 
        } finally {
            // Important to clear to avoid memory leaks
            RequestContext.clear();
        }
    }
 
    private void log(String message) {
        System.out.printf("[%s] %s: %s%n",
                Thread.currentThread().getName(),
                message,
                RequestContext.getRequestId());
    }
 
    private void simulateWork() {
        try {
            Thread.sleep(200); // Simulate work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
 
public class ThreadLocalRequestTrackingDemo {
    public static void main(String[] args) {
        // Thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);
 
        // Submit 10 requests
        for (int i = 0; i < 10; i++) {
            executor.submit(new RequestProcessor());
        }
 
        executor.shutdown();
    }
}