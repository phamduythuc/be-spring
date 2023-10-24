
import it.sauronsoftware.cron4j.Scheduler;
import org.springframework.stereotype.Component;
import java.util.TimeZone;

@Component
public class MainTest {
    public static void main(String[] args) {
        TimeZone timeZone = TimeZone.getTimeZone("Etc/GMT-7");

        Scheduler scheduler = new Scheduler();
        scheduler.setTimeZone(timeZone);
        scheduler.schedule("31 16 */1 * * ", () -> System.out.println("khue123"));
        scheduler.start();

    }
}
