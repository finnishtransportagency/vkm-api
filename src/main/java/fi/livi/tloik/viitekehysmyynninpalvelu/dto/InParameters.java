package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;


public class InParameters {

    public Integer DEFAULT_SADE = 100;
    public List<Integer> DEFAULT_PALAUTUSARVOT = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

    public Double x;
    public Double y;
    public Double z;
    public Double x_loppu;
    public Double y_loppu;
    public Double z_loppu;
    public String koordinaatisto;

    public Integer tie;
    public Integer osa;
    public Integer etaisyys;
    public Integer losa;
    public Integer let;
    public List<Integer> ajoradat;
    public LocalDate tilannepvm;
    public LocalDate kohdepvm;

    public Integer sade;
    public List<Integer> vaylat;
    public List<Integer> palautusarvot;
    public String palautuskoord;
    public String tunniste;
    //geocode
    public Integer kuntakoodi;
    public String katunimi;
    public Integer katunumero;
    public Integer katunumero_loppu;


    //reversegeocode
    public InParameters(String tunniste, Integer kuntakoodi, String katunimi, Double x, Double y, Double x_loppu, Double y_loppu, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.kuntakoodi = kuntakoodi;
        this.katunimi = katunimi;
        this.x = x;
        this.y = y;
        this.x_loppu = x_loppu;
        this.y_loppu = y_loppu;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        if(palautusarvot == null){
            this.palautusarvot = DEFAULT_PALAUTUSARVOT;
        }else {
            this.palautusarvot = toIntegerList(palautusarvot);
        }
    }

    //geocode
    public InParameters(String tunniste, Integer kuntakoodi, String katunimi, Integer katunumero, Integer katunumero_loppu, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.kuntakoodi = kuntakoodi;
        this.katunimi = katunimi;
        this.katunumero = katunumero;
        this.katunumero_loppu = katunumero_loppu;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        if(palautusarvot == null){
            this.palautusarvot = DEFAULT_PALAUTUSARVOT;
        }else {
            this.palautusarvot = toIntegerList(palautusarvot);
        }
    }

    //xyhaku
    public InParameters( String tunniste, Double x, Double y, Double z, Integer tie, Integer osa, String ajoradat, LocalDate tilannepvm, LocalDate kohdepvm, Double x_loppu, Double y_loppu, Double z_loppu, Integer sade, String vaylat, String palautusarvot){
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

        this.tie = tie;
        this.osa = osa;
        this.tilannepvm = tilannepvm;
        this.kohdepvm = kohdepvm;
        List<Integer> notNullAjoradat = toIntegerList(ajoradat) != null ? toIntegerList(ajoradat) : Lists.newArrayList(0, 1);

        this.ajoradat = notNullAjoradat;
        this.x_loppu = x_loppu;
        this.y_loppu = y_loppu;
        this.z_loppu = z_loppu;
        
        //muuntaa stringin interger listiksi tulevaa hakau varten
        this.vaylat = toIntegerList(vaylat);
        this.tunniste = tunniste;
        if(palautusarvot == null){
            this.palautusarvot = DEFAULT_PALAUTUSARVOT;
        }else {
            this.palautusarvot = toIntegerList(palautusarvot);
        }
    }

    //tieosoitehaku
    public InParameters(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, LocalDate tilannepvm, LocalDate kohdepvm, Integer sade, String palautusarvot){
        
        this.tunniste = tunniste;
        this.tie = tie;
        this.osa = osa;
        this.etaisyys = etaisyys;
        this.tilannepvm = tilannepvm;
        this.kohdepvm = kohdepvm;
        List<Integer> notNullAjoradat = toIntegerList(ajoradat) != null ? toIntegerList(ajoradat) : Lists.newArrayList(0, 1);
        this.ajoradat = notNullAjoradat;
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        if(palautusarvot == null){
            this.palautusarvot = DEFAULT_PALAUTUSARVOT;
        }
        else {
            this.palautusarvot = toIntegerList(palautusarvot);
        }

        
    }

    //tieosoitevali
    public InParameters(String tunniste, Integer tie, Integer osa, Integer etaisyys, String ajoradat, LocalDate tilannepvm, LocalDate kohdepvm, Integer losa, Integer let, Integer sade, String palautusarvot){
        this.tunniste = tunniste;
        this.tie = tie;
        this.osa = Optional.ofNullable(osa).orElse(0);
        this.etaisyys = Optional.ofNullable(etaisyys).orElse(0);
        this.tilannepvm = tilannepvm;
        this.kohdepvm = kohdepvm;
        List<Integer> notNullAjoradat = toIntegerList(ajoradat) != null ? toIntegerList(ajoradat) : Lists.newArrayList(0, 1);
        this.ajoradat = notNullAjoradat;
        this.losa = Optional.ofNullable(losa).orElse(Integer.MAX_VALUE);
        this.let = Optional.ofNullable(let).orElse(Integer.MAX_VALUE);
        if(sade == null){
            this.sade = DEFAULT_SADE;
        } else {
            this.sade = sade;
        }
        if(palautusarvot == null){
            this.palautusarvot = DEFAULT_PALAUTUSARVOT;
        }
        else {
            this.palautusarvot = toIntegerList(palautusarvot);
        }
        
    }

    

    /**
	 * Converts string to <code>Integer</code> list. 
	 * @param string
	 * @return
	 */
	public static List<Integer> toIntegerList(String test){
        List<Integer> palautus = new ArrayList<>(); 
        if(test != null){
            palautus = Stream.of(test.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        } else {
            palautus = null;
        }
        
		return palautus;
	}


    

}