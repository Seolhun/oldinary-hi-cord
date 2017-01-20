package com.brain.home.service.board.reply;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.brain.home.entity.board.Reply;
import com.brain.home.entity.common.Paging;
import com.brain.home.repository.board.reply.ReplyDAO;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	protected ReplyDAO dao;

	@Override
	public void save(Reply reply) {
		dao.save(reply);
	}

	@Override
	public Boolean delete(Long id) {
		Reply temp = dao.findById(id);
		
		if (temp != null) {
			dao.delete(temp.getId());
			return true;
		}
		return false;
	}

	@Override
	public Reply update(Reply reply) {
		Reply entity = dao.findById(reply.getId());
		if (entity != null) {
			entity.setSubject(reply.getSubject());
			entity.setContent(reply.getContent());
		}

		return entity;
	}

	@Override
	public Reply findById(Long id) {
		return dao.findById(id);
	}

	@Override
	public List<Reply> findAll(Paging paging) {
		return dao.findAll(paging);
	}

	@Override
	public int getCount(Paging paging) {
		return dao.getCount(paging);
	}

}
