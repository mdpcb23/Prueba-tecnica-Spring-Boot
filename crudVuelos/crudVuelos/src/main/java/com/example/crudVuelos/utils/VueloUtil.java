package com.example.crudVuelos.utils;

import com.example.crudVuelos.dto.VueloDTO;
import com.example.crudVuelos.models.Vuelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class VueloUtil {
    public static int diasDeVuelo(LocalDate fechaLlegada, LocalDate fechaSalida) {
        if (fechaLlegada == null || fechaSalida == null) {
        return 0;
    }
        return (int) ChronoUnit.DAYS.between(fechaSalida, fechaLlegada);
}
    public static VueloDTO newVueloDTO(Vuelo vuelo) {
        VueloDTO vueloDTO = new VueloDTO();
        vueloDTO.setIdV(vuelo.getId());
        vueloDTO.setNombreV(vuelo.getNombreVuelo());
        vueloDTO.setAereolinea(vuelo.getEmpresa());
        vueloDTO.setCiudadLlegada(vuelo.getLugarLlegada());
        vueloDTO.setCiudadSalida(vuelo.getLugarSalida());
        vueloDTO.setDiaFinal(vuelo.getFechaLlegada());
        vueloDTO.setDiaInicio(vuelo.getFechaSalida());
        vueloDTO.setDuracionEnDias(diasDeVuelo(vuelo.getFechaLlegada(), vuelo.getFechaSalida()));
        return vueloDTO;
    }

    public static boolean verificarDatos(Vuelo vuelo) {
        if (vuelo.getNombreVuelo() == null
                || vuelo.getNombreVuelo().isBlank()
                || vuelo.getEmpresa() == null
                || vuelo.getEmpresa().isBlank()
                || vuelo.getLugarLlegada() == null
                || vuelo.getLugarLlegada().isBlank()
                || vuelo.getLugarSalida() == null
                || vuelo.getLugarSalida().isBlank()
                || vuelo.getFechaLlegada() == null
                || vuelo.getFechaSalida() == null
                || vuelo.getFechaLlegada().isBefore(vuelo.getFechaSalida())) {
            return true;
        }
        return false;
    }
}
