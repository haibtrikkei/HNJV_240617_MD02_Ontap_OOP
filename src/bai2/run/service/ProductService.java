package bai2.run.service;

import bai2.run.Main;
import data.Data;
import depart.Department;
import depart.Employee;

import java.util.Scanner;

public class ProductService {
    public void productManagement(Scanner scanner){
        boolean flag = true;
        int choice;
        do{
            System.out.println("---------------------------EMPLOYEE MANAGEMENT---------------------------");
            System.out.println("1.\tNhập thông tin các nhân viên");
            System.out.println("2.\tHiển thị thông tin các nhân viên");
            System.out.println("3.\tSắp xếp các nhân viên theo lương giảm dần");
            System.out.println("4.\tCập nhật thông tin nhân viên theo mã nhân viên");
            System.out.println("5.\tXóa nhân viên theo mã nhân viên");
            System.out.println("6.\tTìm kiếm các nhân viên theo tên phòng ban");
            System.out.println("7.\tTìm kiếm nhân viên trong khoảng lương a – b (a,b nhập từ bàn phím)");
            System.out.println("8.\tThoát");
            choice = Main.inputChoice(scanner);
            switch (choice){
                case 1:
                    inputEmployees(scanner, Data.arrEmployees, Data.arrDepartments, Data.currentEmployeeIndex, Data.currentDepartIndex);
                    break;
                case 2:
                    showAllEmployees(Data.arrEmployees,Data.currentEmployeeIndex);
                    break;
                case 3:
                    sortEmployeeDescedingBySalary(Data.arrEmployees, Data.currentEmployeeIndex);
                    break;
                case 4:
                    updateEmployee(scanner, Data.arrEmployees, Data.currentEmployeeIndex);
                    break;
                case 5:
                    deleteEmployee(scanner, Data.arrEmployees, Data.currentEmployeeIndex);
                    break;
                case 6:
                    searchEmployeesByDepartName(scanner, Data.arrEmployees, Data.currentEmployeeIndex);
                    break;
                case 7:
                    searchEmployeeByBetweenSalary(scanner, Data.arrEmployees, Data.currentEmployeeIndex);
                    break;
                case 8:
                    flag = false;
                default:
                    System.out.println("Ban phai nhap lua chon tu 1 den 6");
            }
        }while (flag);
    }

    private void searchEmployeeByBetweenSalary(Scanner scanner, Employee[] arrEmployees, int currentEmployeeIndex) {
        System.out.println("Nhap khoang luong min: ");
        double salaryMin = inputSalary(scanner);

        System.out.println("Nhap khoang luong max: ");
        double salaryMax = inputSalary(scanner);

        System.out.println("Thong tin cac nhan vien co luong tu "+salaryMin+" den "+salaryMax);
        int count = 0;
        for(int i=0;i<=currentEmployeeIndex;i++){
            if(arrEmployees[i].getSalary()>=salaryMin && arrEmployees[i].getSalary()<=salaryMax){
                arrEmployees[i].displayData();
                count++;
            }
        }
        System.out.println("Co "+count+" nhan vien duoc tim thay!");
    }

    private double inputSalary(Scanner scanner) {
        do{
            double salary = Double.parseDouble(scanner.nextLine());
            if(salary<0)
                System.out.println("Phai nhap salary >0");
            else
                return salary;
        }while (true);
    }

    private void searchEmployeesByDepartName(Scanner scanner, Employee[] arrEmployees, int currentEmployeeIndex) {
        System.out.println("Nhap ten phong ban can tim kiem cac nhan vien: ");
        String departName = scanner.nextLine();

        //kiem tra ten phong ban co ton tai trong danh sach hay khong?
        if(!isExistedDepartName(departName,Data.arrDepartments,Data.currentDepartIndex)){
            System.out.println("Ten phong ban khong ton tai");
        }else {
            System.out.println("Thong tin cac nhan vien cua phong: " + departName);
            //lay ma phong ban tu ten phong ban:
            int departId = getDepartIdFromDepartName(departName, Data.arrDepartments, Data.currentDepartIndex);
            for(int i=0;i<=currentEmployeeIndex;i++){
                if(arrEmployees[i].getDepartId()==departId){
                    arrEmployees[i].displayData();
                }
            }
        }
    }

    private int getDepartIdFromDepartName(String departName, Department[] arrDepartments, int currentDepartIndex) {
        if(currentDepartIndex==-1)
            return -1;
        else{
            for(int i=0;i<=currentDepartIndex;i++){
                if(arrDepartments[i].getDepartName().equalsIgnoreCase(departName)){
                    return arrDepartments[i].getDepartId();
                }
            }
        }
        return -1;
    }

