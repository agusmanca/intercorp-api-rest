package com.intercorp.retail.rest.app.intercorprestapi.dao;

import com.intercorp.retail.rest.app.intercorprestapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDao extends JpaRepository<Cliente, Long> {
    @Query("SELECT avg(c.edad) FROM Cliente c")
    Double findAvgEdad();

    @Query("SELECT c.edad FROM Cliente c")
    List<Integer> findEdades();
}
