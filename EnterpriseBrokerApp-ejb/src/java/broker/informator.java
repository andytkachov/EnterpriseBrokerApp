package broker;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
// xxx
public class informator implements informatorInterfaceRemote {
    
    private InitialContext ic;
    
    
    @Override
    public List<sale> getSalesList() {
        List<sale> slsList = new ArrayList<>();
        try {
            ic = new InitialContext();
            Connection con = MyConnection.getConnection();
            Statement statement = con.createStatement();
            String queryString = "select " +
            "brokerLevels.id," +//1
            "brokerLevels.description," +//2
            "brokers.id," +//3
            "brokers.firstname," +//4
            "brokers.lastname," +//5
            "brokers.birthDay," +//6
            "brokers.sex," +//7
            "stocks.id," +//8
            "stocks.name_," +//9
            "stocks.code," +//10
            "sales.id," +//11
            "sales.price," +//12
            "sales.amount " +//13
            "from sales " +
            "inner join brokers on sales.broker_id = brokers.id " +
            "inner join stocks on sales.stock_id = stocks.id " +
            "inner join brokerLevels on brokers.level_id = brokerLevels.id";
            ResultSet results = statement.executeQuery(queryString);
            
            sale _sale;
            broker _broker;
            stock _stock;
            brokerLevel _level;
            
            while(results.next()){
                
                _sale = new sale();
                _broker = new broker();
                _level = new brokerLevel();
                _stock = new stock();
                
                _level.setId((Integer)results.getObject(1));
                _level.setDescription((String)results.getObject(2));
                _broker.setGetBrokerLevel(_level);
                
                
                _broker.setId((Integer)results.getObject(3));
                _broker.setFirstname((String)results.getObject(4));
                _broker.setLastname((String)results.getObject(5));
                _broker.setBirthDay((Date)results.getObject(6));
                _broker.setSex((Boolean)results.getObject(7));
                _sale.setBroker(_broker);
                
                _stock.setId((Integer)results.getObject(8));
                _stock.setName((String)results.getObject(9));
                _stock.setCode((String)results.getObject(10));
                _sale.setStock(_stock);
                
                _sale.setId((Integer)results.getObject(11));
                _sale.setPrice(new Double(((BigDecimal)results.getObject(12)).floatValue()));
                _sale.setAmount((Integer)results.getObject(13));
                slsList.add(_sale);
            }
            con.close();
            return slsList;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(informator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    @Override
    public List<stock> getStockList() {
        List<stock> stocksList = new ArrayList<>();
        try {
            ic = new InitialContext();
            try (Connection con = MyConnection.getConnection()) {
                Statement statement = con.createStatement();
                String queryString = "select stocks.id, stocks.name_, stocks.code from stocks";
                ResultSet results = statement.executeQuery(queryString);
                stock _stock;
                while(results.next()){
                    _stock = new stock();
                    _stock.setId((Integer)results.getObject(1));
                    _stock.setName((String)results.getObject(2));
                    _stock.setCode((String)results.getObject(3));
                    stocksList.add(_stock);
                }
            }
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(informator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stocksList;
    }

    @Override
    public List<sale> getSalesListDateID(Date date, Integer stockID) {
        SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");        
         List<sale> slsList = new ArrayList<>();
         String dateFrom;
         String dateTo;
         String where = "";
        if(date != null && stockID != null){
            Date to = new Date(date.getTime() + 1000*60*60*24);
            dateFrom = sdfr.format(date);
            dateTo = sdfr.format(to);
            where = " where stocks.id = " + stockID.toString() + " and sales.date_ >= '" + dateFrom + "' and "
                    + " sales.date_ < '" + dateTo + "' "; 
        }
        try {
            ic = new InitialContext();
            Connection con = MyConnection.getConnection();
            Statement statement = con.createStatement();
            String queryString = "select " +
            "brokerLevels.id," +//1
            "brokerLevels.description," +//2
            "brokers.id," +//3
            "brokers.firstname," +//4
            "brokers.lastname," +//5
            "brokers.birthDay," +//6
            "brokers.sex," +//7
            "stocks.id," +//8
            "stocks.name_," +//9
            "stocks.code," +//10
            "sales.id," +//11
            "sales.price," +//12
            "sales.amount " +//13
            "from sales " +
            "inner join brokers on sales.broker_id = brokers.id " +
            "inner join stocks on sales.stock_id = stocks.id " +
            "inner join brokerLevels on brokers.level_id = brokerLevels.id" + where + 
                    " order by sales.date_ DESC ";
            ResultSet results = statement.executeQuery(queryString);
            
            sale _sale;
            broker _broker;
            stock _stock;
            brokerLevel _level;
            
            while(results.next()){
                
                _sale = new sale();
                _broker = new broker();
                _level = new brokerLevel();
                _stock = new stock();
                
                _level.setId((Integer)results.getObject(1));
                _level.setDescription((String)results.getObject(2));
                _broker.setGetBrokerLevel(_level);
                
                
                _broker.setId((Integer)results.getObject(3));
                _broker.setFirstname((String)results.getObject(4));
                _broker.setLastname((String)results.getObject(5));
                _broker.setBirthDay((Date)results.getObject(6));
                _broker.setSex((Boolean)results.getObject(7));
                _sale.setBroker(_broker);
                
                _stock.setId((Integer)results.getObject(8));
                _stock.setName((String)results.getObject(9));
                _stock.setCode((String)results.getObject(10));
                _sale.setStock(_stock);
                
                _sale.setId((Integer)results.getObject(11));
                _sale.setPrice(new Double(((BigDecimal)results.getObject(12)).floatValue()));
                _sale.setAmount((Integer)results.getObject(13));
                slsList.add(_sale);
            }
            con.close();
            return slsList;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(informator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
