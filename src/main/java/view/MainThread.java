package view;

import dao.EmployeeDao;
import model.Employee;

import java.util.Scanner;

public class MainThread {
    private static EmployeeDao employeeDao=new EmployeeDao();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        do {
            System.out.println("|   MENU");
            System.out.println("|   1. Đăng ký");
            System.out.println("|   2. Đăng nhập");
            System.out.println("|   3. Thoát");
            System.out.println("Chọn chức năng : [1- 3]");
            int chon = scanner.nextInt();
            switch (chon) {
                case 1:
                    boolean c=true;
                    System.out.println("Form dang ky");
                    scanner.nextLine();
                    String account=null;
                    do{
                        System.out.print("Tai khoan:");
                        account=scanner.nextLine();
                        if(employeeDao.checkExistAccount(account)){
                            System.out.println("Da ton tai tai khoan");
                        }else {
                            c=false;
                        }
                    }while (c);
                    System.out.print("Mat khau:");
                    String password=scanner.nextLine();
                    System.out.print("Ten:");
                    String name=scanner.nextLine();
                    System.out.print("Dia chi:");
                    String address=scanner.nextLine();
                    System.out.print("Email:");
                    String email=scanner.nextLine();
                    Employee employee=new Employee();
                    employee.setUsername(account);
                    employee.setPassword(password);
                    employee.setName(name);
                    employee.setAddress(address);
                    employee.setEmail(email);
                    boolean check= employeeDao.register(employee);
                    if(check){
                        System.out.println("Dang ky thanh cong");
                    }else {
                        System.out.println("Dang ky that bai");
                    }
                    break;
                case 2:
                    System.out.println("Dang nhap");
                    scanner.nextLine();
                    System.out.print("Tai khoan:");
                    String inputAccount=scanner.nextLine();
                    System.out.print("Mat khau:");
                    String inputPassword=scanner.nextLine();
                    Employee employee1=employeeDao.login(inputAccount,inputPassword);
                    if(employee1!=null){
                        System.out.println("Dang nghap thanh cong");
                        System.out.println("Ten:"+employee1.getName());
                        System.out.println("Dia chi:"+employee1.getAddress());
                        System.out.println("Email:"+employee1.getEmail());
                        System.out.println("Ngay tao:"+employee1.getCreateDate());
                    }else {
                        System.out.println("Dang nhap that bai");
                    }
                    break;
                case 3:
                    System.out.println("Thực hiện chức năng 3");
                    break;
                default:
                    System.out.println("Tạm biệt");
                    cont = false;
                    break;
            }
        } while (cont);
    }
}
