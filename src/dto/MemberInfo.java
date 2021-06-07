package dto;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import exception.EmptyMemberInfoException;
import exception.OverflowMemberInfoException;

public class MemberInfo {
	private String id;
	private String pw;
	private String joinDate;
	
	public MemberInfo(HttpServletRequest request) throws EmptyMemberInfoException, OverflowMemberInfoException {
		this.id = request.getParameter("id");
		this.pw = request.getParameter("pw");
		
		if(id == null || pw == null) {
			// ���̵� �Ǵ� ��й�ȣ�� ���޵��� �ʾҴٸ���
			throw new EmptyMemberInfoException("���̵� �Ǵ� ��й�ȣ�� ���޵��� �ʾҽ��ϴ�.");
		} else if(id.length() > 20 || pw.length() > 16) {
			// ���̵� 20�� �ʰ� ��й�ȣ�� 16�� �ʰ� �Ѵٸ�
			throw new OverflowMemberInfoException("���̵� �Ǵ� ��й�ȣ�� ������ ���̸� �ʰ��߽��ϴ�");
 		} else if(id.trim().length() == 0 || pw.trim().length() == 0) {
 			throw new EmptyMemberInfoException("��ĭ�� ������ �ȵ˴ϴ�");
 			// ���̵�� ��й�ȣ ����
 		}
		
		MemberInfo memberLoginInfo = new MemberInfo(request);
	}
	
	public MemberInfo(HttpServletRequest request, LocalDateTime joinDate) throws EmptyMemberInfoException, OverflowMemberInfoException {
		this(request);
		
		this.joinDate = joinDate.toString();
	}
	
 	public MemberInfo(String id, String pw) {
 		this.id = id;
 		this.pw = pw;
 	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	
}
