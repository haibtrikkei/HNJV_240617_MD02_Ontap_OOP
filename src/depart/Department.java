package depart;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Department {
    private int departId;
    private String departName;
    private String description;
    private String phone;
    private Boolean status;

    public Department() {
    }

    public Department(int departId, String departName, String description, String phone, Boolean status) {
        this.departId = departId;
        this.departName = departName;
        this.description = description;
        this.phone = phone;
        this.status = status;
    }

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void inputData(Scanner scanner, Department[] arrDepartments, int index){
        this.departId = inputDepartId(arrDepartments, index);
        this.departName = inputDepartName(scanner, arrDepartments, index);
        this.description = inputDescription(scanner);
        this.phone = inputPhone(scanner, arrDepartments, index);
        this.status = inputStatus(scanner);
    }

    //ham hien thi
    public void displayData(){
        System.out.println("Deparment: ");
        System.out.println("\tId: "+departId);
        System.out.println("\tName: "+departName);
        System.out.println("\tDescription: "+description);
        System.out.println("\tPhone: "+phone);
        System.out.println("\tStatus: "+(status?"active":"nonactive"));
    }

    private Boolean inputStatus(Scanner scanner) {
        System.out.println("Nhap trang thai phong ban: ");
        do{
            try{
                String strStatus = scanner.nextLine();
                if(!(strStatus.equalsIgnoreCase("true")||strStatus.equalsIgnoreCase("false"))){
                    System.out.println("Phai nhap trang thai phong ban la true hoac false");
                }else
                    return Boolean.parseBoolean(strStatus);
            }catch (Exception e){
                System.out.println("Trang thai phong ban phai la true hoac false");
            }
        }while (true);
    }

    private String inputPhone(Scanner scanner, Department[] arrDepartments, int index) {
        System.out.println("Nhap so dien thoai: ");

        //Tao mau de xac thuc du lieu
        Pattern pat = Pattern.compile("^0[91384]\\d{7,8}$"); //So dien thoai tu 9 den 10 so

        do{
            String phone = scanner.nextLine();

            //Tao doi tuong de chua ket qua so sanh giua mau voi du lieu can kiem tra
            Matcher mat = pat.matcher(phone);

            if(!mat.find()){
                System.out.println("So dien thoai khong hop le");
            }else if(isExistedPhone(arrDepartments, index)){
                System.out.println("So dien thoai da co trong danh sach");
            }else{
                return phone;
            }
        }while (true);
    }

    private boolean isExistedPhone(Department[] arrDepartments, int index) {
        if(index==-1)
            return false;
        else{
            for(int i=0;i<=index;i++){
                if(arrDepartments[i].getPhone().equalsIgnoreCase(phone))
                    return true;
            }
        }
        return false;
    }

    private String inputDescription(Scanner scanner) {
        System.out.println("Nhap description: ");
        do{
            String description = scanner.nextLine();
            if(description.length()<5)
                System.out.println("Description phai tu 5 ki tu tro len");
            else
                return description;
        }while (true);
    }

    private String inputDepartName(Scanner scanner, Department[] arrDepartments, int index) {
        System.out.println("Nhap department name: ");
        Pattern pat = Pattern.compile("^[DP]\\w{5,}");
        do{
            String name = scanner.nextLine();
            if(!pat.matcher(name).find()){
                System.out.println("Ten phong ban toi thieu 6 ki tu va bat dau bang D hoac P");
            }else if (isExistedDepartName(name, arrDepartments, index)){
                System.out.println("Ten phong ban da ton tai");
            }else{
                return name;
            }
        }while (true);
    }

    private boolean isExistedDepartName(String name, Department[] arrDepartments, int index) {
        if(index==-1)
            return false;
        else{
            for(int i=0;i<=index;i++){
                if(arrDepartments[i].getDepartName().equalsIgnoreCase(name))
                    return true;
            }
        }
        return false;
    }

    private int inputDepartId(Department[] arrDepartments, int index) {
        if(index==-1)
            return 1;
        else
            return arrDepartments[index].getDepartId()+1;
    }

    public void inputUpdateData(Scanner scanner, Department[] arrDepartments, int currentDepartIndex) {
        this.departName = inputDepartName(scanner, arrDepartments, currentDepartIndex);
        this.description = inputDescription(scanner);
        this.phone = inputPhone(scanner, arrDepartments, currentDepartIndex);
        this.status = inputStatus(scanner);
    }
}
