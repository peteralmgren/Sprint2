package BestGymEverTest;

import BestGymEver.Customer;
import BestGymEver.CustomerData;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Peter Almgren
 * Date: 2020-10-14
 * Time: 18:31
 * Project: ObjectorientedJava
 * Copyright: MIT
 */
public class CustomerTest {

    CustomerData cd = new CustomerData();

    @Test
    public final void readCustomerListTest(){
        List<Customer> customers = cd.readCustomerList();
        assertTrue(customers.size() == 14);
        assertFalse(customers.size() == 5);
    }

    @Test
    public final void PrintCustomersTest(){

        LocalDate dateMinusOneYear = LocalDate.now().minusYears(1);
        Customer c1 = new Customer("7706220199","Kalle Kula",
                LocalDate.of(2020,05,22));
        Customer c2 = new Customer("7406220199","Harald Grå",
                LocalDate.of(2018,05,22));

        assertTrue(c1.getDate().compareTo(dateMinusOneYear)>0);
        assertFalse(c2.getDate().compareTo(dateMinusOneYear)>0);

    }
    @Test
    public final void WriteCustomerFileTest() throws IOException {

        Customer c1 = new Customer("7706220199","Kalle Kula",
                LocalDate.of(2020,05,22));

        String approvedCustomers = "Betalande kunder";
        String pathName = "Test/BestGymEverTest/" + approvedCustomers + ".txt";
        Path path = Paths.get(pathName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.APPEND);){
            out.append("\nKunden besökte gymmet\n").append(c1.getName()).append(" ")
                    .append(c1.getIdNum());

        }
        assertTrue(pathName.contains("Betalande kunder"));
        assertFalse(pathName.contains("Kalle Kula"));

    }

    @Test
    public final void exceptionTest() {

        String input= "";

        try{
            if (input.isEmpty()) {
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            System.out.println("Du har inte matat in nått");
            e.printStackTrace();

        }catch (ArithmeticException e){
            System.out.println("Detta skall ej fångas");

        }
        assertTrue(input.isEmpty());
        assertFalse(!input.isEmpty());

    }

}
