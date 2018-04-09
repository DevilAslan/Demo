package cn.umbrella.commons.utils.map;

import java.util.List;
import java.util.Map;

public class MapParamater {
	private List<MapDetails> results;

	public List<MapDetails> getResults() {
		return results;
	}
	
	public MapDetails getResult() {
		if(results!=null)
			return results.get(0);
		return null;
	}

	public void setResults(List<MapDetails> results) {
		this.results = results;
	}
	
	class MapDetails {
		private Map<String,Object> location;
		public Map<String, Object> getLocation() {
			return location;
		}
		public void setLocation(Map<String, Object> location) {
			this.location = location;
		}
	}
	
}
