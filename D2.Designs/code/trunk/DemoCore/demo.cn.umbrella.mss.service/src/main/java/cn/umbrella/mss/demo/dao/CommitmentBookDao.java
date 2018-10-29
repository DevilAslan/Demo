
package cn.umbrella.mss.demo.dao;

import java.util.List;

import cn.umbrella.mss.demo.po.CommitmentBook;


public interface CommitmentBookDao {
	int insertBatch(List<CommitmentBook> list);
}
