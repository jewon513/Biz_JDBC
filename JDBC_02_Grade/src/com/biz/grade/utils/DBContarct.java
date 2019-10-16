package com.biz.grade.utils;

public class DBContarct {

	public static class TABLE {

		public static final String SCORE = " tbl_score ";
		public static final String STUDENT = " tbl_student ";
		public static final String DEPT = "tbl_dept";

	}

	public static class SQL {
		public static final String SCORE_SELECT = " SELECT s_id, s_std, s_subject, s_score, s_remark FROM tbl_score ";
		public static final String STUDENT_SELECT = " SELECT ST_NUM, " + 
													" ST_NAME, " + 
													" ST_TEL, " + 
													" ST_ADDR, " + 
													" ST_GRADE, " + 
													" ST_DEPT " + 
													" FROM tbl_student ";
	}

	public static class VIEW {

		public static final String SCORE = " view_score ";
		public static final String SCORE_PV = " view_score_pv ";

	}

	public static class DbConn {
		public static final String JdbcDriver = "oracle.jdbc.OracleDriver";
		public static final String URL = "jdbc:oracle:thin:@localHost:1521:xe";
		public static final String USER = "grade";
		public static final String PASSWORD = "grade";
	}

}
