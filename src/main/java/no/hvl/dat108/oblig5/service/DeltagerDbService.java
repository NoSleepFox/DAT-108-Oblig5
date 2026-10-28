package no.hvl.dat108.oblig5.service;

import no.hvl.dat108.oblig5.model.Deltager;
import no.hvl.dat108.oblig5.repository.DeltagerRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeltagerDbService {

    private final DeltagerRepository deltagerRepository;

    public DeltagerDbService(DeltagerRepository deltagerRepository) {
        this.deltagerRepository = deltagerRepository;
    }

    // private static List<Deltager> deltagerliste = new ArrayList<>(List.of(
    // new Deltager("12345678", "dummyhash", "Knut", "Knutsen", "mann"),
    // new Deltager("23456789", "dummyhash", "Knut", "Olsen", "mann"),
    // new Deltager("34567890", "dummyhash", "Anne", "Annesen", "kvinne"),
    // new Deltager("11223344", "dummyhash", "Lise", "Hansen", "kvinne")));

    /**
     * @param mobil
     * @return En deltager med gitt mobilnummer, null hvis ingen.
     */
    public Deltager finnDeltagerMedMobil(String mobil) {

        return deltagerRepository.findById(mobil).orElse(null);
    }

    /**
     * @return Alle deltagerne, sortert stigende på fullt navn.
     */
    public List<Deltager> finnAlleSortertPaaFulltNavn() {
        return deltagerRepository.findAll().stream()
                .sorted(Deltager.paaFulltNavn)
                .toList();
    }

    /**
     * @param mobil
     * @return Om deltager med gitt mobil er påmeldt
     */
    public boolean erDeltagerPaameldt(String mobil) {

        return deltagerRepository.existsById(mobil);
    }

    /**
     * Lagrer (melder på) en deltager ved å legge til i listen.
     * 
     * @param deltager
     */
    public void lagreDeltager(Deltager deltager) {
        deltagerRepository.save(deltager);
    }
}
