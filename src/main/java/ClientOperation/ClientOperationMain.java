package ClientOperation;

import java.util.List;
import java.util.Scanner;

import com.MobileServices.AuthService;
import com.MobileServices.MobileServices;
import com.Mobiles.Mobile;

public class ClientOperationMain {

	public static void main(String[] args) {

		MobileServices mobileServices = new MobileServices();
		AuthService authService = new AuthService();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter username: ");
		String username = scanner.nextLine();
		System.out.println("Enter Password: ");
		String password = scanner.nextLine();

		if (authService.authenticate(username, password)) {
			System.out.println("Loggin succesfull....! : ");

			String role = authService.getUserRole(username);
			int choice;

			while (true) {
				System.out.println("\nMenu:");
				System.out.println("1. Add Mobile");
				System.out.println("2. Delete Mobile");
				System.out.println("3. Get All Mobile Data");
				System.out.println("4. Fetch Mobile by ID");
				System.out.println("5. Update Mobile Name");
				System.out.println("6. Update Mobile Quantity");
				System.out.println("7. Update Mobile Cost");
				System.out.println("8. Search by Cost Range");
				  System.out.println("9. Search by Brand Name");
				System.out.println("10. Exit");
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();

				// contrroll acces based on role

				if (role.equals("user") && (choice == 1 || choice == 2)) {
					System.out.println("Acces denide: you do not have permisstion to perform this opration.");
					continue;
				}

				switch (choice) {
				case 1:
					// Add Mobile
					System.out.print("Enter mobile ID: ");
					int id = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					System.out.print("Enter mobile name: ");
					String name = scanner.nextLine();
					System.out.print("Enter mobile quantity: ");
					int quantity = scanner.nextInt();
					System.out.print("Enter mobile cost: ");
					int cost = scanner.nextInt();
					String addResult = mobileServices.addMobile(id, name, quantity, cost);
					System.out.println(addResult);
					break;

				case 2:
					// Delete Mobile
					System.out.print("Enter mobile ID to delete: ");
					int deleteId = scanner.nextInt();
					String deleteResult = mobileServices.deleteMobile(deleteId);
					System.out.println(deleteResult);
					break;

				case 3:
					// Get All Mobile Data
					List<Mobile> mobiles = mobileServices.getAllMobileData();
					if (mobiles != null && !mobiles.isEmpty()) {
						System.out.println("\nAll Mobile Data:");
						System.out.println("-------------------------------------------------------");
						System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Quantity", "Cost");
						System.out.println("-------------------------------------------------------");
						for (Mobile mobile : mobiles) {
							System.out.printf("%-5d %-15s %-10d %-10d\n", mobile.getId(), mobile.getMobileName(),
									mobile.getMobileQuantity(), mobile.getMobileCost());
						}
						System.out.println("-------------------------------------------------------");
					} else {
						System.out.println("No Mobile data found");
					}
					break;

				case 4:
					// Fetch Mobile by ID
					System.out.print("Enter mobile ID to fetch: ");
					int fetchId = scanner.nextInt();
					Mobile mobile = mobileServices.FetchMobileById(fetchId);
					if (mobile != null) {
						System.out.println("\nRetrieved Mobile Data:");
						System.out.println("-------------------------------------------------------");
						System.out.printf("%-5s %-15s %-10s %-10s\n", "ID", "Name", "Quantity", "Cost");
						System.out.println("-------------------------------------------------------");
						System.out.printf("%-5d %-15s %-10d %-10d\n", mobile.getId(), mobile.getMobileName(),
								mobile.getMobileQuantity(), mobile.getMobileCost());
						System.out.println("-------------------------------------------------------");
					} else {
						System.out.println("Mobile not found");
					}
					break;

				case 5:
					// Update Mobile Name
					System.out.print("Enter mobile ID to update: ");
					int updateIdName = scanner.nextInt();
					scanner.nextLine(); // Consume newline
					System.out.print("Enter new mobile name: ");
					String newName = scanner.nextLine();
					String updateNameResult = mobileServices.updateMobileName(updateIdName, newName);
					System.out.println(updateNameResult);
					break;

				case 6:
					// Update Mobile Quantity
					System.out.print("Enter mobile ID to update: ");
					int updateIdQuantity = scanner.nextInt();
					System.out.print("Enter new mobile quantity: ");
					int newQuantity = scanner.nextInt();
					String updateQuantityResult = mobileServices.updateMobileQuantity(updateIdQuantity, newQuantity);
					System.out.println(updateQuantityResult);
					break;

				case 7:
					// Update Mobile Cost
					System.out.print("Enter mobile ID to update: ");
					int updateIdCost = scanner.nextInt();
					System.out.print("Enter new mobile cost: ");
					int newCost = scanner.nextInt();
					String updateCostResult = mobileServices.updateMobileCost(updateIdCost, newCost);
					System.out.println(updateCostResult);
					break;

				case 8:
					// Search by Cost Range
					System.out.print("Enter minimum cost: ");
					int minCost = scanner.nextInt();
					System.out.print("Enter maximum cost: ");
					int maxCost = scanner.nextInt();
					List<Mobile> costMobiles = mobileServices.getMobilileByCostRange(minCost, maxCost);
					if (costMobiles != null && !costMobiles.isEmpty()) {
						System.out.println("\nMobiles in cost range " + minCost + " to " + maxCost + ":");
						for (Mobile mobile1 : costMobiles) {
							System.out.printf("%-5d %-15s %-10d %-10d\n", mobile1.getId(), mobile1.getMobileName(),
									mobile1.getMobileQuantity(), mobile1.getMobileCost());
						}
					} else {
						System.out.println("No mobiles found in this cost range.");
					}
					break;
					
					
				   case 9:
                       // Search by Brand Name
                       System.out.print("Enter brand name: ");
                       scanner.nextLine(); // Consume newline
                       String brandName = scanner.nextLine();
                       List<Mobile> brandMobiles = mobileServices.getMobileByBrandName(brandName);
                       if (brandMobiles != null && !brandMobiles.isEmpty()) {
                           System.out.println("\nMobiles under brand: " + brandName);
                           for (Mobile mobile1 : brandMobiles) {
                               System.out.printf("%-5d %-15s %-10d %-10d\n",
                                       mobile1.getId(),
                                       mobile1.getMobileName(),
                                       mobile1.getMobileQuantity(),
                                       mobile1.getMobileCost());
                           }
                       } else {
                           System.out.println("No mobiles found for this brand.");
                       }
                       break;

				case 10:
					// Exit
					System.out.println("Exiting...");
					scanner.close();
					System.exit(0);
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			}
		} else {
			System.out.println("invalid username or password . Acces denide.");
		}

	}
}
