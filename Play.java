import java.util.Scanner;

public class Play {
    public static void main(String[] args) {
        System.out.println("Введите количество парковочных мест\n" +
                "- максимальную длину очереди автомобилей ожидающих въезда на парковку\n" +
                "- интервал генерации входящих автомобилей в секундах\n" +
                "- интервал генерации выходящих автомобилей в секундах");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        int max_len = scanner.nextInt();
        int t1 = scanner.nextInt();
        int t2 = scanner.nextInt();
        Parking park = new Parking(count, max_len, t1, t2);
    }
}
