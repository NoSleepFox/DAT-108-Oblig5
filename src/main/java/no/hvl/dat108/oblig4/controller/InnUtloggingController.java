package no.hvl.dat108.oblig4.controller;

import jakarta.servlet.http.HttpServletRequest;
import no.hvl.dat108.oblig4.model.Deltager;
import no.hvl.dat108.oblig4.model.Feilmeldinger;
import no.hvl.dat108.oblig4.service.DeltagerDbService;
import no.hvl.dat108.oblig4.service.LogginnService;
import no.hvl.dat108.oblig4.service.PassordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InnUtloggingController {

	/*
	 * GET /innlogging - Viser innloggingsskjemaet vhja logginn-viewet.
	 */

    // Annoteringen for å ta i mot requesten
    @GetMapping("/innlogging")
	public String henteSkjema() {
        return "logginn";
	}

	/*
	 * POST /innlogging - Tar i mot forsøk på innlogging, dvs: 
	 * - Sjekker at deltager finnes og at passord er korrekt
	 * - Logger inn og går (redirecter) til deltagerlisten
	 *
	 * Hvis ugyldig brukernavn og/eller passord, skal det gjøres er redirect
	 * til /innlogging med en feilmelding satt som en flash-attributt.
	 */

    // logg inn -> til deltagerliste
    @PostMapping("/innlogging")
	public String loggeInn(@RequestParam String mobil, @RequestParam String passord, 
			HttpServletRequest request, RedirectAttributes ra) {

        Deltager deltager = DeltagerDbService.finnDeltagerMedMobil(mobil);

        if (deltager != null && PassordService.erPassordKorrekt(
                passord, deltager.getHashetPassord()
        )) {
            LogginnService.loggInnBruker(request, deltager);

            return "redirect:/deltagerliste";
        }

        // feilmelding -> tilbake til innloggingsside
        ra.addFlashAttribute("feilmelding", Feilmeldinger.UGYLDIG_BRUKERNAVN_PASSORD_MLD);

        return "redirect:/innlogging";
	}

	/*
	 * POST /utlogging - Tar i mot forespørsel om utlogging, dvs: 
	 * - Logger ut 
	 * - Går (redirecter) til innlogging
	 */

    //Annoteringen for å ta i mot requesten
    @PostMapping("/utlogging")
	public String loggeUt(HttpServletRequest request, RedirectAttributes ra) {

        LogginnService.loggUtBruker(request);

        ra.addFlashAttribute("feilmelding", Feilmeldinger.UTLOGGET_MLD);

        return "redirect:/innlogging";
	}

}
