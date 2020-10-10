package com.daily.sale.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.daily.sale.modelo.Persona;

@Repository
public interface IPersona extends CrudRepository<Persona, Integer>{

	
}
