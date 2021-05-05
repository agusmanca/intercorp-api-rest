package com.intercorp.retail.rest.app.intercorprestapi.service;

import com.intercorp.retail.rest.app.intercorprestapi.dto.ClienteDto;
import com.intercorp.retail.rest.app.intercorprestapi.dto.PromedioDto;

import java.util.List;

public interface IClienteService {
    List<ClienteDto> getClienteList();
    PromedioDto getPromedios();
    ClienteDto createCliente(ClienteDto request);
    ClienteDto updateCliente(ClienteDto request);
}
