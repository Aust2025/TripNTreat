package po.service;

import java.util.List;

import po.TrorderDetail;

public interface TrorderDetailService {
	
	// create
	void addTrorderDetail(TrorderDetail trorderDetail);

	// read
	List<TrorderDetail> findAllTrorderDetail();
	
	List<TrorderDetail> findAllTrorderByTrorderNo(String trorderNo); // 依訂單查詢

	TrorderDetail findTrorderDetailById(int id);
	

	// update
	void updateTrorderDetail(TrorderDetail trorderDetail);

	// delete
	void deleteTrorderDetail(int id);
}
