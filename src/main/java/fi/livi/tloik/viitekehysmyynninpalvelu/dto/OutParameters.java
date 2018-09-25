package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import org.geolatte.geom.C3DM;
import java.time.LocalDate;
import java.util.List;
import org.geolatte.geom.Point;

import com.vividsolutions.jts.geom.Coordinate;

public class OutParameters {
    
    //tieosoite
    public Integer tie;
    public Integer osa;
    public Integer etaisyys;
    public Integer ajorata;
    public Double x;
    public Double y;
    public Double z;
    public Double distance;

    //TR
    public Integer tietyyppi;
    public LocalDate tilannepvm;
    public LocalDate kohdepvm;

    //tieosoitevali
    public Double x_loppu;
    public Double y_loppu;
    public Double z_loppu;
    public Integer l_tie;
    public Integer losa;
    public Integer let;
    public Integer l_ajorata;
    public String l_maakunta;
    public String l_maakuntaNimi;
    public Integer l_kuntakoodi;
    public String l_kuntaNimi;
    public Integer l_gridcode;
    public Integer l_ely;

    public String koordinaatisto;

    public Integer sade;
    public List<Integer> vaylat;
    public List<Integer> palautusarvot;
    public String palautuskoord;
    public String tunniste;
    //geocode
    public Coordinate koordinaatti;
    public String tienimiFi;
    public Integer katunumero;
    public Integer kunta;
    
    //reversegeocode
    public Point<C3DM> koordReverseGeo;

    //aluehaku
    public String maakunta;
    public String maakuntaNimi;
    public String kuntakoodi;
    public String kuntaNimi;
    public String gridcode;
    public String ely;
    
    
    public OutParameters(String tunniste, Double distance, Integer tie, Integer osa, Integer etaisyys, Integer ajorata, Integer tietyyppi, Point<C3DM> koordReverseGeo, String maakunta, String kuntakoodi, String maakuntaNimi, String kuntaNimi, String gridcode, String ely, List<Integer> palautusarvot) {
        this.tunniste = tunniste;
        this.tie = tie;
        this.osa = osa;
        this.etaisyys = etaisyys;
        this.ajorata = ajorata;
        this.tietyyppi = tietyyppi;
        this.koordReverseGeo = koordReverseGeo;
        this.x = koordReverseGeo.getPosition().getX();
        this.y = koordReverseGeo.getPosition().getY();
        this.z = koordReverseGeo.getPosition().getZ();
        this.distance = distance;
        this.maakunta = maakunta;
        this.maakuntaNimi = maakuntaNimi;
        this.kuntakoodi = kuntakoodi;
        this.kuntaNimi = kuntaNimi;
        this.gridcode = gridcode;
        this.ely = ely;
        this.palautusarvot = palautusarvot;
    }
    



}