    private boolean isExistedDepartName(String departName, Department[] arrDepartments, int currentDepartIndex) {
        if(currentDepartIndex==-1)
            return false;
        else {
            for(int i=0;i<=currentDepartIndex;i++)
                if(arrDepartments[i].getDepartName().equalsIgnoreCase(departName))
                    return true;
        }
        return false;
    }

    private void deleteEmployee(Scanner scanner, Employee[] arrEmployees, int currentEmployeeIndex) {
        System.out.println("Nhap ma nhan vien can xoa: ");
        String empIdDelete = scanner.nextLine();

        if(!isExistedEmployeeId(empIdDelete, arrEmployees, currentEmployeeIndex)){
            System.out.println("Ma nhan vien khong ton tai");
        }else{
            for(int i=0;i<=currentEmployeeIndex;i++){
                if(arrEmployees[i].getEmpId().equalsIgnoreCase(empIdDelete)){
                    for(int j=i;j<=currentEmployeeIndex;j++){
                        arrEmployees[j] = arrEmployees[j+1];
                    }
                    Data.currentEmployeeIndex--;
                    break;
                }
            }
            Data.arrEmployees = arrEmployees;
        }
    }

    private void updateEmployee(Scanner scanner, Employee[] arrEmployees, int currentEmployeeIndex) {
        System.out.println("Nhap ma nhan vien can cap nhat: ");
        String empIdUpdate = scanner.nextLine();

        if(!isExistedEmployeeId(empIdUpdate, arrEmployees, currentEmployeeIndex)){
            System.out.println("Ma nhan vien khong ton tai");
        }else{
            //updat nhan vien:
            Employee eUpdate = new Employee();
            eUpdate.setEmpId(empIdUpdate);
            eUpdate.inputDataUpdate(scanner,arrEmployees,Data.arrDepartments,currentEmployeeIndex,Data.currentDepartIndex);

            //duyet mang de thay the phan tu Employee can update:
            for(int i=0;i<=currentEmployeeIndex;i++){
                if(arrEmployees[i].getEmpId().equalsIgnoreCase(empIdUpdate)){
                    arrEmployees[i] = eUpdate;
                }
            }
            //gan nguoc lai cho class Data
            Data.arrEmployees = arrEmployees;
            System.out.println("Da cap nhat thong tin cho nhan vien: "+eUpdate.getFullName());
        }
    }

    private boolean isExistedEmployeeId(String empIdUpdate, Employee[] arrEmployees, int currentEmployeeIndex) {
        if(currentEmployeeIndex==-1)
            return false;
        else{
            for(int i=0;i<=currentEmployeeIndex;i++){
                if(arrEmployees[i].getEmpId().equalsIgnoreCase(empIdUpdate))
                    return true;
            }
        }
        return false;
    }


    private void sortEmployeeDescedingBySalary(Employee[] arrEmployees, int currentEmployeeIndex) {
        for(int i=0;i<currentEmployeeIndex;i++)
            for(int j=currentEmployeeIndex;j>i;j--)
                if(arrEmployees[j-1].getSalary()<arrEmployees[j].getSalary()){
                    Employee tg = arrEmployees[j-1];
                    arrEmployees[j-1] = arrEmployees[j];
                    arrEmployees[j] = tg;
                }

        System.out.println("Thong tin sap xep giam dan theo luong: ");
        showAllEmployees(arrEmployees,currentEmployeeIndex);
    }

    private void showAllEmployees(Employee[] arrEmployees, int currentEmployeeIndex) {
        System.out.println("Thong tin cac nhan vien: ");
        for(int i=0;i<=currentEmployeeIndex;i++){
            arrEmployees[i].displayData();
        }
    }

    private void inputEmployees(Scanner scanner, Employee[] arrEmployees, Department[] arrDepartments, int currentEmployeeIndex, int currentDepartIndex) {
        if(Data.currentDepartIndex==-1){
            System.out.println("Chua co phong ban nao, ban phai nhap phong ban truoc");
            return;
        }
        int n = inputNumberEmployees(scanner);
        for(int i=0;i<n;i++) {
            Employee e = new Employee();
            e.inputData(scanner,arrEmployees,arrDepartments,currentEmployeeIndex,currentDepartIndex);
            arrEmployees[i] = e;
            Data.currentEmployeeIndex++;
        }
        Data.arrEmployees = arrEmployees;
    }


    private int inputNumberEmployees(Scanner scanner) {
        System.out.println("Nhap so nhan vien can them: ");
        do{
            try{
                int n = Integer.parseInt(scanner.nextLine());
                if(n<1)
                    System.out.println("Phai nhap so nhan vien >0");
                else
                    return n;
            }catch (Exception e){
                System.out.println("So nhan vien phai la so nguyen duong");
            }
        }while (true);
    }
}
