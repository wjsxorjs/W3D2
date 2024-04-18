package pm;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Main_Ex1 {

	SqlSessionFactory factory;
	
	
	
	private void makeFactory() throws Exception { // - 생성자에서 단 한번만 호출되도록
		Reader r = Resources.getResourceAsReader("pm/config.xml");
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		
		factory = builder.build(r);
		r.close();
		
	}
	
	private void search_name(String m_name) {
		SqlSession ss = factory.openSession();
		
		List<MemVO> list = ss.selectList("mem.search_name",m_name);
		
		for(int i=0; i<list.size(); i++) {
			MemVO vo = list.get(i);
			System.out.printf("%2s | %-6s | %-5s | %-22s | %-15s\r\n",vo.getM_idx(), vo.getM_id(), vo.getM_name(), vo.getM_email(), vo.getM_phone());
		}
		
		if(ss != null) {
			ss.close();
		}
	}
	
	private void search(String m_email, String m_phone) {
		SqlSession ss = factory.openSession();
		
		Map<String, String> memap = new HashMap<String, String>();
		
		memap.put("m_email", m_email);
		memap.put("m_phone", m_phone);
		
		
		List<MemVO> list = ss.selectList("mem.search",memap);
		
		for(int i=0; i<list.size(); i++) {
			MemVO vo = list.get(i);
			System.out.printf("%2s | %-6s | %-5s | %-22s | %-15s\r\n",vo.getM_idx(), vo.getM_id(), vo.getM_name(), vo.getM_email(), vo.getM_phone());
		}
		
		if(ss != null) {
			ss.close();
		}
		
	}
	
	
	public Main_Ex1() throws Exception {
		makeFactory();
	}
	
	
	public static void main(String[] args) throws Exception {
		
		Main_Ex1 ex1 = new Main_Ex1();
		System.out.println("----------------------- search_name() -----------------------");
		ex1.search_name("JA");
		System.out.println();
		System.out.println("-------------------------- search() -------------------------");
		ex1.search("ee","0000");
	}

}
