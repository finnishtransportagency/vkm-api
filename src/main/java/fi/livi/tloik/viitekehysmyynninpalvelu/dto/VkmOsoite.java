package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import java.util.Optional;

import com.vividsolutions.jts.geom.Point;

public class VkmOsoite {

    public final String osoite;
    public final Optional<String> kunta;
    public final Optional<String> kuntakoodi;
    public final Point koordinaatti;

    public VkmOsoite(String osoite, Point koordinaatti) {
        this(osoite, null, null, koordinaatti);
    }

    public VkmOsoite(String osoite, String kunta, Point koordinaatti) {
        this(osoite, kunta, null, koordinaatti);
    }

    public VkmOsoite(String osoite, String kunta, String kuntakoodi, Point koordinaatti) {
        this.osoite = osoite;
        this.kunta = Optional.ofNullable(kunta);
        this.koordinaatti = koordinaatti;
        this.kuntakoodi = Optional.ofNullable(kuntakoodi);
    }

}
