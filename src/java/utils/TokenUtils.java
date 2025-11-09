package utils; 

import java.util.UUID;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class TokenUtils {

    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static Timestamp expiryAfterHours(int hours) {
        Instant t = Instant.now().plus(hours, ChronoUnit.HOURS);
        return Timestamp.from(t);
    }
}
