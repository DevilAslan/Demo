package cn.umbrella.commons.annotation;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MapAdapter extends XmlAdapter<MapConvertor, Map<String, Object>> {
	
	/**
	 * 整理,编队
	 */
    @Override
    public MapConvertor marshal(Map<String, Object> map) throws Exception {
        MapConvertor convertor = new MapConvertor();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            MapConvertor.MapEntry e = new MapConvertor.MapEntry(entry);
            convertor.addEntry(e);
        }
        return convertor;
    }

    /**
     * 解编
     */
    @Override
    public Map<String, Object> unmarshal(MapConvertor map) throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        for (MapConvertor.MapEntry e : map.getEntries()) {
            result.put(e.getKey(), e.getValue());
        }
        return result;
    }
}