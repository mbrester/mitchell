/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Brester
 */
@Entity
@Table(name = "magic_suply")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MagicSuply.findAll", query = "SELECT m FROM MagicSuply m"),
    @NamedQuery(name = "MagicSuply.findByProductId", query = "SELECT m FROM MagicSuply m WHERE m.productId = :productId"),
    @NamedQuery(name = "MagicSuply.findByProductPrice", query = "SELECT m FROM MagicSuply m WHERE m.productPrice = :productPrice"),
    @NamedQuery(name = "MagicSuply.findByProductName", query = "SELECT m FROM MagicSuply m WHERE m.productName = :productName"),
    @NamedQuery(name = "MagicSuply.findByProductDescription", query = "SELECT m FROM MagicSuply m WHERE m.productDescription = :productDescription"),
    @NamedQuery(name = "MagicSuply.findByProductImageUrl", query = "SELECT m FROM MagicSuply m WHERE m.productImageUrl = :productImageUrl")})
public class MagicSuply implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "product_price")
    private double productPrice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_name")
    private String productName;
    @Size(max = 45)
    @Column(name = "product_description")
    private String productDescription;
    @Size(max = 45)
    @Column(name = "product_image_url")
    private String productImageUrl;
    @JoinColumn(name = "manufature_id", referencedColumnName = "manufacture_id")
    @ManyToOne
    private Manufacture manufatureId;

    public MagicSuply() {
    }

    public MagicSuply(Integer productId) {
        this.productId = productId;
    }

    public MagicSuply(Integer productId, double productPrice, String productName) {
        this.productId = productId;
        this.productPrice = productPrice;
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public Manufacture getManufatureId() {
        return manufatureId;
    }

    public void setManufatureId(Manufacture manufatureId) {
        this.manufatureId = manufatureId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MagicSuply)) {
            return false;
        }
        MagicSuply other = (MagicSuply) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply[ productId=" + productId + " ]";
    }
    
}
