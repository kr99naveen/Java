package interfaces;

class Pen{
    public synchronized void writeWithPenAndPaper(Paper paper){
        System.out.println(Thread.currentThread().getName()+" writting using pen and paper "+this);
        paper.finishWrite();
    }

    public synchronized void finishWrite(){
        System.out.println(Thread.currentThread().getName()+" finishing write with pen "+this);
    }
}

class Paper {
    public synchronized void writeWithPaperAndPen(Pen pen){
        System.out.println(Thread.currentThread().getName() + " Writting using paper and pen " + this );
        pen.finishWrite();
    }

    public synchronized void finishWrite(){
        System.out.println(Thread.currentThread().getName() + " finishing write with paper " + this);
    }
}


class Task1 implements Runnable{

    private Pen pen;
    private Paper paper;
    Task1(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }
    @Override
    public void run() {
        pen.writeWithPenAndPaper(paper);
    }
}
class Task2 implements Runnable{

    private Pen pen;
    private Paper paper;
    Task2(Pen pen, Paper paper){
        this.pen = pen;
        this.paper = paper;
    }
//    this will create deadlock as this thread lock paper and trying to lock pen, locked other thread
//    @Override
//    public void run() {
//        paper.writeWithPaperAndPen(pen);
//    }

    //handling the deadlock, locking paper after making sure it has locked pen as well.
    @Override
    public void run() {
        synchronized (pen) {
            paper.writeWithPaperAndPen(pen);
        }
    }
}
public class Deadlock {
    public static void main(String[] args) {
        Paper paper = new Paper();
        Pen pen = new Pen();
        Thread t1 = new Thread(new Task1(pen, paper));
        Thread t2 = new Thread(new Task2(pen, paper));
        t1.start();
        t2.start();
    }

}
