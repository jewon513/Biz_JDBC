package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV4 {

	SqlSession sqlSession;
	Scanner scan;
	
	public BBsServiceV4(){
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);
	}
	
	public void bbsMenu() {
		while(true) {
			this.viewBBsList();
			System.out.println("내용보기(SQ입력) W.작성 U.수정 D.삭제 Q.종료");
			System.out.println("--------------------------------------------------------");
			System.out.print("선택하세요 >> ");
			String strMenu = scan.nextLine();
			
			if(strMenu.equalsIgnoreCase("Q")) {
				return;
			}else if(strMenu.equalsIgnoreCase("W")) {
				this.writeBBS();
				this.viewBBsList();
			}else if(strMenu.equalsIgnoreCase("U")) {
				this.viewBBsList();
				this.updateBBS();
			}else if(strMenu.equalsIgnoreCase("D")) {
				this.deleteBBS();
				this.viewBBsList();
			}else {
				try {
					long longSEQ = Integer.valueOf(strMenu);
					this.viewText(longSEQ);
				} catch (Exception e) {
					
				}
			}
		}
	}
	
	public void viewText(long bs_id) {
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO = bbsDao.findByid(bs_id);
		if(bbsDTO == null) {
			System.out.println("내용이 없습니다.");
		}else {
			System.out.println("========================================================");
			System.out.println("제목 : " + bbsDTO.getBs_subject());
			System.out.println("작성자 : " + bbsDTO.getBs_writer());
			System.out.println("작성일 : " + bbsDTO.getBs_date());
			System.out.println("작성시각 : " + bbsDTO.getBs_time());
			System.out.println("--------------------------------------------------------");
			System.out.println(bbsDTO.getBs_text());
			System.out.println("========================================================");
		}
	}
	
	public void updateBBS() {
		// 수정할 ID를 입력받고, 
		// 내용을 한번 보여주고, 각 항목을 입력받고, Enter를 입력하면 원래 데이터 유지, 새로 입력하면 값 수정
		System.out.println("=======================================================");
		System.out.print("수정할 SQ (-Q : 종료 ) >> ");
		String strId = scan.nextLine();
		
		long longId = 0;
		try {
			longId = Long.valueOf(strId);
			this.viewText(longId);
		} catch (Exception e) {
			System.out.println("찾는 SQ가 없습니다.");
			return;
			// TODO: handle exception
		}
		
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO = bbsDao.findByid(longId);
		
		if(bbsDTO == null) {
			System.out.println("찾는 SQ가 없습니다.");
		}
		
		System.out.printf("[현재 제목 : %s] 수정할 제목 입력 (-Q : 종료 ) >> " , bbsDTO.getBs_subject());
		String strSubject = scan.nextLine();
		if(strSubject.equals("-Q")) return;
		if(strSubject.trim().length()>0) {
			bbsDTO.setBs_subject(strSubject);
		}
		
		System.out.printf("[현재 작성자 : %s] 수정할 작성자 입력 (-Q : 종료 ) >> " , bbsDTO.getBs_writer());
		String strWriter = scan.nextLine();
		if(strWriter.equals("-Q")) return;
		if(strWriter.trim().length()>0) {
			bbsDTO.setBs_writer(strWriter);
		}
		
		System.out.printf("[현재 내용 : %s] 수정할 내용 입력 (-Q : 종료 ) >> " , bbsDTO.getBs_text());
		String strText = scan.nextLine();
		if(strText.equals("-Q")) return;
		if(strText.trim().length()>0) {
			Date date = new Date(System.currentTimeMillis());
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat tf = new SimpleDateFormat("hh:mm:dd");
			
			String CurDate = df.format(date);
			String CurTime = tf.format(date);
			bbsDTO.setBs_text(strText+"\n\n"+"수정한 날짜 : " + CurDate +"\n"+"수정한 시간 : " + CurTime);
		}
		
		int ret = bbsDao.update(bbsDTO);
		
		if(ret>0) {
			System.out.println("수정 완료");
		}else {
			System.out.println("수정 실패");
		}
		
	}
	
	public void deleteBBS() {
		// 삭제할 게시판 ID를 입력 받고
		// 해당 ID의 내용을 보여주고
		// 삭제의 여부를 물어보고
		// 삭제를 진행
		System.out.println("=======================================================");
		System.out.println("삭제할 ID (-Q : 종료) ");
		String strId = scan.nextLine();
		
		try {
			long longId = Long.valueOf(strId);
			this.viewText(longId);
			System.out.println("--------------------------------------------------------");
			System.out.print("삭제 할까요? (YES/NO) >> ");
			String yesNO = scan.nextLine();
			if(yesNO.equals("YES")) {
				
				BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
				bbsDao.delete(longId);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public void writeBBS() {
		// TODO Auto-generated method stub
		
		/*
		 * 작성자, 제목, 내용을 입력하지 않으면 다시 메세지를 보여주고 다시 입력을 받도록 로직.
		 * bs_name, bs_subject, bs_text는 NOT NULL이기 때문임.
		 */
		while(true) {
			
			System.out.println("=======================================================");
			System.out.println("\t\t게시글 작성");
			System.out.println("=======================================================");
			
			System.out.print("작성자 (-Q : 종료 )>> ");
			String strWriter = scan.nextLine();
			if(strWriter.equals("-Q")) break;
			
			if(strWriter.trim().length()<1) {
				System.out.println("작성자는 필수로 입력해야 합니다.");
				continue;
			}
			
			System.out.print("제목 (-Q : 종료 ) >> ");
			String strSubject = scan.nextLine();
			if(strSubject.equals("-Q")) break;
			
			if(strSubject.trim().length()<1) {
				System.out.println("제목은 필수로 입력해야 합니다.");
				continue;
			}
			
			System.out.print("내용 (-Q : 종료 ) >> ");
			String strText = scan.nextLine();
			if(strText.equals("-Q")) break;
			
			if(strText.trim().length()<1) {
				System.out.println("내용은 필수로 입력해야 합니다.");
				continue;
			}
			
			
			/*
			 * 작성일자,  작성시각은 컴퓨터 시간을 참조하여 자동생성.
			 */
			// java 1.7 이하의 코드로 작성
			
			// 컴퓨터의 현재 시각을 가져오기
			Date date = new Date(System.currentTimeMillis());
			
			// date 날짜형 값을 "2019-10-24"의 문자열형으로 변환
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			// date 날짜형 값을 "14:17:00"의 문자열형으로 변환
			SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss");
			
			String curDate = df.format(date);
			String curTime = tf.format(date);
			
			// 입력받은 데이터와 날짜, 시각을 DTO에 담기
			BBsDTO bbsDTO = BBsDTO.builder().
					bs_date(curDate).
					bs_time(curTime).
					bs_writer(strWriter).
					bs_subject(strSubject).
					bs_text(strText).build();
			
			BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
			
			int ret = bbsDao.insert(bbsDTO);
			
			if(ret > 0) {
				System.out.println("게시글 작성 완료");
			}else {
				System.out.println("게시글 작성 실패");
			}
			
			break;
			
		}
		
	}

	public void viewBBsList() {
		
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bbsDao.selectAll();
		System.out.println("========================================================");
		System.out.println("\t\t슈퍼 BBS V1");
		System.out.println("========================================================");
		System.out.println("SQ\t작성일\t\t시각\t\t작성자\t\t제목");
		for (BBsDTO bbsDTO : bbsList) {
			System.out.print(bbsDTO.getBs_id() + "\t");
			System.out.print(bbsDTO.getBs_date() + "\t");
			System.out.print(bbsDTO.getBs_time() + "\t");
			System.out.print(bbsDTO.getBs_writer() + "\t\t");
			System.out.print(bbsDTO.getBs_subject() + "\n");
		}
		System.out.println("========================================================");
		
	}
	
	
	
	
}