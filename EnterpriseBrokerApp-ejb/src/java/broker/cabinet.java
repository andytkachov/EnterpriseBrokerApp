package broker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;

@Stateful
public class cabinet implements cabinetInterfaceRemote {
    private brokerLevel _level = new brokerLevel();
    private broker _broker = new broker();
    private Connection con;
    
    public cabinet(){
        con = getConnection();        
    }
   
    @Override
    public Boolean saveSale(sale Sale) {
        if(con != null){
            SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd h:m:s");
            
            try {
                Statement statement = con.createStatement();
                String queryString = "insert into sales "
                        + "(broker_id,stock_id,price,amount,date_) values "
                        + "("+ Sale.getBroker().getId()+ ","
                        + ""+ Sale.getStock().getId()+ ","
                        + ""+ Sale.getPrice()+ ","
                        + ""+ Sale.getAmount()+ ","
                        + "'"+ sdfr.format(new Date()).toString() + "');";
                statement.executeUpdate(queryString);
                
            } catch (SQLException ex) {
                Logger.getLogger(cabinet.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }        
            
        }
        return true;
    }

    @Override
    public broker getBroker() {        
        return _broker;
    }
    
    private Connection getConnection(){
        try {
            Connection con = MyConnection.getConnection();
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(cabinet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private void _fillBroker(Integer broker_id){
        if(con != null){
            try {
                Statement statement = con.createStatement();
                String queryString = "select "
                        + "brokerLevels.id,"
                        + "brokerLevels.description, "
                        + "brokers.id, brokers.firstname, "
                        + "brokers.lastname, brokers.birthDay, "
                        + "brokers.sex, "
                        + "brokers.level_id, "
                        + "brokers.user_id "
                        + "from brokers inner "
                        + "join brokerLevels on brokers.level_id = brokerLevels.id "
                        + "where brokers.user_id = " + broker_id.toString();
                ResultSet results = statement.executeQuery(queryString);
                if(results.next()){
                    _level.setId((Integer)results.getObject(1));
                    _level.setDescription((String)results.getObject(2));

                    _broker.setId((Integer)results.getObject(3));
                    _broker.setFirstname((String)results.getObject(4));
                    _broker.setLastname((String)results.getObject(5));
                    _broker.setBirthDay((Date)results.getObject(6));
                    _broker.setSex((Boolean)results.getObject(7));
                }

            } catch (SQLException ex) {
                Logger.getLogger(cabinet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public Boolean CheckLoginPassword(String login, String password) {
        if(con != null){
            try {
                Statement statement = con.createStatement();
                String queryString = "select users.id "
                        + "from users "
                        + "where users.login = '"+ login 
                        + "' and users.password = md5( '" + password + "')";
                ResultSet results = statement.executeQuery(queryString);
                if(!results.next())
                    return false;
                else{
                    _fillBroker((Integer)results.getObject(1));
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(cabinet.class.getName()).log(Level.SEVERE, null, ex);
            }        
            return false;
        }
        return false;
    }    
}
