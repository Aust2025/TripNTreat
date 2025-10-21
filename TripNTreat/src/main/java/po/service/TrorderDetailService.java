package po.service;

import java.util.List;

import po.TrorderDetail;

public interface TrorderDetailService {

	// Create
	void addTrorderDetail(TrorderDetail trorderDetail);

	// Read
	List<TrorderDetail> findAllTrorderDetail(); 

	TrorderDetail findById(int id); 

	List<TrorderDetail> findByTrorderNo(String trorderNo); 

	// Update
	void updateTrorderDetail(TrorderDetail trorderDetail);

	// Delete
	void deleteTrorderDetail(int id);

	void deleteByTrorderNo(String trorderNo); 
}
