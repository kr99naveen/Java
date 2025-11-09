package interfaces;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class JusPay extends ReentrantLock implements PaymentProcessor{
    private int amount=500;
    @Override
    public void checkPayment() throws InterruptedException {
        System.out.println("Payment Checked By ::: "+Thread.currentThread().getName()+" balance : "+this.amount);
    }

    @Override
    public synchronized void makePayment() throws  InterruptedException{
        try{
            if(tryLock(3000, TimeUnit.MILLISECONDS )) {
                this.checkPayment();
                this.amount -= 50;
                System.out.println("Payment processed By ::: " + Thread.currentThread().getName());
                this.checkPayment();
                Thread.sleep(3000);
            } else {
//                Thread.currentThread().interrupt();
                //interrupt is used when we want to say this thread stop whatever you are doing, i.e interrupt it
                throw new InterruptedException("Method locked, please try later");
            }
        }catch ( Exception e){
//            Thread.currentThread().interrupt();
            //interrupt is used when we want to say this thread stop whatever you are doing, i.e interrupt it
            throw new InterruptedException(e.getMessage());
        }

        finally {
            unlock();
        }
    }
}
