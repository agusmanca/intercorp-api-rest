package com.intercorp.retail.rest.app.intercorprestapi.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesvioServiceImpl {
    public Double getDesvio(List<Integer> edades){
        double media = edades.stream().mapToDouble(e -> e).average().orElse(0.0);
        double varianza = edades.stream()
                                .filter(f -> media > 0)
                                .map(e -> Math.pow(e - media, 2))
                                .mapToDouble(e -> e).sum() / (edades.size() - 1);

        return Math.round(Math.sqrt(varianza) * 100.0) / 100.0;
    }
}
