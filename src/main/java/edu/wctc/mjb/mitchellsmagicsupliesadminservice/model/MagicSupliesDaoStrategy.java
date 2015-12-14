package edu.wctc.mjb.mitchellsmagicsupliesadminservice.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brester
 */
public class MagicSupliesDaoStrategy implements DaoStrategy {
   private DbStrategy db;
   private String driverClass;
   private String url; 
   private String userName;
   private String password;

    public MagicSupliesDaoStrategy(DbStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
   
    @Override
    
    public final List<MagicSuply> getAllMagicSuplies() throws Exception{
        db.openConnection(driverClass, url, userName, password);
        
        List<Map<String,Object>> rawData = db.findAllRecords("magic_suply");
        List<MagicSuply> magicSuplies = new ArrayList<>();
        
        for(Map rawRec : rawData){
            MagicSuply magicSuply = new MagicSuply();
            
            Object obj = rawRec.get("product_id");
            magicSuply.setProductId(Integer.parseInt( (obj== null) ? "": obj.toString()));
            
            obj = rawRec.get("product_price");
            magicSuply.setProductPrice(Double.parseDouble((obj== null) ? "": obj.toString()));
            
            obj = rawRec.get("product_description");
            magicSuply.setProductDescription((obj== null) ? "": obj.toString());
            obj = rawRec.get("product_image_url");
            magicSuply.setProductImageUrl((obj== null) ? "": obj.toString());
            obj = rawRec.get("product_name");
            magicSuply.setProductName((obj== null) ? "": obj.toString());
            
            magicSuplies.add(magicSuply);
        }
        db.closeConnection();
        return magicSuplies;
    }
  
    @Override
    
    public final void deleteMagicSuplyByID(Object productId) throws Exception{
        if(productId == null){
            throw new Exception();
        }
         db.openConnection(driverClass, url, userName, password);
         db.deleteRecord("magic_suply","product_id",productId);
         db.closeConnection();
        
    }
   
    @Override
   
    public final void createNewRecord(MagicSuply newMagicSuply) throws Exception{
        if(newMagicSuply == null ){
            throw new IllegalArgumentException();
        }
        db.openConnection(driverClass, url, userName, password);
      Map<String, Object> newData = new HashMap<>();
        
        newData.put("product_name", newMagicSuply.getProductName());
        newData.put("product_price", newMagicSuply.getProductPrice());
        newData.put("product_description", newMagicSuply.getProductDescription());
        newData.put("product_image_url", newMagicSuply.getProductImageUrl());
        
        db.createNewRecord(newData, "magic_suply");
        db.closeConnection();
    }
   
    @Override
    
    public final void updateRecord(MagicSuply newMagicSuply, Object pkValue) throws Exception
    {
            if(newMagicSuply == null || pkValue == null){
                    throw new IllegalArgumentException();
                    }
        db.openConnection(driverClass, url, userName, password);
        Map<String, Object> newData = new HashMap<>();
        
        newData.put("product_name", newMagicSuply.getProductName());
        newData.put("product_price", newMagicSuply.getProductPrice());
        newData.put("product_description", newMagicSuply.getProductDescription());
        newData.put("product_image_url", newMagicSuply.getProductImageUrl());
        
        
        db.updateRecord(newData, "magic_suply","product_id",pkValue);
        db.closeConnection();
    }

    @Override
    public final DbStrategy getDb() {
        return db;
    }

    @Override
    //needs validation
    public final void setDb(DbStrategy db) {
        if(db == null){
            throw new IllegalArgumentException();
        }
        this.db = db;
    }

    @Override
    public final String getDriverClass() {
        return driverClass;
    }

    @Override
    
    public final void setDriverClass(String driverClass) {
        if(driverClass == null || driverClass.equals("")){
            throw new IllegalArgumentException();
        }
        this.driverClass = driverClass;
    }

    @Override
    public final String getUrl() {
        
        return url;
    }

    @Override
   
    public final void setUrl(String url) {
      if(url == null || url.equals("")) {
          throw new IllegalArgumentException();
      } 
        this.url = url;
    }

    @Override
    public final String getUserName() {
        return userName;
    }

    @Override
    
    public final void setUserName(String userName) {
        if(userName == null || userName.equals("")){
            throw new IllegalArgumentException();
        }
        this.userName = userName;
    }

    @Override
    public final String getPassword() {
        return password;
    }

    @Override

    public final void setPassword(String password) {
        if(password == null || password.equals("")){
            throw new IllegalArgumentException();
        }
        this.password = password;
    }

    @Override
    
    public final MagicSuply getMagicSuplyById(int productId)throws Exception {
        if(productId < 0){
            throw new IllegalArgumentException();
        }
        db.openConnection(driverClass, url, userName, password);
        Map rawData = db.findById("magic_suply","product_id", productId);
        MagicSuply magicSuply = new MagicSuply();
            
            Object obj = rawData.get("product_id");
            magicSuply.setProductId(Integer.parseInt( (obj== null) ? "": obj.toString()));
            
            obj = rawData.get("product_price");
            magicSuply.setProductPrice(Double.parseDouble( (obj== null) ? "": obj.toString()));
            
            obj = rawData.get("product_description");
            magicSuply.setProductDescription((obj== null) ? "": obj.toString());
            obj = rawData.get("product_image_url");
            magicSuply.setProductImageUrl((obj== null) ? "": obj.toString());
            obj = rawData.get("product_name");
            magicSuply.setProductName((obj== null) ? "": obj.toString());
        db.closeConnection();
        return magicSuply;
                
    }

    @Override
    public String toString() {
        return "MagicSupliesDaoStrategy{" + "db=" + db + ", driverClass=" + driverClass + ", url=" + url + ", userName=" + userName + ", password=" + password + '}';
    }
    
   public static void main(String[] args) throws Exception {
      // DbStrategy db, String driverClass, String url, String userName, String password
        MagicSupliesDaoStrategy m = new MagicSupliesDaoStrategy(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/magic", "root", "password");
      List<MagicSuply> t = m.getAllMagicSuplies();
       for(MagicSuply c :t){
           System.out.println(c);   
    }
    }
}
