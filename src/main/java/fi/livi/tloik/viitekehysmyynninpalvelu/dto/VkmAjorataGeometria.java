package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

public class VkmAjorataGeometria {

    public final Integer ajorata;
    public final String wktMultilineStrings;

    public VkmAjorataGeometria(Integer ajorata, String wktMultilineStrings) {
        this.ajorata = ajorata;
        this.wktMultilineStrings = wktMultilineStrings;
    }

}
