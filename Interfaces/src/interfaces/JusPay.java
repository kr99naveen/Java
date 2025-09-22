package interfaces;

import java.util.concurrent.locks.ReentrantLock;

public class JusPay extends ReentrantLock implements PaymentProcessor{
    private int amount=500;
    @Override
    public void checkPayment() throws InterruptedException {
        System.out.println("Payment Checked By ::: "+Thread.currentThread().getName()+" balance : "+this.amount);
    }

    @Override
    public void makePayment() throws  InterruptedException{
        try{
            if(tryLock()) {
                this.checkPayment();
                this.amount -= 50;
                System.out.println("Payment processed By ::: " + Thread.currentThread().getName());
                this.checkPayment();
                Thread.sleep(3000);
            } else throw new InterruptedException("Method locked.");
        }catch (Exception e){
            throw new InterruptedException("method is locked, please wait for some time.");
        }
        finally {
            unlock();
        }
    }
}
