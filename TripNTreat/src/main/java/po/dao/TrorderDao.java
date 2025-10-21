package po.dao;

import java.util.List;

import po.Trorder;

public interface TrorderDao {
	// create
	void add(Trorder trorder);

	// read
	List<Trorder> selectAll();

	Trorder selectById(int id);

	List<Trorder> selectByMemberNo(String memberNo); 

	Trorder findTrorderByNo(String trorderNo);

	// update
	void update(Trorder trorder);

	// delete
	void delete(int id);
}
