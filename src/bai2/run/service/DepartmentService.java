package bai2.run.service;

import bai2.run.Main;
import data.Data;
import depart.Department;

import java.util.Scanner;

public class DepartmentService {
    public void departmentManagement(Scanner scanner){
        boolean flag = true;
        int choice;
        do{
            System.out.println("---------------------------DEPARTMENT MANAGEMENT---------------------------");
            System.out.println("1.\tNhập thông tin các phòng ban");
            System.out.println("2.\tHiển thị thông tin các phòng ban");
            System.out.println("3.\tCập nhật thông tin phòng ban");
            System.out.println("4.\tXóa phòng ban");
            System.out.println("5.\tCập nhật trạng thái phòng ban");
            System.out.println("6.\tThoát");

            choice = Main.inputChoice(scanner);
            switch (choice){
                case 1:
                    inputDepartments(scanner, Data.arrDepartments, Data.currentDepartIndex);
                    break;
                case 2:
                    showAllDepartments(Data.arrDepartments,Data.currentDepartIndex);
                    break;
                case 3:
                    updateDepartment(scanner, Data.arrDepartments, Data.currentDepartIndex);
                    break;
                case 4:
                    deleteDepartment(scanner, Data.arrDepartments, Data.currentDepartIndex);
                    break;
                case 5:
                    updateStatusOfDepartment(scanner, Data.arrDepartments, Data.currentDepartIndex);
                    break;
                case 6:
                    flag = false;
                    break;
                default:
                    System.out.println("Ban phai nhap lua chon tu 1 den 6");
            }
        }while (flag);
    }

    private void updateStatusOfDepartment(Scanner scanner, Department[] arrDepartments, int currentDepartIndex) {
        System.out.println("Nhap ma phong ban can cap nhat: ");
        int departId = inputDepartId(scanner);
        if(!isExistedDepartId(departId, arrDepartments, currentDepartIndex)){
            System.out.println("Ma phong ban khong ton tai");
        }else{
            //updat trang thai phong ban:
            //duyet mang tim vi tri co id can update, doi status tu true thanh false va nguoc lai
            for(int i=0;i<=currentDepartIndex;i++){
                if(arrDepartments[i].getDepartId()==departId){
                    arrDepartments[i].setStatus(!arrDepartments[i].getStatus());
                    break;
                }
            }
            Data.arrDepartments = arrDepartments;
            System.out.println("Da cap nhat trang thai cho phong ban co ma: "+departId);
        }
    }

    private void deleteDepartment(Scanner scanner, Department[] arrDepartments, int currentDepartIndex) {
        System.out.println("Nhap ma phong ban can xoa: ");
        int departId = inputDepartId(scanner);
        if(!isExistedDepartId(departId, arrDepartments, currentDepartIndex)){
            System.out.println("Ma phong ban khong ton tai");
        }else{
            //xoa phong ban:
            //duyet mang tim vi tri co id can update
            for(int i=0;i<=currentDepartIndex;i++){
                if(arrDepartments[i].getDepartId()==departId){
                    //lui cac phan tu dang sau ve truoc;
                    for(int j=i;j<currentDepartIndex;j++){
                        arrDepartments[j]=arrDepartments[j+1];
                    }
                    Data.currentDepartIndex--;
                    break;
                }
            }
            Data.arrDepartments = arrDepartments;
            System.out.println("Da xoa phong ban co ma: "+departId);
        }
    }

    private void updateDepartment(Scanner scanner, Department[] arrDepartments, int currentDepartIndex) {
        System.out.println("Nhap ma phong ban can cap nhat: ");
        int departId = inputDepartId(scanner);
        if(!isExistedDepartId(departId, arrDepartments, currentDepartIndex)){
            System.out.println("Ma phong ban khong ton tai");
        }else{
            //updat phong ban:
            Department dUpdate = new Department();
            dUpdate.setDepartId(departId);
            dUpdate.inputUpdateData(scanner, arrDepartments, currentDepartIndex);

            //duyet mang tim vi tri co id can update, thay the de doi tuong department moi
            for(int i=0;i<=currentDepartIndex;i++){
                if(arrDepartments[i].getDepartId()==departId){
                    arrDepartments[i] = dUpdate;
                    break;
                }
            }
            Data.arrDepartments = arrDepartments;
            System.out.println("Da cap nhat thong tin cho phong ban: "+dUpdate.getDepartName());
        }
    }

    private boolean isExistedDepartId(int departId, Department[] arrDepartments, int currentDepartIndex) {
        if(currentDepartIndex==-1)
            return false;
        else{
            for(int i=0;i<=currentDepartIndex;i++){
                if(arrDepartments[i].getDepartId()==departId)
                    return true;
            }
        }
        return false;
    }

    private int inputDepartId(Scanner scanner) {
        do{
            try{
                int departId = Integer.parseInt(scanner.nextLine());
                if(departId<1)
                    System.out.println("Ma phong ban phai >0");
                else
                    return departId;
            }catch (Exception e){
                System.out.println("Ma phong ban phai la so nguyen");
            }
        }while (true);
    }

    private void showAllDepartments(Department[] arrDepartments, int currentDepartIndex) {
        if(currentDepartIndex==-1){
            System.out.println("Khong co phong ban nao");
            return;
        }
        System.out.println("Thong tin cac phong ban: ");
        for(int i=0;i<=currentDepartIndex;i++){
            arrDepartments[i].displayData();
        }
    }

    private void inputDepartments(Scanner scanner, Department[] arrDepartments, int currentDepartIndex) {
        int n = inputNumberDepartments(scanner);
        for(int i=0;i<n;i++){
            Department d = new Department();
            d.inputData(scanner, arrDepartments,currentDepartIndex);
            Data.arrDepartments[i] = d;
            currentDepartIndex++;
        }
        Data.currentDepartIndex = currentDepartIndex;
    }

    private int inputNumberDepartments(Scanner scanner) {
        System.out.println("Nhap so phong ban can them: ");
        do{
            try{
                int n = Integer.parseInt(scanner.nextLine());
                if(n<1)
                    System.out.println("Phai nhap so phong ban >0");
                else
                    return n;
            }catch (Exception e){
                System.out.println("So phong ban phai la so nguyen duong");
            }
        }while (true);
    }
}
