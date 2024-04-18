package am;


import java.io.Reader;

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

	public static void main(String[] args) throws Exception {
		Main_Ex1 ex1 = new Main_Ex1();
		
		
		
		// 저장을 하기 위해서 Main_Ex1 안에 있는 add를 호출한다.
		ex1.add("7961", "JANE", "DEVELOPER", "1400", "1983-01-11", "40");

	}

}
