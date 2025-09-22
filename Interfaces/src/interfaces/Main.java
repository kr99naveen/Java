package interfaces;

public class Main {

    public static void main(String[] args) {

//        JusPay payJusPay = new JusPay();
//        PayPal payPaypal = new PayPal();
//
//        Payment paymentByJuspay = new Payment(payJusPay);
//        Payment paymentByPaypal = new Payment(payPaypal);
//
//        paymentByJuspay.makePayment();
//        paymentByJuspay.checkPayment();
//        paymentByPaypal.makePayment();
//        paymentByPaypal.checkPayment();
//
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getPriority());

        JusPay payJusPay = new JusPay();
        Payment paymentByJuspay = new Payment(payJusPay);

        PayPal payPaypal = new PayPal();
        Payment paymentByPaypal = new Payment(payPaypal);


        JuspayThread t1 = new JuspayThread("juspay",paymentByJuspay);
        PaypalThread t2 = new PaypalThread("paypal", paymentByPaypal);
        JuspayThread2 t3 = new JuspayThread2("juspay2",paymentByJuspay);

//        System.out.println(t1.getName() + " - " + t1.getState());
//        System.out.println(t2.getName() + " - " + t2.getState());
//        System.out.println(t3.getName() + " - " + t3.getState());

        t1.start();
        t2.start();
        t3.start();

//        System.out.println(t1.getName() + " - " + t1.getState());
//        System.out.println(t2.getName() + " - " + t2.getState());
//        System.out.println(t3.getName() + " - " + t3.getState());
    }
}
