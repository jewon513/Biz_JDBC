#주소록 프로젝트

*	오라클의 tbl_addr 테이블을 참조하여 주소록 프로젝트 수행
*	base package : com.biz.addr
*	~.persistence : AddrDTO 
*	~.service : AddrServiceV1
								selectAll();
								findById(long id);
								findByName(String name);
								findByTel(String tel);
								findBychain(String chain);

*	오라클 DBMS에 접속하기 위해 ojdbc를 설정
*	lombok을 설정

*	Test를 위해서  ~.exec : AddrEx_**