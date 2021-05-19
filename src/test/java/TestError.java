import java.io.IOException;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class TestError {
	
	//String BaseUrl = "http://localhost:8889/vkm-api/tieosoitevali/";
	//String BaseUrl = "http://localhost:8889/viitekehysmuunnin/muunna";
	String BaseUrl = "https://testijulkinen.vayla.fi/viitekehysmuunnin/muunna";
	
	@Test
	public void testBasicError() throws IOException {
			
			// ANNETAAN IN-PARAMETRIT JA TEHDÄÄN KYSELY
			
			Query q = new Query(BaseUrl);
			
			q.addToQuery("tie", "1");
			q.addToQuery("ajorata", "1");
			q.addToQuery("osa", "1");
			q.addToQuery("etaisyys", "0");
			
			q.addToQuery("osa_loppu", "1");
			q.addToQuery("etaisyys_loppu", "50000");
			
			q.addToQuery("valihaku", "true");
			q.addToQuery("palautusarvot", "1");
			
			//q.addToQuery("metadata", "true");
			
			RequestResponse testInfo = new RequestResponse( q.getQuery().toString() );
			
			// ANNETAAN ODOTETUT OUT-PARAMETRIT
		
			ResultParameters expected = new ResultParameters();
			
			expected.setType("Feature");
			expected.setGeomType("MultiLineString");
			expected.setCoordinates("[]");
			expected.setX(385885.719999971);
			expected.setY(6671747.8099999);
			expected.setZ(9.66899999999441);
		
			expected.setX_loppu(384292.298999971);
			expected.setY_loppu(6674530.68399987);
			expected.setZ_loppu(15.2609999999986);

			//expected.setVirheet();
			//expected.setFeatureCollectionMetadata("{'loppupiste_ajoradoilla':false}");
			
			// VERRATAAN KYSELYN TULOSTA ODOTETTUUN TULOKSEEN
		
			assertThat(testInfo.result).isEqualToComparingFieldByField(expected);
		
	}

}