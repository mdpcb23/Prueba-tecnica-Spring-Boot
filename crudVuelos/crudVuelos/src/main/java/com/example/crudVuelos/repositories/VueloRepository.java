package com.example.crudVuelos.repositories;

import com.example.crudVuelos.models.Vuelo;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class VueloRepository {

    private HashMap<Integer, Vuelo> vuelos = new HashMap<>();
    private Integer conteoId = 11;

    public VueloRepository() {
        vuelos.put(1, new Vuelo(1, "A234-J","ITA Airways","Madrid","Roma",LocalDate.of(2025,9,22), LocalDate.of(2025,9,22)));
        vuelos.put(2, new Vuelo(2, "C345-H","American Airlines","New York","Londres",LocalDate.of(2025,9,24), LocalDate.of(2025,9,24)));
        vuelos.put(3, new Vuelo(3, "F789-0","Ryanair","Paris","Barcelona",LocalDate.of(2025,9,27), LocalDate.of(2025,9,27)));
        vuelos.put(4, new Vuelo(4, "B500-0","United Airlines","Dubai","Estambul",LocalDate.of(2025,9,25), LocalDate.of(2025,9,25)));
        vuelos.put(5, new Vuelo(5, "H889-9","easyJet","Lisboa","Buenos Aires",LocalDate.of(2025,10,1), LocalDate.of(2025,10,1)));
        vuelos.put(6, new Vuelo(6, "DG890","Lufthansa","Bangkok","Tokio",LocalDate.of(2025,10,2), LocalDate.of(2025,10,2)));
        vuelos.put(7, new Vuelo(7, "I5557","IAG","Washington","Milán",LocalDate.of(2025,9,28), LocalDate.of(2025,9,29)));
        vuelos.put(8, new Vuelo(8, "L4480","Air France-KLM","Amsterdam","California",LocalDate.of(2025,10,1), LocalDate.of(2025,10,3)));
        vuelos.put(9, new Vuelo(9, "R4690","Iberia","Hong Kong","Roma",LocalDate.of(2025,9,3), LocalDate.of(2025,9,2)));
        vuelos.put(10, new Vuelo(10, "S5780","Star Peru","Cusco","Wellington",LocalDate.of(2025,10,1), LocalDate.of(2025,10,1)));
    }

    public void guardar(Vuelo vuelo) {
        vuelos.put(vuelo.getId(), vuelo);
    }

    public List<Vuelo>buscarVuelos() {
        return vuelos.values().stream().toList();
    }

    public Optional<Vuelo>buscarId(Integer id){
        return Optional.ofNullable(vuelos.get(id));
    }

    public boolean idActual(Integer id){
        return vuelos.containsKey(id);
    }

    public void eliminarId(Integer id){
        vuelos.remove(id);
    }
}

