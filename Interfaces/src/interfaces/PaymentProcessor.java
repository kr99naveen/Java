package interfaces;

public interface PaymentProcessor {
    public void makePayment() throws InterruptedException;

    public void checkPayment() throws InterruptedException;
}
