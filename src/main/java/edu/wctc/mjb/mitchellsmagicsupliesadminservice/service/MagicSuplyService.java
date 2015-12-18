/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.service;

import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply;
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

 * @author brester
 */
@Repository("magicSuplyService")
@Transactional(readOnly = true)
public class MagicSuplyService {

    private transient final Logger LOG = LoggerFactory.getLogger(MagicSuplyService.class);

    @Inject
    private MagicSuplyRepository magicRepo;

    @Inject
    private ManufactureRepository manRepo;

    public MagicSuplyService() {
    }

    public List<MagicSuply> findAll() {
        return magicRepo.findAll();
    }

    public MagicSuply findById(String id) {
        return magicRepo.findOne(new Integer(id));
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void remove(MagicSuply suply) {
        LOG.debug("Deleting author: " + suply.getProductName());
        magicRepo.delete(suply);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MagicSuply edit(MagicSuply suply) {
        return magicRepo.saveAndFlush(suply);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public MagicSuply create(MagicSuply suply) {
        return magicRepo.saveAndFlush(suply);
    }
}
