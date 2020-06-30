package com.springbook.biz.board;
import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	public static void main(String[] args) {
		// 컨테이너 구동
		AbstractApplicationContext container = 
				new GenericXmlApplicationContext("applicationContext.xml");
		// spring컨테이너로부터 BoardServiceImpl 객체를 LookUp
		BoardService boardService = (BoardService) container.getBean("boardService");
		
		// 글 등록 테스
		BoardVO vo = new BoardVO();
		vo.setTitle("ㅁㄴ");
		vo.setWriter("ㅈㅂㅇㅂㅈㅇㅂㅇㅂ");
		vo.setContent("3");
		boardService.insertBoard(vo);
		
		
		
		
		// 글 목록 검색 기능 테스트
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for(BoardVO board : boardList) {
			System.out.println("==>" + board.toString());
		}
		
		// Spring 컨테이너 종료
		container.close();
	}
}
