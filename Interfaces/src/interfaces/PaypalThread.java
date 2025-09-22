package interfaces;

public class PaypalThread extends  Thread{

    private Payment payObj;
    PaypalThread(String name, Payment payObj){
        super(name);
        this.payObj = payObj;
    }

    @Override
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + " : RUNNING");
            payObj.makePayment();
        }catch (Exception e){
            System.out.println("Exception ::::: "+e);
        }
    }
}
