import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Test
    void failIfExecutionExceeds22Seconds() throws Exception{
        Main.main(new String[]{});
    }
}