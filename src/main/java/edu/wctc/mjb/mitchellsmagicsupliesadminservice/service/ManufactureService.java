/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.service;

import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.repository.MagicSuplyRepository;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.repository.ManufactureRepository;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Brester
 */
@Repository("manufactureService")
@Transactional(readOnly = true)
public class ManufactureService {

    private transient final Logger LOG = LoggerFactory.getLogger(MagicSuplyService.class);

    @Inject
    private MagicSuplyRepository magicRepo;

    @Inject
    private ManufactureRepository manRepo;

    public ManufactureService() {
    }

    public List<Manufacture> findAll() {
        return manRepo.findAll();
    }

    public Manufacture findById(String id) {
        return manRepo.findOne(new Integer(id));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(Manufacture man) {
        LOG.debug("Deleting author: " + man.getName());
        manRepo.delete(man);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Manufacture edit(Manufacture man) {
        return manRepo.saveAndFlush(man);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Manufacture create(Manufacture man) {
        return manRepo.saveAndFlush(man);
    }
}