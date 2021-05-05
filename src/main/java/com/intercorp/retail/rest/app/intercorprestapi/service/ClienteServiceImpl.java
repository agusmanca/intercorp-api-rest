package com.intercorp.retail.rest.app.intercorprestapi.service;

import com.intercorp.retail.rest.app.intercorprestapi.dao.IClienteDao;
import com.intercorp.retail.rest.app.intercorprestapi.dto.ClienteDto;
import com.intercorp.retail.rest.app.intercorprestapi.dto.PromedioDto;
import com.intercorp.retail.rest.app.intercorprestapi.entity.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    IClienteDao clienteDao;

    @Autowired
    DesvioServiceImpl calculoDesvio;

    @Autowired
    FechaMuerteServiceImpl fechaMuerteService;

    ModelMapper mapeador = new ModelMapper();

    @Transactional(readOnly = true)
    public List<ClienteDto> getClienteList() {

        List<Cliente> clientes = clienteDao.findAll();

        if(Objects.isNull(clientes)){ return null; }

        List<ClienteDto> listaClientes = clientes.stream()
                                           .map( cli -> mapeador.map(cli, ClienteDto.class))
                                           .collect(Collectors.toList());

        listaClientes.forEach(c -> c.setFechaMuerte(fechaMuerteService.getFechaMuerte(c.getFechaNacimiento())));

        return listaClientes;
    }

    @Transactional(readOnly = true)
    public PromedioDto getPromedios(){
        PromedioDto promedioDto = new PromedioDto();

        Optional<Double> avgEdades = Optional.of(clienteDao.findAvgEdad());
        Optional<List<Integer>> edades = Optional.of(clienteDao.findEdades());

        if(avgEdades.isPresent()){
            promedioDto.setPromedio(avgEdades.get());
        }

        if(edades.isPresent()){
            promedioDto.setDesvio(calculoDesvio.getDesvio(edades.get()));
        }

        return promedioDto;
    }

    @Transactional()
    public ClienteDto createCliente(ClienteDto request){
        Cliente clienteNuevo = clienteDao.save(mapeador.map(request, Cliente.class));
        return mapeador.map(clienteNuevo, ClienteDto.class);
    }

    @Transactional()
    public ClienteDto updateCliente(ClienteDto request){
        if(!clienteDao.findById(request.getId()).isPresent()){
            return null;
        }

        Cliente findedCliente = clienteDao.findById(request.getId()).get();
        findedCliente = mapeador.map(request, Cliente.class);

        Cliente clienteNuevo = clienteDao.save(findedCliente);
        return mapeador.map(clienteNuevo, ClienteDto.class);
    }

}
