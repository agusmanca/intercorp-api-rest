package com.intercorp.retail.rest.app.intercorprestapi.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class ClienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer edad;
    private Date fechaNacimiento;
    private Date fechaMuerte;
}
