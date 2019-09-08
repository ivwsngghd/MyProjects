package com.imooc.interview.jdbc;

import java.sql.*;

public class StatementSample {
    //使用Statement
    public void findByEname1(String ename) {
        String driverName = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://127.0.0.1:3306/scott";
        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(URL, username, password);
            Statement ps = conn.createStatement();
            //使用Statement
            String sql = "SELECT * FROM emp where ename = '" + ename + "' ";
            System.out.println(sql);
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("ename") + "," +
                        rs.getString("job") + "," +
                        rs.getFloat("sal"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5.关闭连接
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //使用预编译PreparedStatement
    public void findByEname2(String ename) {
        String driverName = "com.mysql.jdbc.Driver";
        String URL = "jdbc:mysql://127.0.0.1:3306/scott";

        String username = "root";
        String password = "root";
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(URL, username, password);

            //使用预编译PreparedStatement
            String sql = "SELECT * FROM emp where ename = ?";
            System.out.println(sql);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ename);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("ename") + "," +
                        rs.getString("job") + "," +
                        rs.getFloat("sal"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //5.关闭连接
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        StatementSample sample = new StatementSample();
        sample.findByEname1("SMITH' or 1=1 or '");
        sample.findByEname2("SMITH' or 1=1 or '");
    }
}
