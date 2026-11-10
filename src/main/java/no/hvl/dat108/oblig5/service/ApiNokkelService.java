package no.hvl.dat108.oblig5.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiNokkelService {

    @Value("${app.api-key}")
    private String denEnesteGyldigeApiNokkel;

    public boolean erNokkelGyldig(String nokkel){
        return denEnesteGyldigeApiNokkel.equals(nokkel);
    }
}
