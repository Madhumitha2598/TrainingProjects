package day2;

//Base interface
interface Role {
 void accessResource(String resource);
}

//Admin role - full access
class Admin implements Role {
 public void accessResource(String resource) {
     System.out.println("Access granted to " + resource);
 }
}

//Manager role - limited access
class Manager implements Role {
 public void accessResource(String resource) {
     if (resource.equalsIgnoreCase("reports") || resource.equalsIgnoreCase("team-data")) {
         System.out.println("Access granted to " + resource);
     } else {
         System.out.println("Access denied to " + resource);
     }
 }
}

//Employee role - team-data only
class Employee implements Role {
 public void accessResource(String resource) {
     if (resource.equalsIgnoreCase("team-data")) {
         System.out.println("Access granted to " + resource);
     } else {
         System.out.println("Access denied to " + resource);
     }
 }
}

//Guest role - only public-info
class Guest implements Role {
 public void accessResource(String resource) {
     if (resource.equalsIgnoreCase("public-info")) {
         System.out.println("Access granted to " + resource);
     } else {
         System.out.println("Access denied to " + resource);
     }
 }
}
public class RoleBasedAccessSystem {
	 public static void main(String[] args) {
	        Role admin = new Admin();
	        Role manager = new Manager();
	        Role employee = new Employee();
	        Role guest = new Guest();
	 
	        // Testing Admin
	        admin.accessResource("confidential");  // granted
	        admin.accessResource("team-data");     // granted
	 
	        // Testing Manager
	        manager.accessResource("reports");      // granted
	        manager.accessResource("confidential"); // denied
	 
	        // Testing Employee
	        employee.accessResource("team-data");   // granted
	        employee.accessResource("reports");     // denied
	 
	        // Testing Guest
	        guest.accessResource("public-info");    // granted
	        guest.accessResource("team-data");      // denied
	    }
}
