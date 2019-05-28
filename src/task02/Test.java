package task02;

/*
 * 2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a) Используя параметризированный запрос
 * b) Используя batch процесс
 */
public class Test {
    public static void main(String[] args) {
        Task02 task02 = new Task02();
//        task02.query("param");
        task02.query("batch");
    }
}
