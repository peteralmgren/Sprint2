package BestGymEver;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Peter Almgren
 * Date: 2020-10-12
 * Time: 18:35
 * Project: ObjectorientedJava
 * Copyright: MIT
 */
public class Customer implements IprintCustomerToFile {

    protected String idNum;
    protected String name;
    protected LocalDate subscriptionDate;

    public Customer(String idNum, String name, LocalDate subscriptionDate) {
        this.idNum = idNum;
        this.name = name;
        this.subscriptionDate = subscriptionDate;
    }

    public String getIdNum() {
        return idNum;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDate() {
        return subscriptionDate;
    }

    @Override
    public void printCustomerToFile() throws IOException {

        String approvedCustomers = "Betalande kunder";
        String pathName = "src/BestGymEver/" + approvedCustomers + ".txt";
        Path path = Paths.get(pathName);
        Files.createDirectories(path.getParent());

        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        try (BufferedWriter out = Files.newBufferedWriter(path, StandardOpenOption.APPEND);) {
            out.append("Kunden besökte gymmet\n").append(name).append(" ").append(idNum).append(" ")
                    .append("\nDatum: ").append(String.valueOf(LocalDate.now()))
                    .append("\nKlockan: ").append(String.valueOf(LocalTime.now()));

        } catch (NoSuchFileException e) {
            System.out.println("Det finns ingen sådan fil");
            e.printStackTrace();
            System.exit(0);

        } catch (Exception e) {
            System.out.println("Det går inte att skriva till filen");
            e.printStackTrace();
            System.exit(0);
        }
    }
}
