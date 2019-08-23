package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import com.vividsolutions.jts.geom.Point;

public class VkmTieosoite {

    public final Integer tie;
    public final Integer osa;
    public final Integer etaisyys;
    public final Integer ajorata;
    public transient final Point koordinaatti;
    public final Double x;
    public final Double y;


    public String maakunta;
    public String kuntakoodi;
    public String urakka_alue;
    public String ely;
    
    public VkmTieosoite(Integer tie, Integer osa, Integer etaisyys, Integer ajorata, Point koordinaatti) {
        this.tie = tie;
        this.osa = osa;
        this.etaisyys = etaisyys;
        this.ajorata = ajorata;
        this.koordinaatti = koordinaatti;
        this.x = koordinaatti.getX();
        this.y = koordinaatti.getY();
    }

}
