package com.intercorp.retail.rest.app.intercorprestapi.service;

import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;
import static java.time.temporal.ChronoUnit.*;

@Service
public class FechaMuerteServiceImpl {

    public Date getFechaMuerte(Date fechaNacimiento)  {
        Random r = new Random();
        long promedioMuerte = r.nextInt(88-70)+70;
        long mes = 1 + r.nextInt(11);
        long dia = 1 + r.nextInt(30);

        LocalDate fechaHoy = LocalDate.now();
        LocalDate fechaNacimientoLocalDate = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        long edad = YEARS.between(fechaNacimientoLocalDate, fechaHoy);
;       long aniosDeVida = promedioMuerte - edad;
        long anioMuerte = fechaHoy.getYear() + aniosDeVida;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String stringFecha = anioMuerte + "-" + mes + "-" + dia;
        try {
            return sdf.parse(stringFecha);
        } catch (ParseException e) {
            return null;
        }
    }
}
