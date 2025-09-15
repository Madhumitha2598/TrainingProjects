package day4;

import java.io.*;
import java.util.*;
 
class Customer {
    int id;
    String name;
    String email;
    String phone;
    String city;
 
    public Customer(int id, String name, String email, String phone, String city) {
        this.id = id;
        this.name = name;
        this.email = email.toLowerCase(); // normalize for duplicate check
        this.phone = phone;
        this.city = city;
    }
 
    @Override
    public String toString() {
        return id + "," + name + "," + email + "," + phone + "," + city;
    }
}
 
public class CustomerDataCleaner {
 
    public static void main(String[] args) {
        String inputFile = "customers.csv";
        String outputFile = "cleaned_customers.csv";
 
        List<Customer> customers = readCustomers(inputFile);
        List<Customer> cleaned = removeDuplicates(customers);
        cleaned.sort(Comparator
                .comparing((Customer c) -> c.city)
                .thenComparing(c -> c.name));
 
        writeCustomers(cleaned, outputFile);
        System.out.println("Cleaning completed. Output saved to " + outputFile);
    }
 
    private static List<Customer> readCustomers(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    String email = parts[2].trim();
                    String phone = parts[3].trim();
                    String city = parts[4].trim();
                    customers.add(new Customer(id, name, email, phone, city));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }
 
    private static List<Customer> removeDuplicates(List<Customer> customers) {
        Map<String, Customer> emailMap = new HashMap<>();
        Map<String, Customer> phoneMap = new HashMap<>();
 
        for (Customer c : customers) {
            Customer existing = emailMap.get(c.email);
            if (existing == null) existing = phoneMap.get(c.phone);
 
            if (existing == null || c.id < existing.id) {
                emailMap.put(c.email, c);
                phoneMap.put(c.phone, c);
            }
        }
 
        // return unique values
        return new ArrayList<>(new HashSet<>(emailMap.values()));
    }
 
    private static void writeCustomers(List<Customer> customers, String fileName) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            pw.println("CustomerID,Name,Email,Phone,City");
            for (Customer c : customers) {
                pw.println(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}