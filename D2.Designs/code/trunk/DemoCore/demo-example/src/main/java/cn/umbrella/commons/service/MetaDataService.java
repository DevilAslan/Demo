package cn.umbrella.commons.service;

import java.util.logging.Logger;

public class MetaDataService {
	// private static final Logger logger =
	// Logger.getLogger(MetaDataService.class);
	private static final String FILE_SEPARATOR = System
			.getProperty("file.separator");
	private static int pageSize = 50000;

	public static void addIndex(String data) {
		
		String sql = "select * from wzcredit." + data
				+ " where data_status=0 limit ?,?";
	}
}
