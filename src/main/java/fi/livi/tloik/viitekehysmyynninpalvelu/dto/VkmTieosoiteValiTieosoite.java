package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import com.vividsolutions.jts.geom.Point;

/*
   {
        "ajorata": 0,
        "point": {
            "spatialReference": {
                "wkid": 3067
            },
            "y": 6818120.506214286,
            "x": 335122.036
        },
        "osa": 2,
        "etaisyys": 1000,
        "tie": 309
    }
 */
public class VkmTieosoiteValiTieosoite {

    public class TieosoitePoint {
        public class SpatialR {
            public Integer wkid = 3067;
        }

        public SpatialR spatialReference = new SpatialR();
        public Double y;
        public Double x;

        public TieosoitePoint(Double y, Double x) {
            super();
            this.y = y;
            this.x = x;
        }

    }

    public Integer ajorata;
    //public Point point;
    public TieosoitePoint point;
    public Integer osa;
    public Integer etaisyys;
    public Integer tie;

    public VkmTieosoiteValiTieosoite(Integer ajorata, Integer osa, Integer etaisyys,
            Integer tie, Point point) {
        this.ajorata = ajorata;
        this.point = new TieosoitePoint(point.getX(), point.getY());
        this.osa = osa;
        this.etaisyys = etaisyys;
        this.tie = tie;
    }


}
