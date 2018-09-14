package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

public class TieosoiteVali extends Tieosoite{
    
    public Integer losa;
    public Integer let;

    public TieosoiteVali(int tie, int osa, int etaisyys, int ajorata, int losa, int let){
        super(tie,osa,etaisyys,ajorata);
        this.losa = losa;
        this.let = let;

    }
    
}