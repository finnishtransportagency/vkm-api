package fi.livi.tloik.viitekehysmyynninpalvelu.dto;

import java.util.ArrayList;
import java.util.List;

/*
 {
        "pituus": 10262,
        "tieosoitteet": [
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
        ]
 }
 */
public class VkmTieosoiteValiPiste {

    public Double pituus;
    public List<VkmTieosoiteValiTieosoite> tieosoitteet = new ArrayList<>();

    public VkmTieosoiteValiPiste(Double pituus, List<VkmTieosoiteValiTieosoite> tieosoitteet) {
        super();
        this.pituus = pituus;
        this.tieosoitteet = tieosoitteet;
    }



}
