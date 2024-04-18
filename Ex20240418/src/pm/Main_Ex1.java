package pm;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main_Ex1 {

	SqlSessionFactory factory;
	

	public Main_Ex1() throws Exception {
		makeFactory();
	}
	
	private void makeFactory() throws Exception { // - 생성자에서 단 한번만 호출되도록
		Reader r = Resources.getResourceAsReader("pm/config.xml");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		factory = builder.build(r);
		r.close();
		
	}
	
	private void search_name(String m_name) {
		SqlSession ss = factory.openSession();
		
		List<MemVO> list = ss.selectList("mem.search_name",m_name);
		
		printList(list);
		
		if(ss != null) {
			ss.close();
		}
	}
	
	private void search(String m_email, String m_phone) {
		SqlSession ss = factory.openSession();
		
		Map<String, String> memap = new HashMap<String, String>();
		
		
		
		if(m_email != null) {
			memap.put("m_email", m_email);
		}
		if(m_phone != null) {
			memap.put("m_phone", m_phone);
		}
		
		
		if(m_email == null && m_phone == null) {
			if(ss != null) {
				ss.close();
			}
			return;
		}
		
		List<MemVO> list = ss.selectList("mem.search",memap);
		
		printList(list);
		
		if(ss != null) {
			ss.close();
		}
		
	}
	
	private void printList(List<MemVO> list) {
		for(int i=0; i<list.size(); i++) {
			MemVO vo = list.get(i);
			System.out.printf("%2s | %-6s | %-5s | %-22s | %-15s\r\n",vo.getM_idx(), vo.getM_id(), vo.getM_name(), vo.getM_email(), vo.getM_phone());
		}
	}
	
		
	
	public static void main(String[] args) throws Exception {
		
		Main_Ex1 ex1 = new Main_Ex1();
		
//		Scanner scan = new Scanner(System.in);
//		
//		System.out.print("검색할 이름 입력 : ");
//		String m_name = scan.nextLine();
//		
//		System.out.println("----------------------- search_name() -----------------------");
//		ex1.search_name(m_name);
//		System.out.println();
//		
//		System.out.print("검색할 이메일 입력 : ");
//		String m_email = scan.nextLine();
//		System.out.print("같이 검색할 연락처 입력 : ");
//		String m_phone = scan.nextLine();
//		System.out.println("-------------------------- search() -------------------------");
//		ex1.search(m_email, m_phone);
		
		ex1.search("ee", null);
		System.out.println();
		ex1.search(null, "001");
		System.out.println();
		ex1.search(null, null);
		System.out.println();
		ex1.search("ke", "03");
		
		
		
	}

}
