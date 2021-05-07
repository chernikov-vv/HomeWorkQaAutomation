package ru.tinkoff.qa.homework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import ru.fintech.qa.homework.utils.BeforeUtils;
import ru.fintech.qa.homework.utils.DBService;


public class TestDb {


    @BeforeAll
    public static void beforeAll() {
        BeforeUtils.createData();
    }


    @Test
    public void test() {
        int actual = new DBService().executeQueryGetCount("select count (*) from public.animal");

        Assertions.assertEquals(10, actual);
    }


    @Test
    public void testNullId() {
        int count = 1;
        while (count < 11) {
            boolean actual = new DBService().executeQueryUpdate("insert into public.animal(id) "
                    + "values(" + count + ")");

            String exception = "Ожидалось исключение SQLException в строке, где id = " + count;

            Assertions.assertFalse(actual, exception);

            count++;
        }
    }


    @Test
    public void testNullName() {
        DBService dbService = new DBService();
        dbService.executeQueryUpdate("delete from public.workman where id = 100");
        boolean actual = dbService.executeQueryUpdate("insert into public.workman(id) values(100)");
        dbService.executeQueryUpdate("delete from public.workman where id = 100");

        Assertions.assertFalse(actual);
    }


    @Test
    public void testCountZoo() {
        int actual = new DBService().executeQueryGetCount("select count (*) from public.zoo where \"name\""
                + " in ('Центральный', 'Северный', 'Западный')");

        Assertions.assertEquals(3, actual);
    }


    @Test
    public void testZooNames() {
        DBService dbService = new DBService();
        dbService.executeQueryUpdate("delete from public.workman where id = 100");
        boolean actual = new DBService().executeQueryUpdate("insert into public.workman(id) values(100)");

        Assertions.assertFalse(actual);
    }
}
