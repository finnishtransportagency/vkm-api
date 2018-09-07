package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


public class Tieosoite {
    public Integer tie;
    public Integer osa;
    public Integer etaisyys;
    public Integer ajorata;

    public Tieosoite(int tie, int osa, int etaisyys, int ajorata){
        this.tie = tie;
        this.osa = osa;
        this.etaisyys = etaisyys;
        this.ajorata = ajorata;
    }
}