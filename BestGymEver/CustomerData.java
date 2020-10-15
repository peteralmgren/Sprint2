package BestGymEver;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Peter Almgren
 * Date: 2020-10-12
 * Time: 18:32
 * Project: ObjectorientedJava
 * Copyright: MIT
 */
public class CustomerData {

    public List<Customer> readCustomerList() {

        List<Customer> customerList = new ArrayList<>();
        Path inFilePath = Paths.get("src/BestGymEver/customers.txt");

        try (Scanner in = new Scanner(inFilePath)) {

            while (in.hasNext()) {
                String firstLine = in.nextLine();
                String[] customerFirstLine = firstLine.split(",");

                if (in.hasNext()) {
                    String secondLine = in.nextLine();
                    LocalDate date = LocalDate.parse(secondLine);

                    Customer c = new Customer(customerFirstLine[0],
                            customerFirstLine[1].substring(1), date);
                    customerList.add(c);
                }
            }
        } catch (NoSuchFileException e) {
            System.out.println("Filen kunde inte hittas.");
            e.printStackTrace();
            System.exit(0);

        } catch (IOException e) {
            System.out.println("Fel inträffade vid läsning från fil");
            e.printStackTrace();
            System.exit(0);
        }
        return customerList;
    }

    public void PrintCustomers() throws IOException {

        CustomerData cd = new CustomerData();
        LocalDate dateMinusOneYear = LocalDate.now().minusYears(1);
        int counter = 0;

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Sök efter namn eller personnummer:");
            String input = sc.nextLine();
            if (input.isEmpty()) {
                throw new NullPointerException();
            }

            for (Customer c : cd.readCustomerList()) {
                if (c.getName().equalsIgnoreCase(input) || c.getIdNum().equalsIgnoreCase(input)) {
                    counter++;
                    if (c.subscriptionDate.compareTo(dateMinusOneYear) > 0) {
                        System.out.println(c.getName() + " är en betalande kund.");
                        c.printCustomerToFile();
                    } else {
                        System.out.println("Medlemskap för " + c.getName() + " har gått ut.");
                    }
                }
            }

        } catch (NullPointerException e) {
            System.out.println("Du har inte matat in nått");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Okänt fel");
            e.printStackTrace();
        }
        if (counter < 1) System.out.println("Kund är obehörig!. Finns ej i register");
    }

    public static void main(String[] args) throws IOException {
        CustomerData cd = new CustomerData();
        cd.PrintCustomers();

    }
}
