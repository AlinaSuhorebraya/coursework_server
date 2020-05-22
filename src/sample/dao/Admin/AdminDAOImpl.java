package sample.dao.Admin;

import sample.dao.DAOFactory;
import sample.Organization.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminDAOImpl implements AdminDAO{
    public static final AdminDAOImpl instance = new AdminDAOImpl();
    public static synchronized AdminDAOImpl getInstance(){ return instance; }
    private AdminDAOImpl() { }
    //////////////////////////////////
    private static Connection conn = DAOFactory.getInstance().createConnection();

    @Override
    public void insertAdmin(Admin admin) {

        ///
        ///
        //
        String login = admin.getLogin();
        String password = admin.getPassword();
        String name = admin.getName();
        String surname = admin.getSurname();

        try {

            Statement statement = conn.createStatement();//utverzdenie soed


            //тту добавление админа в базу
            statement.executeUpdate("create table if not exists  admin "
                    + "(id MEDIUMINT  not null  auto_increment,"
                    + "login varchar(125) not null default('0'),"
                    + "password varchar(125) not null default('0'),"
                    + "name varchar(125) not null default('0'),"
                    + "surname varchar(125) not null default('0'),"
                    + "primary key(id));");

            statement.executeUpdate(" INSERT INTO admin (login, password, name, surname) VALUES("
                    + "' "+ login.trim()  + "' , "
                    + "' "+ password.trim() + "' , "
                    + "' "+ name.trim() + "' , "
                    + "' "+ surname.trim() + "'); ");
            System.out.println("Info was added");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }

    }


    @Override
    public void deleteAdmin(Admin admin) {
        String login = admin.getLogin();

        try {
            Statement    statement = conn.createStatement();
            statement.executeUpdate(" DELETE FROM admin " +
                    "WHERE login = " + "' " + login.trim() + "' ;");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Удаление заверешно!  " + login);

    }


    public ArrayList<Admin> getAdminList() {
        ArrayList<Admin> adminList = new ArrayList<Admin>();
        try {

            if(CheckCountOfObjectInBase("admin")>0)
            {
                Statement    statement = conn.createStatement();
                String command = " select * from admin;";
                ResultSet rs = statement.executeQuery(command);//v rs vce s bd
                try {

                    //  System.out.println("Count in table:  " + CheckCountOfObjectInBase("admin"));

                    while (rs.next()) {

                        Admin admin = new Admin();

                        admin.setLogin(rs.getString("login"));
                        admin.setPassword(rs.getString("password"));
                        admin.setName(rs.getString("name"));
                        admin.setSurname(rs.getString("surname"));
                        adminList.add(admin);
                    }
                }
                catch(SQLException ex){
                    ex.printStackTrace();
                }
                finally {
                    rs.close();
                }
            }


        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return adminList;
    }

    private static int CheckCountOfObjectInBase(String tableName) {

        String  countOfStrings = " select count(*) from " + tableName;

        int countForReturn = 0;
        try {
            Statement  statement = conn.createStatement();
            ResultSet count;

            count = statement.executeQuery(countOfStrings);
            count.last();//Перемещает указатель на крайний ряд
            countForReturn = count.getRow();//Возвращает номер ряда, на который в данный момент указывает курсор.

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return countForReturn;
    }


}
