package dao;

import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    public boolean register(Employee emp){
        boolean check=false;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="insert into employees(name,address,email,username,password,create_date) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,emp.getName());
            ps.setString(2,emp.getAddress());
            ps.setString(3,emp.getEmail());
            ps.setString(4,emp.getUsername());
            ps.setString(5,emp.getPassword());
            long time=System.currentTimeMillis();
            Timestamp timestamp=new Timestamp(time);
            ps.setTimestamp(6,timestamp);
            int rs=ps.executeUpdate();
            if(rs>0){
                check=true;
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
    public boolean checkExistAccount(String account){
        boolean check=false;
        List<String> accounts=new ArrayList<>();
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select username from employees";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            while (rs.next()){
                String acc=rs.getString("username");
                accounts.add(acc);
            }
            for (String acc:accounts) {
                if(acc.equals(account)){
                    check=true;
                    break;
                }
            }
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return check;
    }
    public Employee login(String account,String password){
        Employee employee=null;
        Connection con=JDBCConnection.getJDBCConnection();
        String sql="select * from employees where username=? and password=?";
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setString(1,account);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                employee=new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAddress(rs.getString("address"));
                employee.setEmail(rs.getString("email"));
                employee.setUsername(account);
                employee.setCreateDate(rs.getTimestamp("create_date"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }
}
