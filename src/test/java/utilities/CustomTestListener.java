package utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomTestListener implements ITestListener {

    MyLogClass log =  new MyLogClass();
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        System.out.println("Test başladı: " + result.getName());
        log.info("Test başladı: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        System.out.println("Test başarılı: " + result.getName());
        log.info("Test başarılı: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        System.out.println("Test başarısız: " + result.getName());
        log.info("Test başarısız: " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
        System.out.println("Test tamamlandı: " + context.getName());
        log.info("Test tamamlandı: " + context.getName());
    }
}