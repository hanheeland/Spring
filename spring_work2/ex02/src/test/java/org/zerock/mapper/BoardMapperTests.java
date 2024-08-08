package org.zerock.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Autowired
	private BoardMapper mapper;

//	@Test
//	public void test() { // 글 조회
//		mapper.getList().forEach(board-> log.info(board));
//	}
	
//	@Test
//	public void testInsert() { // 글 작성
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운글2");
//		board.setContent("새로작성한글2");
//		board.setWriter("newUser2");
//		
//		mapper.insert(board);
//	}
	
//	@Test
//	public void testInsert() {
//		BoardVO board = new BoardVO();
//		board.setTitle("새로운글3");
//		board.setContent("새로작성한글3");
//		board.setWriter("newUser2");
//		
//		mapper.insertSelectKey(board);
//		log.info(board);
//	}
	
//	@Test
//	public void testRead() { // 상세보기
//		BoardVO board = mapper.read(3L);
//		log.info(board);
//	}
	
//	@Test
//	public void testDelete() { // 글 삭제
//		log.info("Delete count: " + mapper.delete(3L));
//	}
	
	@Test
	public void testUpdate() { // 글 수정
		BoardVO board = new BoardVO();
		board.setBno(2L);
		board.setTitle("수정된제목2");
		board.setContent("수정된내용2");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("Update count: " + count);
	}

}
