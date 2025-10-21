package po.service;

import java.util.List;

import po.Trorder;

public interface TrorderService {

	// create
	void addTrorder(Trorder trorder);

	// read
	List<Trorder> selectAll();

	List<Trorder> findAllTrorderByMemberId(String memberNo); 

	Trorder findTrorderById(int id);

	Trorder findTrorderByNo(String trorderNo);

	// update
	void updateTrorder(Trorder trorder);

	// delete
	void deleteTrorder(int id);
}
