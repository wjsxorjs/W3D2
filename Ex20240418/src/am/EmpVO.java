package am;

public class EmpVO {
	String empno, ename, job, sal, hiredate, deptno;

	
	public EmpVO() {};
	
	public EmpVO(String empno, String ename, String job, String sal, String hiredate, String deptno) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.hiredate = hiredate;
		this.deptno = deptno;
	}

	public void setEmpno(String empno) {
		this.empno = empno;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSal(String sal) {
		this.sal = sal;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}

	
	
	public String getEmpno() {
		return empno;
	}

	public String getEname() {
		return ename;
	}

	public String getJob() {
		return job;
	}

	public String getSal() {
		return sal;
	}

	public String getHiredate() {
		return hiredate;
	}

	public String getDeptno() {
		return deptno;
	}
	
	

}
