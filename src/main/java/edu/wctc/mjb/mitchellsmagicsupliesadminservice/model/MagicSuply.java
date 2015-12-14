/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.model;

/**
 *
 * @author Brester
 */
public class MagicSuply {
    private int productId;
    private double productPrice;
    private String productName;
    private String productImageUrl;
    private String productDescription;

    public MagicSuply() {
    }

    public MagicSuply(int productSuplyId, double productUnitPrice, String productName, String productImageUrl, String productDescription) {
        this.productId = productSuplyId;
        this.productPrice = productUnitPrice;
        this.productName = productName;
        this.productImageUrl = productImageUrl;
        this.productDescription = productDescription;
    }

    public final int getProductId() {
        return productId;
    }


    public final void setProductId(int productSuplyId) {
        
        this.productId = productSuplyId;
    }

    public final double getProductPrice() {
        return productPrice;
    }

    public final void setProductPrice(double productUnitPrice) {
        if(productUnitPrice <0){
            throw new IllegalArgumentException();
        }
        this.productPrice = productUnitPrice;
    }

    public final String getProductName() {
        return productName;
    }

    public final void setProductName(String productName) {
        if(productName == null || productName.equals("")){
            throw new IllegalArgumentException();
        }
        this.productName = productName;
    }

    public final String getProductImageUrl() {
        return productImageUrl;
    }
//Can be null or empty string, no validation needed for now
    public final void setProductImageUrl(String productImageUrl) {
        if(productImageUrl == null){
            productImageUrl = "N/A";
        }
        this.productImageUrl = productImageUrl;
    }

    public final String getProductDescription() {
        return productDescription;
    }

    public final void setProductDescription(String productDescription) {
        if(productDescription == null){
            productDescription = "N/A";
        }
        this.productDescription = productDescription;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.productId;
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MagicSuply other = (MagicSuply) obj;
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "MagicSuply{" + "productId=" + productId + ", productUnitPrice=" + productPrice + ", productName=" + productName + ", productImageUrl=" + productImageUrl + ", productDescription=" + productDescription + '}';
    }
    
    
    
}
