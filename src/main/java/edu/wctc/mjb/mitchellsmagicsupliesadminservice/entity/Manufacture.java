/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Brester
 */
@Entity
@Table(name = "manufacture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufacture.findAll", query = "SELECT m FROM Manufacture m"),
    @NamedQuery(name = "Manufacture.findByManufactureId", query = "SELECT m FROM Manufacture m WHERE m.manufactureId = :manufactureId"),
    @NamedQuery(name = "Manufacture.findByName", query = "SELECT m FROM Manufacture m WHERE m.name = :name"),
    @NamedQuery(name = "Manufacture.findByCity", query = "SELECT m FROM Manufacture m WHERE m.city = :city")})
public class Manufacture implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "manufacture_id")
    private Integer manufactureId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "city")
    private String city;
    @OneToMany(mappedBy = "manufatureId")
    private Collection<MagicSuply> magicSuplyCollection;

    public Manufacture() {
    }

    public Manufacture(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public Integer getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(Integer manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<MagicSuply> getMagicSuplyCollection() {
        return magicSuplyCollection;
    }

    public void setMagicSuplyCollection(Collection<MagicSuply> magicSuplyCollection) {
        this.magicSuplyCollection = magicSuplyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (manufactureId != null ? manufactureId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacture)) {
            return false;
        }
        Manufacture other = (Manufacture) object;
        if ((this.manufactureId == null && other.manufactureId != null) || (this.manufactureId != null && !this.manufactureId.equals(other.manufactureId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture[ manufactureId=" + manufactureId + " ]";
    }
    
}
