package po.service;

import java.util.List;

import po.Trorder;

public interface TrorderService {

	// create
	void addTrorder(Trorder trorder);

	// read
	List<Trorder> selectAll();

	List<Trorder> findAllTrorderByMemberId(String memberNo); // 依會員查詢

	Trorder findTrorderById(int id);

	// update
	void updateTrorder(Trorder trorder);

	// delete
	void deleteTrorder(int id);
}
