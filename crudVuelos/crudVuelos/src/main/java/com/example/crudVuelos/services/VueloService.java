package com.example.crudVuelos.services;

import com.example.crudVuelos.dto.VueloDTO;
import com.example.crudVuelos.models.Vuelo;
import com.example.crudVuelos.repositories.VueloRepository;
import com.example.crudVuelos.utils.VueloUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VueloService {

    @Autowired
    private VueloRepository repository;

    private Integer conteo = 11;

    public List<VueloDTO> filtroVuelos(Optional<String> empresa,
                                       Optional<String> lugarLlegada,
                                       Optional<String> lugarSalida,
                                       Optional<LocalDate> fechaSalida,
                                       Optional<LocalDate> fechaLlegada,
                                       Optional<String> secuencia) {
        return repository.buscarVuelos().stream()
                .filter(empresa.isPresent() ? v -> v.getEmpresa().equalsIgnoreCase(empresa.get()) : v -> true)
                .filter(lugarLlegada.isPresent() ? v -> v.getLugarLlegada().equalsIgnoreCase(lugarLlegada.get()) : v -> true)
                .filter(lugarSalida.isPresent() ? v -> v.getLugarSalida().equalsIgnoreCase(lugarSalida.get()) : v -> true)
                .filter(fechaLlegada.isPresent() ? v -> v.getFechaLlegada().isEqual(fechaLlegada.get()) : v -> true)
                .filter(fechaSalida.isPresent() ? v -> v.getFechaSalida().isEqual(fechaSalida.get()) : v -> true)
                .sorted(secuencia.isEmpty()
                        ? Comparator.comparing(Vuelo::getFechaSalida)
                        : secuencia.get().equalsIgnoreCase("empresa")
                        ? Comparator.comparing(Vuelo::getEmpresa)
                        : Comparator.comparing(Vuelo::getLugarLlegada))
                .map(VueloUtil::newVueloDTO)
                .toList();
    }

    public VueloDTO busquedaId(Integer id) {
        Vuelo vuelo = repository.buscarId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vuelo no encontrado"));
        return VueloUtil.newVueloDTO(vuelo);
    }

    public void agregarVuelo(Vuelo vuelo) {
        if (VueloUtil.verificarDatos(vuelo)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Datos del vuelo inválidos: revise nombre, empresa, lugares y fechas.");
        }
        vuelo.setId(conteo++);
        repository.guardar(vuelo);
    }

    public void actualizarVuelo(Integer id, Vuelo vuelo) {
        if (!repository.idActual(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un vuelo con ese ID");
        }
        if (VueloUtil.verificarDatos(vuelo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Datos del vuelo inválidos: revise nombre, empresa, lugares y fechas.");
        }
        vuelo.setId(id);
        repository.guardar(vuelo);
    }

    public void borrarVuelo(Integer id) {
        if (!repository.idActual(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un vuelo con ese ID");
        }
        repository.eliminarId(id);
    }
}