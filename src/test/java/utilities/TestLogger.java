package utilities;

import org.junit.AssumptionViolatedException;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.time.LocalDateTime;

public class TestLogger extends TestWatcher {
    /**
     * @param description
     */
    @Override
    protected void succeeded(Description description) {
        super.succeeded(description);
        System.out.println("Test başarıyla tamamlandı: " + description.getDisplayName());
    }

    /**
     * @param e
     * @param description
     */
    @Override
    protected void failed(Throwable e, Description description) {
        super.failed(e, description);
    }

    /**
     * @param e
     * @param description
     */
    @Override
    protected void skipped(AssumptionViolatedException e, Description description) {
        super.skipped(e, description);
    }

    /**
     * @param description
     */
    @Override
    protected void starting(Description description) {
        super.starting(description);
        System.out.println("Test başlıyor: " + description.getDisplayName());
        // Başlangıç zamanı
        System.out.println("Başlangıç zamanı: " + LocalDateTime.now());
    }

    /**
     * @param description
     */
    @Override
    protected void finished(Description description) {
        super.finished(description);
    }
}
