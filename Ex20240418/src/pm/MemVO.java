package pm;

public class MemVO {
	// member_t테이블에서 필요로 하는 컬럼들을 멤버 변수로 선언
	// 회원번호, 아이디, 이름, 이메일, 전화번호
	
	private String m_idx, m_id, m_name, m_email, m_phone;

	public void setM_idx(String m_idx) {
		this.m_idx = m_idx;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}

	
	
	public String getM_idx() {
		return m_idx;
	}

	public String getM_id() {
		return m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public String getM_email() {
		return m_email;
	}

	public String getM_phone() {
		return m_phone;
	}
	
	
	

}
