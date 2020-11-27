public class Auto {
    public static int id_count = 1;
    int id = id_count;
    int len;
    public Auto(int length){
        id_count++;
        this.len = length;
    }
}
