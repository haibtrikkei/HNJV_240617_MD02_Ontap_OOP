package bai2.run;

import bai2.run.service.DepartmentService;
import bai2.run.service.ProductService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        DepartmentService departService = new DepartmentService();
        ProductService productService = new ProductService();

        do{
            System.out.println("---------------------------EMPLOYEE DEPARTMENT MENU---------------------------");
            System.out.println("1. Quan ly phong ban");
            System.out.println("2. Quan ly nhan vien");
            System.out.println("3. Thoat");
            choice = inputChoice(scanner);
            switch (choice){
                case 1:
                    departService.departmentManagement(scanner);
                    break;
                case 2:
                    productService.productManagement(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Ban phai nhap lua chon tu 1 den 3");
            }
        }while (true);
    }

    public static int inputChoice(Scanner scanner) {
        System.out.println("Nhap vao lua chon: ");
        do{
            try{
                int choice = Integer.parseInt(scanner.nextLine());
                return choice;
            }catch (Exception e){
                System.out.println("Choice phai nhap la so");
            }
        }while (true);
    }
}
