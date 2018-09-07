package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import java.util.List;

public class VkmAjoradat {

    public final int pituus;
    public final List<VkmAjorataGeometria> ajoradat;

    public VkmAjoradat(int pituus, List<VkmAjorataGeometria> ajoradat) {
        this.pituus = pituus;
        this.ajoradat = ajoradat;
    }

}
