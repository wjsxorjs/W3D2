package am;


import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main_Ex1 {
	
	SqlSessionFactory factory;
	
	public Main_Ex1() {
		makeFactory(); // DB를 활용할 수 있는 factory 생성
	}
	
	private void makeFactory() {
		try {
			Reader r = Resources.getResourceAsReader("am/config.xml");
			
			factory = new SqlSessionFactoryBuilder().build(r);
			r.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	// 저장 기능
	public void add(String empno, String ename, String job, String sal, String hiredate, String deptno) {
		//myBatis환경에 인자로 전달할 객체 생성
		EmpVO vo = new EmpVO(empno, ename, job, sal, hiredate, deptno);
		
		//위의 객체를 인자로 보내 저장시키면 된다.
		
		SqlSession ss = factory.openSession();
		int cnt = ss.insert("emp.add", vo); // 저장한 레코드의 수
		
		//cnt에는 저장을 수행한 레코드 수가 저장된다.
		
		if(cnt>0) {
			System.out.println("저장 완료");
			ss.commit();
		} else {
			System.out.println("저장 실패");
			ss.rollback();
		}
		
	}
	
	private void login(String id, String pw) {
		// 로그인 SQL문장을 호출하기 위해 SqlSession을 얻어낸다.
		SqlSession ss = factory.openSession();
		
		
		MemberVO mvo = new MemberVO();
		mvo.setM_id(id);
		mvo.setM_pw(pw);
		
		// 로그인은 결과객체가 1개다.
		
		MemberVO vo = ss.selectOne("member.login", mvo);
		
		if(vo != null) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("아이디 또는 비밀번호가 다릅니다.");
		}
		
		
		if(ss != null) {
			ss.close();
		}

	}
	
	
	private void login2(String id, String pw) {
		// 로그인 SQL문장을 호출하기 위해 SqlSession을 얻어낸다.
		SqlSession ss = factory.openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("m_id", id);
		map.put("m_pw", pw);
		
		MemberVO vo = ss.selectOne("member.login2", map);
		
		if(vo != null) {
			System.out.println("로그인 성공");
		} else {
			System.out.println("아이디 또는 비밀번호가 다릅니다.");
		}
		
		
		if(ss != null) {
			ss.close();
		}
		
	}
	

	public static void main(String[] args) throws Exception {
		Main_Ex1 ex1 = new Main_Ex1();
		
		
		
		// 저장을 하기 위해서 Main_Ex1 안에 있는 add를 호출한다.
//		ex1.add("7962", "JAKE", "DEVELOPER", "1400", "1983-01-11", "40");
		
		Scanner scan = new Scanner(System.in);
		System.out.print("아이디: ");
		String id = scan.nextLine();
		System.out.print("비밀번호: ");
		String pw = scan.nextLine();
		
		System.out.print("login() : ");
		ex1.login(id, pw);
		System.out.print("login2() : ");
		ex1.login2(id, pw);

	}

}
