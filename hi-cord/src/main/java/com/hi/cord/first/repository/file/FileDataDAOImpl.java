package com.hi.cord.first.repository.file;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.hi.cord.common.model.Paging;
import com.hi.cord.first.entity.file.FileData;
import com.hi.cord.first.repository.AbstractDao;

@Repository("fileDataDao")
public class FileDataDAOImpl extends AbstractDao<Long, FileData> implements FileDataDAO {
	static final Logger log = LoggerFactory.getLogger(FileDataDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<FileData> findAll(FileData fileData) {
		log.info("TEST : findAll"+fileData.toString());
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("fileDataId")).add(Restrictions.eq("fileDataDelCheck", 0))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<FileData> fileList =criteria.list();
		log.info("Parameter : "+fileList.toString());
		return fileList;
	}
	
	@Override
	public FileData findById(Long id) {
		FileData fileData = getByKey(id);
		return fileData;
	}

	@Override
	public void save(FileData fileData) {
		persist(fileData);
	}

	@Override
	public void delete(Long id) {
		delete(id);
	}

	
	@Override
	public int getCount(Paging paging) {
		String condition = "";
		if (paging.getTableName() != null) {
			condition = "BOARD WHERE BOARD_TYPE='" + paging.getTableName() + "'";
		}
		Query query = rawQuery("SELECT COUNT(*) FROM " + condition);
		return ((Number) query.uniqueResult()).intValue();
	}
//	@Override
//	public int getCount2(Paging paging) {
//		// 검색 로직
//		Criteria criteria = createEntityCriteria().addOrder(Order.desc("id")).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		int count=((Integer)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
//		return count;
//	}
}
