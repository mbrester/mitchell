/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.model;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Brester
 */
public interface DaoStrategy {

    public void createNewRecord(MagicSuply newMagicSuply) throws Exception;

   public  void deleteMagicSuplyByID(Object productId) throws Exception;

    public List<MagicSuply> getAllMagicSuplies() throws Exception;

    public DbStrategy getDb();

    public String getDriverClass();

    public String getPassword();

    public String getUrl();

    public String getUserName();

    public void setDb(DbStrategy db);

    public void setDriverClass(String driverClass);

    public void setPassword(String password);

    public void setUrl(String url);

    public void setUserName(String userName);

    public void updateRecord(MagicSuply newMagicSuply, Object pkValue) throws Exception;
    
    public MagicSuply getMagicSuplyById(int productId) throws Exception;
    
}
