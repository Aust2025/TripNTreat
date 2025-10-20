package po.service.impl;

import java.util.List;

import po.Trorder;
import po.TrorderDetail;
import po.dao.impl.TrorderDetailDaoImpl;
import po.service.TrorderDetailService;

public class TrorderDetailServiceImpl implements TrorderDetailService {

	private static TrorderDetailDaoImpl trorderDetailDao = new TrorderDetailDaoImpl();

	// create
	@Override
	public void addTrorderDetail(TrorderDetail trorderDetail) {
		trorderDetailDao.add(trorderDetail);
	}

	// read
	@Override
	public List<TrorderDetail> findAllTrorderDetail() {
		return trorderDetailDao.selectAll();
	}

	@Override
	public TrorderDetail findTrorderDetailById(int id) {
		return trorderDetailDao.selectById(id);
	}

	// update
	@Override
	public void updateTrorderDetail(TrorderDetail trorderDetail) {
		trorderDetailDao.update(trorderDetail);
	}

	// delete
	@Override
	public void deleteTrorderDetail(int id) {
		trorderDetailDao.delete(id);
	}
	
	// findAllTrorderByMemberId
	@Override
	public List<TrorderDetail> findAllTrorderByTrorderNo(String trorderNo) {
		return trorderDetailDao.selectByTrorderNo(trorderNo);
	}

}
