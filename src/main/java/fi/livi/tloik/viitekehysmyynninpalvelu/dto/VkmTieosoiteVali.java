package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import com.vividsolutions.jts.geom.Geometry;

public class VkmTieosoiteVali {

    public VkmTieosoite loppupiste;
    public VkmTieosoite alkupiste;
    public Geometry geometria;
    public Double pituus;

    public VkmTieosoiteVali(VkmTieosoite alkupiste, VkmTieosoite loppupiste, Geometry geometria, Double pituus) {
        super();
        this.loppupiste = loppupiste;
        this.alkupiste = alkupiste;
        this.geometria = geometria;
        this.pituus = pituus;
    }

    @Override
    public String toString() {
        return "VkmTieosoiteVali [loppupiste=" + loppupiste + ", alkupiste=" + alkupiste + ", geometria=" + geometria.toText()
                + ", pituus=" + pituus + "]";
    }



}
