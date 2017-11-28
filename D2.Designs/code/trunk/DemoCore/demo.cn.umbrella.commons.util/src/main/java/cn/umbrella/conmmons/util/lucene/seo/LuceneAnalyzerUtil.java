package cn.umbrella.conmmons.util.lucene.seo;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class LuceneAnalyzerUtil {
	private static Directory directory = null;
	private static IndexWriter indexWriter = null;

	/**
	 * 建立索引
	 * 
	 * @param clear
	 * @param analyzer
	 * @param indexDir
	 * @param docs
	 * @param terms
	 * @throws IOException
	 */
	public static void index(boolean clear, Analyzer analyzer, String indexDir, List<Document> docs, List<Term> terms)
			throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(Paths.get(indexDir)), new IndexWriterConfig(analyzer));
		if(clear){
			indexWriter.deleteAll();
		} else {
			for(Term term : terms){
				indexWriter.deleteDocuments(term);
			}
		}
		if(docs != null && docs.size()>0){
			indexWriter.addDocuments(docs);
		}
		indexWriter.commit();
		indexWriter.close();
	}
	
	/**
	 * 建立索引
	 * 
	 * @param indexDir
	 * @param docs
	 * @throws IOException
	 */
	public static void index(boolean clear, Analyzer analyzer, String indexDir, List<Document> docs)
			throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(Paths.get(indexDir)), new IndexWriterConfig(analyzer));
		if(clear){
			indexWriter.deleteAll();
		}
		if(docs != null && docs.size()>0){
			indexWriter.addDocuments(docs);
		}
		indexWriter.commit();
		indexWriter.close();
	}
	
	/**
	 * 更新索引
	 * 
	 * @param indexDir
	 * @param term
	 * @param docs
	 * @throws IOException
	 */
	public static void update(Analyzer analyzer, String indexDir, Term term, List<Document> docs) throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(Paths.get(indexDir)), new IndexWriterConfig(analyzer));
		if(docs != null && docs.size()>0){
			indexWriter.updateDocuments(term, docs);
			indexWriter.commit();
		}
		indexWriter.close();
	}
	
	/**
	 * 删除索引
	 *
	 * @param analyzer
	 * @param indexDir
	 * @param terms
	 * @throws IOException
	 */
	public static void delete(Analyzer analyzer, String indexDir, List<Term> terms) throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(Paths.get(indexDir)), new IndexWriterConfig(analyzer));
		for(Term term : terms){
			indexWriter.deleteDocuments(term);
		}
		indexWriter.commit();
		indexWriter.close();
	}

	/**
	 * 删除索引
	 * 
	 * @param indexDir
	 * @param term
	 * @throws IOException
	 */
	public static void delete(Analyzer analyzer, String indexDir, Term term) throws IOException {
		indexWriter = new IndexWriter(FSDirectory.open(Paths.get(indexDir)), new IndexWriterConfig(analyzer));
		if(term != null){
			indexWriter.deleteDocuments(term);
			indexWriter.commit();
		}
		indexWriter.close();
	}
	
	/**
	 * 关键字查询
	 * 
	 * @param str
	 * @throws Exception
	 */
	public static List<Document> search(Query query, String indexDir, int top) {
		List<Document> docs = new ArrayList<Document>();
		try {
			directory = FSDirectory.open(Paths.get(indexDir));
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			TopDocs topDocs = isearcher.search(query, top);
			System.out.println("总共匹配多少个：" + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : hits) {
				System.out.println("匹配得分：" + scoreDoc.score + ", 文档索引ID：" + scoreDoc.doc);
				docs.add(isearcher.doc(scoreDoc.doc));
			}
			ireader.close();
			directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/** 
	* @Title: searchNeedClose 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @category 需要手动关闭目录，调用closeDir
	* @param @param query
	* @param @param indexDir
	* @param @param top
	* @param @return    设定文件 
	* @return List<Document>    返回类型 
	* @throws 
	*/ 
	
	public static List<Document> searchNeedClose(Query query, String indexDir, int top) {
		List<Document> docs = new ArrayList<Document>();
		try {
			directory = FSDirectory.open(Paths.get(indexDir));
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			TopDocs topDocs = isearcher.search(query, top);
			//System.out.println(indexDir+"总共匹配多少个：" + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : hits) {
//				System.out.println("匹配得分：" + scoreDoc.score + ", 文档索引ID：" + scoreDoc.doc);
				docs.add(isearcher.doc(scoreDoc.doc));
			}
			ireader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/** 
	* @Title: searchNeedClose 
	* @Description: 
	* @category 需要手动关闭目录，调用closeDir
	* @param query
	* @param indexDir
	* @param top
	* @param sort
	* @return List<Document>    返回类型 
	* @throws 
	*/
	public static List<Document> searchNeedCloserBySort(Query query, String indexDir, int top, Sort sort) {
		List<Document> docs = new ArrayList<Document>();
		try {
			directory = FSDirectory.open(Paths.get(indexDir));
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			TopDocs topDocs = isearcher.search(query, top, sort);
			ScoreDoc[] hits = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : hits) {
				docs.add(isearcher.doc(scoreDoc.doc));
			}
			ireader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return docs;
	}
	
	/** 
	* @Title: closeDir 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @category 手动关闭索引目录
	* @param     设定文件 
	* @return void    返回类型 
	* @throws 
	*/ 
	
	public static void closeDir(){
		try {
			if(null != directory){
				directory.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关键字查询,返回一个List数组，里面类型是map<String,String>
	 * 
	 * @param str
	 * @throws Exception
	 */
	public static List<Map<String,String>> searchConvertToMap(Query query, String indexDir, int top) {
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<Document> docs = new ArrayList<Document>();
		try {
			directory = FSDirectory.open(Paths.get(indexDir));
			DirectoryReader ireader = DirectoryReader.open(directory);
			IndexSearcher isearcher = new IndexSearcher(ireader);
			
			TopDocs topDocs = isearcher.search(query, top);
			System.out.println("总共匹配多少个：" + topDocs.totalHits);
			ScoreDoc[] hits = topDocs.scoreDocs;
			for (ScoreDoc scoreDoc : hits) {
				System.out.println("匹配得分：" + scoreDoc.score + ", 文档索引ID：" + scoreDoc.doc);
				docs.add(isearcher.doc(scoreDoc.doc));
			}
			ireader.close();
			directory.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(Document doc:docs){
			Map<String,String> map = new HashMap<String, String>();
			for( IndexableField field : doc.getFields()){
				map.put(field.name(), field.stringValue());
			}
			list.add(map);
		}
		return list;
	}
}
