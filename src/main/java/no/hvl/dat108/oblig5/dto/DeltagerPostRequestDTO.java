package no.hvl.dat108.oblig5.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import no.hvl.dat108.oblig5.model.Deltager;

import static no.hvl.dat108.oblig5.model.DeltagerSkjema.*;

public record DeltagerPostRequestDTO(
        @NotNull(message ="Mobilnummer må oppgis")
        @Pattern(regexp = STRING_START + A_DIGIT + EIGHT_TIMES + STRING_END,
        message = "Mobilnummeret må være nøyaktig 8 tall")
        String mobil,

        @NotNull(message = "Passord må oppgis")
        @Size(min = 12, message = "Passordet må inneholde minst 12 tegn")
        String passord,

        @NotNull(message = "Fornavn må oppgis")
        @Pattern(regexp = STRING_START + A_CAPITAL_LETTER + A_LETTER_SPACE_OR_HYPHEN + ONE_TO_19_TIMES + STRING_END,
        message = "Fornavn må være mellom 2-20 tegn og starte med stor bokstav")
        String fornavn,

        @NotNull(message = "Etternavn må oppgis")
        @Pattern(regexp = STRING_START + A_CAPITAL_LETTER + A_LETTER_OR_HYPHEN + ONE_TO_19_TIMES + STRING_END,
        message = "Etternavn må være mellom 2-20 tegn og starte med stor bokstav")
        String etternavn,

        @NotNull(message = "Velg kjønn")
        @Pattern(regexp = "^(mann|kvinne)$", message = "Kjønn må være enten mann eller kvinne")
        String kjonn) {

    public Deltager lagDeltager(String hashetPassord){
        return new Deltager(mobil,hashetPassord,fornavn,etternavn,kjonn);
    }
}
