/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.model;

import java.util.List;

/**
 *
 * @author Brester
 */
public class MagicSuplyService {
    DaoStrategy dao;

    public  MagicSuplyService(MagicSupliesDaoStrategy magicDao) {
        dao = magicDao;
    }

    public final MagicSuply getSuplyById(String magicSuplyId) throws Exception {
        if(magicSuplyId == null || magicSuplyId.equals("")){
            throw new Exception();
        }
        return dao.getMagicSuplyById(Integer.parseInt(magicSuplyId));
    }
    
    public final void updateRecord(String productName, int productId, String productDescription, double productUnitPrice, String productImageUrl) throws Exception {
        if(productId < 0 || productName == null || productName.equals("") || productDescription == null || productDescription.equals("") || productUnitPrice < 0 || productImageUrl == null){
            throw new Exception();
        }
        MagicSuply magicSuply = new MagicSuply();
        magicSuply.setProductId(productId);
        magicSuply.setProductDescription(productDescription);
        magicSuply.setProductImageUrl(productImageUrl);
        magicSuply.setProductName(productName);
        magicSuply.setProductPrice(productUnitPrice);
        
       dao.updateRecord(magicSuply, productId);
    }

 

    public final void createNewMagicSuply(String productName, String productDescription, double productUnitPrice, String productImageUrl) throws Exception {
        if(productName == null || productName.equals("") || productDescription == null || productDescription.equals("") || productUnitPrice < 0 || productImageUrl == null){
            throw new Exception();
        }
        MagicSuply magicSuply = new MagicSuply();
         magicSuply.setProductDescription(productDescription);
        magicSuply.setProductImageUrl(productImageUrl);
        magicSuply.setProductName(productName);
        magicSuply.setProductPrice(productUnitPrice);
        dao.createNewRecord(magicSuply);
    }

    public  final void deleteRecordByID(int productId) throws Exception {
        if(productId < 0){
            throw new IllegalArgumentException();
        }
        dao.deleteMagicSuplyByID(productId);
    }

    public final List<MagicSuply> getAllSuplies() throws Exception {
        
        return dao.getAllMagicSuplies();
    }

    public final DaoStrategy getDao() {
        
        return dao;
    }

    public final void setDao(DaoStrategy dao) {
        if(dao == null){
            throw new IllegalArgumentException();
        }
        this.dao = dao;
    }

    @Override
    public String toString() {
        return "MagicSuplyService{" + "dao=" + dao + '}';
    }
    
    public  static void main(String[] args) throws Exception {
        
        MagicSuplyService s = new MagicSuplyService(new MagicSupliesDaoStrategy(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/magic", "root", "password"));
         System.out.println(s.getSuplyById("3"));
    }
    
}
