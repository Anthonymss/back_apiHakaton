package com.seguridad_ciudadana.service.comisaria;
import com.seguridad_ciudadana.entity.Comisaria;
import com.seguridad_ciudadana.repository.ComisariaRepositor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DistanceService {

    private static final double EARTH_RADIUS = 6371.0;

    @Autowired
    private ComisariaRepositor comisariaRepository;

    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(lat1Rad) * Math.cos(lat2Rad)
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

    public List<Comisaria> findClosestComisarias(double userLat, double userLon) {
        List<Comisaria> comisarias = comisariaRepository.findAll();
        List<Comisaria> sortedComisarias = comisarias.stream()
                .sorted(Comparator.comparingDouble(comisaria -> calculateDistance(userLat, userLon, comisaria.getLatitud(), comisaria.getLongitud())))
                .collect(Collectors.toList());
        return sortedComisarias.stream().limit(3).collect(Collectors.toList());
    }
}
