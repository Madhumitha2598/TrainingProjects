package day4;
import java.util.concurrent.ConcurrentHashMap;
 
class PreferenceCache {
    private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();
 
    // Get preference (from cache or DB if not found)
    public String getPreference(String customerId) {
        return cache.computeIfAbsent(customerId, this::fetchFromDB);
    }
 
    // Update preference
    public void updatePreference(String customerId, String preference) {
        cache.put(customerId, preference);
        System.out.println("Update: Updated preference for " + customerId);
    }
 
    // Remove preference
    public void removePreference(String customerId) {
        cache.remove(customerId);
        System.out.println("Remove: Removed preference for " + customerId);
    }
 
    // Simulated DB fetch (delay 200ms)
    private String fetchFromDB(String customerId) {
        try {
            Thread.sleep(200); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "{\"theme\":\"dark\",\"lang\":\"en\",\"customer\":\"" + customerId + "\"}";
    }
}
 
public class CacheDemo {
    public static void main(String[] args) {
        PreferenceCache cache = new PreferenceCache();
 
        // First fetch (DB call)
        System.out.println("First fetch: " + cache.getPreference("CUST1"));
 
        // Second fetch (from cache)
        System.out.println("Second fetch (cached): " + cache.getPreference("CUST1"));
 
        // Update preference
        cache.updatePreference("CUST1", "{\"theme\":\"light\",\"lang\":\"fr\",\"customer\":\"CUST1\"}");
        System.out.println("After update: " + cache.getPreference("CUST1"));
 
        // Remove preference
        cache.removePreference("CUST1");
 
        // Fetch again (DB call)
        System.out.println("After removal, fetched again: " + cache.getPreference("CUST1"));
    }
}
 
