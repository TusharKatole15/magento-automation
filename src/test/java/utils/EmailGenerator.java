package utils;

import java.util.UUID;

public class EmailGenerator {

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        return "user_" + uuid + "@sample.com";
    }
}
