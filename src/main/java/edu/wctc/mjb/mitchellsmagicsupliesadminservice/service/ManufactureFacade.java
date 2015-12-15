/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.service;

import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Brester
 */
@Stateless
public class ManufactureFacade extends AbstractFacade<Manufacture> {
    @PersistenceContext(unitName = "magic_PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManufactureFacade() {
        super(Manufacture.class);
    }
    
}
