package com.intercorp.retail.rest.app.intercorprestapi.controller;

import com.intercorp.retail.rest.app.intercorprestapi.dto.ClienteDto;
import com.intercorp.retail.rest.app.intercorprestapi.dto.PromedioDto;
import com.intercorp.retail.rest.app.intercorprestapi.service.IClienteService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/cliente")
@Api(tags = "Cliente Controller", value = "Operaciones para la busqueda y creación de clientes")
public class ClienteController {

    @Autowired
    IClienteService clienteService;

    @GetMapping("/listclientes")
    @ApiOperation(nickname = "Lista de Clientes", value = "Obtener la lista de clientes", notes = "Permite obtener una lista de todos los clientes registrados en la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessful", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<?> getClientList() {
        List<ClienteDto> clientes = clienteService.getClienteList();

        if(Objects.isNull(clientes)){
            return new ResponseEntity<>("No se encontraron resultados para su busqueda", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/kpideclientes")
    @ApiOperation(nickname = "Valores promedio", value = "Obtener la media y el desvio estandar", notes = "Permite calcular la media entre las edades de los usuarios y obtener también su desvio estandar.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessful", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<?> getEdadPromedio() {
        PromedioDto promedio = clienteService.getPromedios();
        if(Objects.isNull(promedio.getPromedio()) && Objects.isNull(promedio.getDesvio())){
            return new ResponseEntity<>("No se encontraron resultados para su busqueda", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(promedio, HttpStatus.OK);
    }

    @PostMapping("/creacliente")
    @ApiOperation(nickname = "Crear usuario", value = "Cree un nuevo usuario", notes = "Permite registrar un nuevo usuario en la base.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Sucessful", response = ResponseEntity.class),
            @ApiResponse(code = 404, message = "Not Found", response = ResponseEntity.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ResponseEntity.class)
    })
    public ResponseEntity<?> crearCliente(@ApiParam(value = "Payload del nuevo usuario.") @Valid @RequestBody ClienteDto request) {
        ClienteDto cliente;

        if(Objects.isNull(request.getId())) {
            cliente = clienteService.createCliente(request);
        } else {
            cliente = clienteService.updateCliente(request);
        }

        if(Objects.isNull(cliente)){
            return new ResponseEntity<>("No pudo ejecutarse su solicitud.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
