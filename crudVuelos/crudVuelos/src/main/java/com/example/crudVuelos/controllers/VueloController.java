package com.example.crudVuelos.controllers;

import com.example.crudVuelos.dto.VueloDTO;
import com.example.crudVuelos.models.Vuelo;
import com.example.crudVuelos.services.VueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private VueloService service;

    @GetMapping("")
    public ResponseEntity<List<VueloDTO>> listaDeVuelos(
            @RequestParam("empresa") Optional<String>empresa,
            @RequestParam("lugarLlegada") Optional<String> lugarLlegada,
            @RequestParam("lugarSalida") Optional<String> lugarSalida,
            @RequestParam("fechaSalida") Optional<LocalDate> fechaSalida,
            @RequestParam("fechaLlegada") Optional<LocalDate> fechaLlegada,
            @RequestParam("ordenarSegun") Optional<String> secuencia) {
        return ResponseEntity.ok(service.filtroVuelos(empresa,lugarLlegada,
            lugarSalida, fechaSalida, fechaLlegada, secuencia));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VueloDTO> busquedaId(@PathVariable Integer id) {
        return ResponseEntity.ok(service.busquedaId(id));
    }

    @PostMapping("")
    public ResponseEntity<String> agregarVuelo(@RequestBody Vuelo vuelo){
        service.agregarVuelo(vuelo);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vuelo guardado con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> editarVuelo(@PathVariable Integer id, @RequestBody Vuelo vuelo) {
        service.actualizarVuelo(id, vuelo);
            return ResponseEntity.ok("Vuelo actualizado con éxito");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVuelo(@PathVariable Integer id) {
        service.borrarVuelo(id);
            return ResponseEntity.ok("Vuelo eliminado con éxito.");
    }
}
