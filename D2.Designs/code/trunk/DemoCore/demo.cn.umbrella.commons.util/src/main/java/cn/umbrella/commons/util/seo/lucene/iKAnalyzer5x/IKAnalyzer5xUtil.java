package cn.umbrella.commons.util.seo.lucene.iKAnalyzer5x;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

public class IKAnalyzer5xUtil {
	private static String synonymsDir = "D:\\data\\lucene\\synonyms.txt";
	/**
	 * 
	 * 此方法描述的是：进行中文拆分
	 */
	public static String analyzeChinese(String input, boolean userSmart)
			throws IOException {
		StringBuffer sb = new StringBuffer();
		StringReader reader = new StringReader(input.trim());
		IKSegmenter ikSeg = new IKSegmenter(reader, userSmart);// true　用智能分词　，false细粒度
		for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
			sb.append(lexeme.getLexemeText()).append(" ");
		}
		return sb.toString();
	}

	/**
	 * 
	 * 此方法描述的是：针对上面方法拆分后的词组进行同义词匹配，返回TokenStream
	 */
	public static TokenStream convertSynonym(String input) throws IOException {
		Map<String, String> filterArgs = new HashMap<String, String>();
		filterArgs.put("expand", "true");
		SynonymFilterFactory factory = new SynonymFilterFactory(filterArgs);
		factory.inform(new FilesystemResourceLoader(Paths.get(synonymsDir)));
		Analyzer whitespaceAnalyzer = new WhitespaceAnalyzer();
		TokenStream ts = whitespaceAnalyzer.tokenStream("someField", input);
		whitespaceAnalyzer.close();
		return factory.create(ts);
	}
	
	/**
	 * 
	 * 此方法描述的是：将tokenstream拼成一个特地格式的字符串，交给IndexSearcher来处理
	 */
	public static String displayTokens(TokenStream ts) throws IOException {
		StringBuffer sb = new StringBuffer();
		CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
		ts.reset();
		while (ts.incrementToken()) {
			String token = termAttr.toString();
			sb.append(token).append(" ");
			System.out.print(token + "|");
		}
		System.out.println();
		ts.end();
		ts.close();
		return sb.toString();
	}
}
