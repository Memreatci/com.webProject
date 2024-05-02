package utilities;


import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import java.time.LocalDateTime;


public class TestLogger extends TestWatcher {

    @Override
    protected void succeeded(Description description) {
        super.succeeded(description);
        System.out.println("Test başarıyla tamamlandı: " + description.getDisplayName());
    }

    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
        System.out.println(description+ "FAAAAAAILLL");
    }

    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        super.skipped(e, description);
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
        System.out.println("Test başlıyor: " + description.getDisplayName());
        // Başlangıç zamanı
        System.out.println("Başlangıç zamanı: " + LocalDateTime.now());
    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
    }
}
