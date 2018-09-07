package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

public class Coordinates {

    public Double x;
    public Double y;
    public Double z;

    public String koordinaatisto;

    public Coordinates(Double x, Double y, Double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}