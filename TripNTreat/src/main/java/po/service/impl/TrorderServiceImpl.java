package po.service.impl;

import java.util.List;

import po.Trorder;
import po.dao.impl.TrorderDaoImpl;
import po.service.TrorderService;

public class TrorderServiceImpl implements TrorderService {
	private static TrorderDaoImpl tr = new TrorderDaoImpl();

	// create
	@Override
	public void addTrorder(Trorder trorder) {
		tr.add(trorder);
	}

	// read
	@Override
	public List<Trorder> selectAll() {
		return tr.selectAll();
	}

	@Override
	public Trorder findTrorderById(int id) {
		return tr.selectById(id);
	}

	// update
	@Override
	public void updateTrorder(Trorder trorder) {
		tr.update(trorder);
	}

	// delete
	@Override
	public void deleteTrorder(int id) {
		tr.delete(id);
	}

	// findAllTrorderByMemberId
	@Override
	public List<Trorder> findAllTrorderByMemberId(String memberNo) {
		return tr.selectByMemberNo(memberNo);
	}

	public Trorder findTrorderByNo(String trorderNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
