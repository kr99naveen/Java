package interfaces;

public class Payment {

    private  PaymentProcessor paymentProcessor;
    Payment(PaymentProcessor pp){
        this.paymentProcessor = pp;
    }

    public void makePayment() throws InterruptedException{
        paymentProcessor.makePayment();
    }

    public void checkPayment() throws InterruptedException{
        paymentProcessor.checkPayment();
    }
}
