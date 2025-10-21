package po.service.impl;

import java.util.List;

import po.TrorderDetail;
import po.dao.impl.TrorderDetailDaoImpl;
import po.service.TrorderDetailService;

public class TrorderDetailServiceImpl implements TrorderDetailService {

	private TrorderDetailDaoImpl trorderDetailDao;

	public TrorderDetailServiceImpl() {
		this.trorderDetailDao = new TrorderDetailDaoImpl();
	}


	// Create
	@Override
	public void addTrorderDetail(TrorderDetail trorderDetail) {
		trorderDetailDao.add(trorderDetail);
	}


	// Read
	@Override
	public List<TrorderDetail> findAllTrorderDetail() {
		return trorderDetailDao.selectAll();
	}

	@Override
	public TrorderDetail findById(int id) {
		return trorderDetailDao.selectById(id);
	}

	//根據訂單編號取得所有細項
	@Override
	public List<TrorderDetail> findByTrorderNo(String trorderNo) {
		return trorderDetailDao.selectByTrorderNo(trorderNo);
	}

	// Update
	@Override
	public void updateTrorderDetail(TrorderDetail trorderDetail) {
		trorderDetailDao.update(trorderDetail);
	}

	
	// Delete
	@Override
	public void deleteTrorderDetail(int id) {
		trorderDetailDao.delete(id);
	}

	
	public void deleteByTrorderNo(String trorderNo) {
		trorderDetailDao.deleteByTrorderNo(trorderNo);
	}
}
