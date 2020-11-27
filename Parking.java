import java.util.*;

public class Parking {
    int count_places;
    int max_len_turn_on_park;
    int T1;
    int T2;
    int len_current_turn = 0; //длина текущей очереди
    int count_cars = 0;
    int count_gruz = 0;
    int free_places;
    LinkedList<Auto> list_auto = new LinkedList<Auto>();
    LinkedList <Auto> list_turn = new LinkedList<Auto>();


    public Parking(int cnt, int len, int t1, int t2){
        this.count_places = cnt;
        this.max_len_turn_on_park = len;
        this.T1 = t1;
        this.T2 = t2;
        this.free_places = cnt;

        MyTime1();

    }
    public void Stat(){
        Timer timer = new Timer();
        MyTimerstat mt = new MyTimerstat(this);
        timer.schedule(mt, 5000, 5000);


    }
    public void MyTime1() {

        int Tvh = 0;

        Random random = new Random();
        int tempVh;

        Stat();

        while(len_current_turn != max_len_turn_on_park) {
            tempVh = Tvh + random.nextInt(Math.abs(T1 - Tvh));
            Timer timer = new Timer();

            int[] mas = {1,1,2};
            int gl = (int) (Math.random() * mas.length);

            MyTimerTask1 mt = new MyTimerTask1(mas[gl], this);
            timer.schedule(mt, tempVh * 1000);
            Tvh = Tvh + tempVh;
            push_auto_on_park();
            MyTime2();

        }
        System.out.println("Достигнута максимальная длина очереди. Программа завершает работу.");
        System.exit(0);


    }
    void push_auto_on_turn(int len){
        Auto a = new Auto(len);
        this.len_current_turn ++;
        if(a.len == 1) {
            System.out.println("Легковой автомобиль с id = "+ a.id + "встал в очередь на въезд.");
        }
        else
            System.out.println("Грузовой автомобиль с id = "+ a.id + "встал в очередь на въезд.");

        list_turn.add(a);
    }
    public void MyTime2(){
        int Tvyh = 0;
        Random random = new Random();
        int tempVyh;

        while(len_current_turn != max_len_turn_on_park) {
            tempVyh = Tvyh + random.nextInt(Math.abs(T2 - Tvyh));
            System.out.println("temp = " + tempVyh);
            Timer timer = new Timer();
            MyTimerTask2 mt = new MyTimerTask2(this);
            timer.schedule(mt, tempVyh * 1000);
            Tvyh = Tvyh + tempVyh;
        }
        System.out.println("Достигнута максимальная длина очереди. Программа завершает работу.");
        System.exit(0);
    }

    void push_auto_on_park(){
        Auto a = list_turn.getFirst();
        list_auto.push(list_turn.getFirst());
        list_turn.removeFirst();
        len_current_turn--;
        if (a.len == 1){
            count_cars++;
            System.out.println("Легковой автомобиль с id = " + a.id + " припарковался.");
            free_places--;
        }
        else {
            count_gruz++;
            System.out.println("Грузовой автомобиль с id = " + a.id + " припарковался.");
            free_places -=2;
        }
    }
}


class MyTimerTask1 extends TimerTask {
    Parking park;
    int dlina;
    public MyTimerTask1(int l, Parking p){
        park = p;
        dlina = l;
    }
    @Override
    public void run() {
        park.push_auto_on_turn(dlina);

    }
}
class MyTimerTask2 extends TimerTask {
    Parking park;
    public MyTimerTask2(Parking p){
        park = p;
    }
    @Override
    public void run() {
        Random rand = new Random();
        int temp = rand.nextInt(Auto.id_count);
        Auto a = park.list_auto.get(temp);
        park.free_places = park.free_places - a.len;
        if (a.len == 1){
            park.count_cars --;
            System.out.println("Легковой автомобиль с id =" +a.id + "покинул парковку");
        }
        else {
            park.count_gruz --;
            System.out.println("Грузовой автомобиль с id" + a.id+ "покинул парковку");
        }
        park.list_auto.remove(temp);
    }

}

class MyTimerstat extends TimerTask {
    Parking park;
    public MyTimerstat(Parking p){
        park = p;
    }

    @Override
    public void run() {
        System.out.println("Свободных мест: " + park.free_places + "\n Занято место: " + (park.count_places - park.free_places)
        +"(из них " + park.count_cars + "легковых и "+ park.count_gruz + "грузовых" +
                "\n Автомобилей, ожидающих в очереди: " + park.len_current_turn);
    }
}