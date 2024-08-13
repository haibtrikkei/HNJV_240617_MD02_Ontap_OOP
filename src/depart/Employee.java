package depart;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Employee {
    private String empId;
    private String fullName;
    private Boolean gender;
    private Date birthday;
    private String address;
    private int departId;
    private double salary;

    public Employee() {
    }

    public Employee(String empId, String fullName, Boolean gender, Date birthday, String address, int departId, double salary) {
        this.empId = empId;
        this.fullName = fullName;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
        this.departId = departId;
        this.salary = salary;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void inputData(Scanner scanner, Employee[] arrEmployees,Department[] arrDepartments, int index, int departIndex){
        this.empId = inputEmployeeId(scanner, arrEmployees, index);
        this.fullName = inputFullName(scanner);
        this.gender = inputGender(scanner);
        this.birthday = inputBirthday(scanner);
        this.address = inputAddress(scanner);
        this.departId = inputDepartId(scanner, arrDepartments, departIndex);
        this.salary = inputSalary(scanner);
    }

    public void displayData(){
        System.out.println("Employee: ");
        System.out.println("\tId: "+empId);
        System.out.println("\tFull name: "+fullName);
        System.out.println("\tGender: "+(gender?"male":"female"));
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("\tBirthday: "+sf.format(birthday));
        System.out.println("\tAddress: "+address);
        System.out.println("\tDepartment id: "+departId);
        System.out.println("\tSalary: "+salary);
    }

    private double inputSalary(Scanner scanner) {
        System.out.println("Nhap salary: ");
        do{
            try{
                double salary = Double.parseDouble(scanner.nextLine());
                if(salary<0)
                    System.out.println("Salary phai >0");
                else
                    return salary;
            }catch (Exception e){
                System.out.println("Salary phai la so thuc");
            }
        }while (true);
    }

    private int inputDepartId(Scanner scanner, Department[] arrDepartments, int departIndex) {
        System.out.println("Nhap department id: ");
        for(int i=0;i<=departIndex;i++){
            arrDepartments[i].displayData();
        }
        do{
            try{
                int departId = Integer.parseInt(scanner.nextLine());
                if(!isExistedDepartId(departId, arrDepartments, departIndex)){
                    System.out.println("Department id khong ton tai");
                }else{
                    return departId;
                }
            }catch (Exception e){
                System.out.println("Department id phai la so nguyen");
            }
        }while (true);
    }

    private boolean isExistedDepartId(int departId, Department[] arrDepartments, int departIndex) {
        if(departIndex==-1)
            return false;
        else{
            for(int i=0;i<=departIndex;i++)
                if(arrDepartments[i].getDepartId()==departId)
                    return true;
        }
        return false;
    }

    private String inputAddress(Scanner scanner) {
        System.out.println("Nhap address: ");
        do{
            String address = scanner.nextLine();
            if(address.length()<2)
                System.out.println("Address phai tu 2 ki tu tro len");
            else
                return address;
        }while (true);
    }

    private Date inputBirthday(Scanner scanner) {
        System.out.println("Nhap birthday: ");
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
        do{
            try{
                Date birthday = sf.parse(scanner.nextLine());
                return birthday;
            }catch (Exception e){
                System.out.println("Ngay sinh khong hop le");
            }
        }while (true);
    }

    private Boolean inputGender(Scanner scanner) {
        System.out.println("Nhap gender: ");
        do{
            try{
                Boolean gender = Boolean.parseBoolean(scanner.nextLine());
                return gender;
            }catch (Exception e){
                System.out.println("Gender chi duoc true hoac false");
            }
        }while (true);
    }

    private String inputFullName(Scanner scanner) {
        System.out.println("Nhap full name: ");
        do{
            String fullName = scanner.nextLine();
            if(fullName.length()<2)
                System.out.println("Full name phai tu 2 ki tu tro len");
            else
                return fullName;
        }while (true);
    }

    private String inputEmployeeId(Scanner scanner, Employee[] arrEmployees, int index) {
        System.out.println("Nhap vao employee id: ");
        do{
            String empId = scanner.nextLine();

            if(empId.length()!=4){
                System.out.println("Ma nhan vien phai co chinh xac 4 ki tu");
            }else if(empId.charAt(0)!='E'){
                System.out.println("Ma nhan vien bat dau phai la chu E");
            }else if(isExistedEmpId(empId,arrEmployees,index)){
                System.out.println("Ma nhan vien da trung");
            }else{
                return empId;
            }
        }while (true);
    }

    private boolean isExistedEmpId(String empId, Employee[] arrEmployees, int index) {
        if(index==-1)
            return false;
        else {
            for (int i = 0; i <= index; i++) {
                if (arrEmployees[i].getEmpId().equalsIgnoreCase(empId))
                    return true;
            }
        }
        return false;
    }

    public void inputDataUpdate(Scanner scanner, Employee[] arrEmployees, Department[] arrDepartments, int currentEmployeeIndex, int currentDepartIndex) {
        this.fullName = inputFullName(scanner);
        this.gender = inputGender(scanner);
        this.birthday = inputBirthday(scanner);
        this.address = inputAddress(scanner);
        this.departId = inputDepartId(scanner, arrDepartments, currentDepartIndex);
        this.salary = inputSalary(scanner);
    }
}
