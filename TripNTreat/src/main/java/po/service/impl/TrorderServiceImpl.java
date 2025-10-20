package po.service.impl;

import java.util.List;

import po.Trorder;
import po.dao.impl.TrorderDaoImpl;
import po.service.TrorderService;

public class TrorderServiceImpl implements TrorderService {
	private static TrorderDaoImpl tr = new TrorderDaoImpl();

	@Override
	public void addTrorder(Trorder trorder) {
		tr.add(trorder);
	}

	@Override
	public List<Trorder> selectAll() {
		return tr.selectAll();
	}

	@Override
	public Trorder findTrorderById(int id) {
		return tr.selectById(id);
	}

	@Override
	public void updateTrorder(Trorder trorder) {
		tr.update(trorder);
	}

	@Override
	public void deleteTrorder(int id) {
		tr.delete(id);
	}

	// 正確實作 findAllTrorderByMemberId
	@Override
	public List<Trorder> findAllTrorderByMemberId(String memberNo) {
		return tr.selectByMemberNo(memberNo);
	}
}
