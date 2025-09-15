package day5;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
 
class Employee {
    private String name, department;
    private int performanceScore, yearsOfExperience;
 
    public Employee(String name, String department, int performanceScore, int yearsOfExperience) {
        this.name = name;
        this.department = department;
        this.performanceScore = performanceScore;
        this.yearsOfExperience = yearsOfExperience;
    }
    public String getDepartment() { return department; }
    public int getPerformanceScore() { return performanceScore; }
    public int getYearsOfExperience() { return yearsOfExperience; }
}
 
class DepartmentReport {
    private String departmentName;
    private double avgPerformance, avgExperience;
    private int maxPerformance, minPerformance;
    private long employeeCount;
 
    public DepartmentReport(String dept, double avgPerf, int maxPerf, int minPerf,
                            double avgExp, long count) {
        this.departmentName = dept;
        this.avgPerformance = avgPerf;
        this.maxPerformance = maxPerf;
        this.minPerformance = minPerf;
        this.avgExperience = avgExp;
        this.employeeCount = count;
    }
 
    @Override
    public String toString() {
        return "Department: " + departmentName + "\n" +
               "   Avg Performance: " + String.format("%.2f", avgPerformance) + "\n" +
               "   Max Performance: " + maxPerformance + "\n" +
               "   Min Performance: " + minPerformance + "\n" +
               "   Avg Experience: " + String.format("%.2f", avgExperience) + "\n" +
               "   Employee Count: " + employeeCount + "\n";
    }
}
 
class DeptAccumulator {
    int totalPerf = 0, totalExp = 0, maxPerf = Integer.MIN_VALUE, minPerf = Integer.MAX_VALUE, count = 0;
 
    void add(Employee e) {
        totalPerf += e.getPerformanceScore();
        totalExp += e.getYearsOfExperience();
        maxPerf = Math.max(maxPerf, e.getPerformanceScore());
        minPerf = Math.min(minPerf, e.getPerformanceScore());
        count++;
    }
 
    DeptAccumulator combine(DeptAccumulator other) {
        totalPerf += other.totalPerf;
        totalExp += other.totalExp;
        maxPerf = Math.max(maxPerf, other.maxPerf);
        minPerf = Math.min(minPerf, other.minPerf);
        count += other.count;
        return this;
    }
 
    DepartmentReport finish(String dept) {
        return new DepartmentReport(dept,
                (double) totalPerf / count, maxPerf, minPerf,
                (double) totalExp / count, count);
    }
}
 
public class EmployeeReportGenerator {
    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("Alice", "Engineering", 85, 4),
                new Employee("Bob", "Engineering", 90, 5),
                new Employee("Charlie", "HR", 70, 2),
                new Employee("Diana", "Engineering", 60, 1),
                new Employee("Eve", "HR", 95, 6),
                new Employee("Frank", "Sales", 80, 3)
        );
 
        Map<String, DepartmentReport> reports = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collector.of(
                                DeptAccumulator::new,
                                DeptAccumulator::add,
                                DeptAccumulator::combine,
                                acc -> acc // keep raw accumulator
                        )
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue().finish(e.getKey())
                ));
 
        reports.values().forEach(System.out::println);
    }
}
