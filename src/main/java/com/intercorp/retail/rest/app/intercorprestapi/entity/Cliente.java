package com.intercorp.retail.rest.app.intercorprestapi.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PRIVATE)
    @Column(name = "ID", nullable = false, unique = true)
    private Long id;

    @Column(name = "NOMBRE", length = 60)
    private String nombre;

    @Column(name = "APELLIDO", length = 60)
    private String apellido;

    @Column(name = "EDAD")
    private Integer edad;

    @Column(name = "FECHA_NACIMIENTO")
    private Date fechaNacimiento;
}
