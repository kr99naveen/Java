package interfaces;

class SharedResource{
    private int data;
    private boolean hasData;
    public synchronized void produce(int value) throws InterruptedException {
        while(hasData){
            try{
                wait();
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("produced : "+value);
        data = value;
        hasData=true;
        Thread.sleep(1000);
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while(!hasData){
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("consumed : "+data);
        hasData = false;
        Thread.sleep(500);
        notify();
        return data;

    }
}

class Producer implements Runnable{

    private SharedResource resource;

    Producer(SharedResource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try {
                resource.produce(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class  Consumer implements Runnable{
    SharedResource resource;

    Consumer(SharedResource resource){
        this.resource = resource;
    }

    @Override
    public void run() {
        for(int i=0;i<10;i++) {
            try{
                resource.consume();
            }catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class ThreadCommunication {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        Thread t1 = new Thread(new Producer(resource));
        Thread t2 = new Thread(new Consumer(resource));
        t1.start();
        t2.start();
    }
}
