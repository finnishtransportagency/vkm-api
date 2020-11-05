//    @RequestMapping(value = "xyhaku", method = RequestMethod.GET)
//    public void haeKoordinaatilla(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "x", required = true) Double x,
//            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "tie", required = false) Integer tie,
//            @RequestParam(name = "aosa", required = false) Integer aosa,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
//            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @RequestParam(name = "vaylan_luonne", required = false) List<Integer> vaylan_luonne,
//            @ApiParam(value = "Oletusarvo 100")
//            @RequestParam(name = "sade", required = false) Integer sade,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//                
//    			yleisRajapinta(tunniste, 
//    				x, y, null, x_loppu, y_loppu, null, null, sade, 
//    				tie, ajr, aosa, null, tilannepvmAsString, kohdepvmAsString, null, null, 
//    				null, null, null, null, 
//    				null, null, null, null, 
//    				vaylan_luonne, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//    }
//    
//    
//    @RequestMapping(value = "tieosoitehaku", method= RequestMethod.GET)
//    public void haeTieosoitteella(
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "tie", required = true) Integer tie,
//            @RequestParam(name = "aosa", required = true) Integer aosa,
//            @RequestParam(name = "aet", required = true) Integer aet,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//    		@RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//		
//				yleisRajapinta(tunniste, 
//					null, null, null, null, null, null, null, null, 
//					tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, null, null, 
//					null, null, null, null, 
//					null, null, null, null, 
//					null, null, 
//					null, null, null,
//					null, palautusarvot, null);
//		
//    }
//	
//    
//	@RequestMapping(value = "tieosoitevali", method= RequestMethod.GET)
//    public void haeKokoTie(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "tie", required = true) Integer tie,
//            @RequestParam(name = "aosa", required = false) Integer aosa,
//            @RequestParam(name = "aet", required = false) Integer aet,
//            @RequestParam(name = "losa", required = false) Integer losa,
//            @RequestParam(name = "let", required = false) Integer let,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot,
//            @RequestParam(name = "ajr", required = false) List<Integer> ajr,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "tilannepvm", required = false) String tilannepvmAsString,
//            @ApiParam(value = "Muodossa pp.kk.vvvv")
//            @RequestParam(name = "kohdepvm", required = false) String kohdepvmAsString
//            ) {
//    	
//    			yleisRajapinta(tunniste, 
//    				null, null, null, null, null, null, null, null, 
//    				tie, ajr, aosa, aet, tilannepvmAsString, kohdepvmAsString, losa, let, 
//    				null, null, null, null, 
//    				null, null, null, null, 
//    				null, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//    	
//    }
//    
//    
//    @RequestMapping(value = "geocode", method= RequestMethod.GET)
//    public void  geocode(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste,
//            @RequestParam(name = "kuntakoodi", required = true) Integer kuntakoodi,
//            @RequestParam(name = "katunimi", required = true) String katunimi,
//            @RequestParam(name = "katunumero", required = true) Integer katunumero,
//            @RequestParam(name = "katunumero_loppu", required = false) Integer katunumero_loppu,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//
//    			yleisRajapinta(tunniste, 
//    				null, null, null, null, null, null, null, null, 
//    				null, null, null, null, null, null, null, null, 
//    				null, null, null, null, 
//    				kuntakoodi, katunimi, katunumero, katunumero_loppu, 
//    				null, null, 
//    				null, null, null,
//    				null, palautusarvot, null);
//
//    }
//    
//    
//    @RequestMapping(value = "reversegeocode", method= RequestMethod.GET)
//    public void reversegeocode(
//    		
//    		@RequestParam(name = "tunniste", required = false) String tunniste, 
//            @RequestParam(name = "kuntakoodi", required = false) Integer kuntakoodi,
//            @RequestParam(name = "katunimi", required = false) String katunimi,
//            @RequestParam(name = "x", required = true) Double x,
//            @RequestParam(name = "y", required = true) Double y,
//            @RequestParam(name = "x_loppu", required = false) Double x_loppu,
//            @RequestParam(name = "y_loppu", required = false) Double y_loppu,
//            @ApiParam(value = "Oletusarvo 100")
//            @RequestParam(name = "sade", required = false) Integer sade,
//            @ApiParam(value = "1=pistekoordinaatti, 2=tieosoite, 3=katuosoite, 4=aluetiedot, 5=viivageometria")
//            @RequestParam(name = "palautusarvot", required = false) List<Integer> palautusarvot
//            ) {
//    			
//    			yleisRajapinta(tunniste, 
//					x, y, null, x_loppu, y_loppu, null, null, sade, 
//					null, null, null, null, null, null, null, null, 
//					null, null, null, null, 
//					kuntakoodi, katunimi, null, null, 
//					null, null, 
//					null, null, null,
//					null, palautusarvot, null);
//                
//    }
//      
//    
////    @RequestMapping(value = "muunnin-post", method = RequestMethod.POST)
////    public Object handlePost(HttpServletRequest request) throws VkmVirheException, NamingException, SQLException {
////    	
////    	String json = request.getParameter("json");
////    	return muunnin(json);           
////    }