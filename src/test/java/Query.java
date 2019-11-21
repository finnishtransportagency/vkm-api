public class Query {
	
	StringBuilder query = new StringBuilder();
	
	public Query(String baseURL) {
		query.append(baseURL);
	}
	
	public void addToQuery(String param, String value) {
		if (query.toString().contains("?") ) {
			query.append("&");
		}
		else {
			query.append("?");
		}
		query.append(param);
		query.append("=");
		query.append(value);
	}
	
	public StringBuilder getQuery() {
		return query;
	}
}