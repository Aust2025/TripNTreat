package po.dao;

import java.util.List;

import po.TrorderDetail;

public interface TrorderDetailDao {
	// create
	void add(TrorderDetail trorderDetail);

	// read
	List<TrorderDetail> selectAll();

	TrorderDetail selectById(int id);

	// update
	void update(TrorderDetail trorderDetail);

	// delete
	void delete(int id);
}
