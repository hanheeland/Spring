package org.zerock.service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper mapper;
	
	@Override
	public void register(BoardVO board) {
		log.info("register.......");
		mapper.insertSelectKey(board);
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("getList.......");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("getListPaging.......");
		return mapper.getListWithPaging(cri);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get.......");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify.......");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove......");
		return mapper.delete(bno) == 1;
	}

	@Override
	public int getTotal(Criteria cri) {
		return mapper.getTotalCount(cri);
	}
	

}
