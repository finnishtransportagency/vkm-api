package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import fi.livi.tloik.viitekehysmyynninpalvelu.dto.Coordinates;
import fi.livi.tloik.viitekehysmyynninpalvelu.dto.Tieosoite;

import java.time.LocalDate;
import java.util.List;
import org.assertj.core.util.Lists;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Optional;

import fi.livi.vkm.util.VkmUtil;


public class InParameters {

    public Integer DEFAULT_SADE = 100;

    public Double x;
    public Double y;
    public Double z;
    public String koordinaatisto;

    public Integer tie;
    public Integer osa;
    public Integer etaisyys;
    public Integer losa;
    public Integer let;
    public List<Integer> ajoradat;

    public Integer sade;
    public List<Integer> vaylat;
    public List<Integer> palautusarvot;
    public String palautuskoord;
    public String tunniste;
    //geocode
    public Integer kuntakoodi;
    public String katunimi;
    public Integer katunumero;

    public LocalDate kohdepvm;


    //reversegeocode
    public InParameters(String tunniste, Double x, Double y, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.x = x;
        this.y = y;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        this.palautusarvot = toIntegerList(palautusarvot);
    }

    //geocode
    public InParameters(String tunniste, Integer kuntakoodi, String katunimi, Integer katunumero, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.kuntakoodi = kuntakoodi;
        this.katunimi = katunimi;
        this.katunumero = katunumero;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        this.palautusarvot = toIntegerList(palautusarvot);
    }

    //xyhaku
    public InParameters( String tunniste, Double x, Double y, Double z, Integer sade, String vaylat, String palautusarvot){
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        if(z == null){
            this.z = 0.0;
        } else {
            this.z = z;
        }
        
        this.x = x;
        this.y = y;
        
        //muuntaa stringin interger listiksi tulevaa hakau varten
        this.vaylat = toIntegerList(vaylat);
        this.tunniste = tunniste;
        this.palautusarvot = toIntegerList(palautusarvot);
    }

    //tieosoitehaku
    public InParameters(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, Integer sade, String palautusarvot){
        
        this.tunniste = tunniste;
        this.tie = tie;
        this.osa = osa;
        this.etaisyys = etaisyys;
        List<Integer> notNullAjoradat = toIntegerList(ajoradat) != null ? toIntegerList(ajoradat) : Lists.emptyList();
        this.ajoradat = notNullAjoradat;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        
    }

    //tieosoitevali
    public InParameters(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, Integer losa, Integer let, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.tie = tie;
        this.osa = Optional.ofNullable(osa).orElse(0);
        this.etaisyys = Optional.ofNullable(etaisyys).orElse(0);
        List<Integer> notNullAjoradat = toIntegerList(ajoradat) != null ? toIntegerList(ajoradat) : Lists.emptyList();
        this.ajoradat = notNullAjoradat;
        this.losa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
        this.let = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        this.palautusarvot = toIntegerList(palautusarvot);
        
    }

    

    /**
	 * Converts string to <code>Integer</code> list. 
	 * @param string
	 * @return
	 */
	public static List<Integer> toIntegerList(String test){

		Scanner scanner = new Scanner(test);
		List<Integer> list = new ArrayList<Integer>();
		while (scanner.hasNextInt()) {
    	list.add(scanner.nextInt());
		}
		scanner.close();

		return list;
	}


    

}