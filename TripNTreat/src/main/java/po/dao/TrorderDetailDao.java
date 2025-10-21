package po.dao;

import java.util.List;

import po.TrorderDetail;

public interface TrorderDetailDao {
	// create
	void add(TrorderDetail trorderDetail);

	// read
	List<TrorderDetail> selectAll();

	TrorderDetail selectById(int id);

	List<TrorderDetail> selectByTrorderNo(String trorderNo); 

	// update
	void update(TrorderDetail trorderDetail);

	// delete
	void delete(int id);

	void deleteByTrorderNo(String trorderNo);
}
