package interfaces;

public class PayPal implements PaymentProcessor{

    private int amount =1000;
    @Override
    public void checkPayment() {
        System.out.println("Payment Checked By ::: "+Thread.currentThread().getName()+" balanc :: "+this.amount);
    }

    @Override
    public void makePayment() {
        this.checkPayment();
        this.amount-=100;
        System.out.println("Payment processed By ::: "+Thread.currentThread().getName());
        this.checkPayment();
    }
}
