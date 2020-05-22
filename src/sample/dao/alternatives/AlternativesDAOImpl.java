package sample.dao.alternatives;

import sample.dao.DAOFactory;
import sample.Organization.AlternativesForMarks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlternativesDAOImpl implements AlternativesDAO {
    public static AlternativesDAOImpl instance = new AlternativesDAOImpl();
    public static synchronized AlternativesDAOImpl getInstance(){ return instance; }
    private AlternativesDAOImpl() { }
    //////////////////////////////////
    private static Connection conn = DAOFactory.getInstance().createConnection();

    @Override
    public void insertAlternative(AlternativesForMarks alternative) {

        ///
        ///
        ///

        int id = 0; // alternative.getId();
        String name = alternative.getName();
        String country = alternative.getCountry();
        String typeOf = alternative.getTypeOf();
        double cost = alternative.getCost();
        String firstEx = alternative.getFirstEx();


        try {

            Statement statement = conn.createStatement();

            statement.executeUpdate("create table if not exists  alternatives "
                    + "(id  MEDIUMINT  not null  auto_increment,"
                    + "name varchar(125) not null default('0'),"
                    + "country varchar(125) not null  default('0'),"
                    + "typeOf varchar(125) not null  default('0'),"
                    + "cost DOUBLE not null  default(0),"
                    + "firstEx varchar(5) not null  default('0'),"

                    + "primary key(id));");

            statement.executeUpdate(" INSERT INTO alternatives" +
                    " ( name, country,typeOf,cost,firstEx)" +
                    " VALUES("
                    //  + "' "+ id  + "' , "
                    + "' "+ name.trim() + "' , "
                    + "' "+ country.trim() + "' , "
                    + "' "+ typeOf.trim() + "' , "
                    + "' "+ cost+ "' , "
                    + "' "+ firstEx.trim() + "' );"
            );

            System.out.println("Info was added");
        }
        catch (SQLException ex){
            ex.printStackTrace();
        }


    }



    @Override
    public void deleteAlternative(int id) {

        System.out.println("Удаляем:  " + id);
        try {

            Statement    statement = conn.createStatement();
            statement.executeUpdate(" DELETE FROM alternatives " +
                    "WHERE id = " + "'" + id + "';");
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println("Удаление заверешно!  " + id);

    }

    @Override
    public ArrayList<AlternativesForMarks> getAlternativeList() {
        ArrayList<AlternativesForMarks> alternatives = new ArrayList<AlternativesForMarks>();
        try {

            if(CheckCountOfObjectInBase("alternatives")>0)
            {
                Statement    statement = conn.createStatement();
                String command = " select * from alternatives;";
                ResultSet rs = statement.executeQuery(command);
                try {

                    while (rs.next()) {

                        AlternativesForMarks alternative = new AlternativesForMarks();
                        alternative.setId(rs.getInt("id"));
                        alternative.setName(rs.getString("name"));
                        alternative.setCountry(rs.getString("country"));
                        alternative.setTypeOf(rs.getString("typeOf"));
                        alternative.setCost(rs.getDouble("cost"));
                        alternative.setFirstEx(rs.getString("firstEx"));
                       /* alternative.setSecondEx(rs.getString("secondEx"));
                        alternative.setThirdEx(rs.getString("thirdEx"));*/

                        alternatives.add(alternative);
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
        return alternatives;
    }

    @Override
    public void update(AlternativesForMarks alternative) {//обновление

        int id = alternative.getId();
        String name = alternative.getName();
        String country = alternative.getCountry();
        String typeOf = alternative.getTypeOf();
        double cost = alternative.getCost();
        String firstEx = alternative.getFirstEx();
       /* String secondEx = alternative.getSecondEx();
        String thirdEx = alternative.getThirdEx();*/

        try {

            Statement statement = conn.createStatement();


            statement.executeUpdate(" UPDATE  alternatives" +
                    " SET  name='" + name.trim()
                    + "', country='" + country.trim()
                    + "',typeOf='" + typeOf.trim() + "', "
                    + "cost= " + cost + " ," +
                    "firstEx='" + firstEx.trim()
                  /*  + "',secondEx='" + secondEx.trim()
                    + "',thirdEx='" + thirdEx.trim() */+ "'" +
                    " WHERE id= " + id + " ;");

            System.out.println("Info was changed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    private static int CheckCountOfObjectInBase(String tableName) {

        String  countOfStrings = " select count(*) from " + tableName;

        int countForReturn = 0;
        try {
            Statement    statement = conn.createStatement();
            ResultSet count;

            count = statement.executeQuery(countOfStrings);
            count.last();
            countForReturn = count.getRow();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return countForReturn;
    }
}
