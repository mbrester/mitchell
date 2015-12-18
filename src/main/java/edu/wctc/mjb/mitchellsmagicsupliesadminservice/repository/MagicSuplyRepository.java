/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.mjb.mitchellsmagicsupliesadminservice.repository;

import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.MagicSuply;
import edu.wctc.mjb.mitchellsmagicsupliesadminservice.entity.Manufacture;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Brester
 */
public interface MagicSuplyRepository extends JpaRepository<MagicSuply, Integer>, Serializable{

    public MagicSuply saveAndFlush(MagicSuply man);
    
}
