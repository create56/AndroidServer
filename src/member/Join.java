package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberService;
import dto.MemberInfo;
import exception.EmptyMemberInfoException;
import exception.OverflowMemberInfoException;
import util.DBMng;

/**
 * Servlet implementation class Join
 */
@WebServlet("/member/join")
public class Join extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Join() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private boolean checkParameter(MemberInfo memberJoinInfo) {
    	String id = memberJoinInfo.getId();
    	String pw = memberJoinInfo.getPw();
    	if(id == null || pw == null) {
    		return false;
    	} else if(id.length() > 20 || pw.length() > 16) {
    		return false;
    	} else if(id.trim().length() == 0 || pw.trim().length() == 0) {
    		return false;
    	}
		return false;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			MemberInfo memberLoginInfo = new MemberInfo(request, LocalDateTime.now());
			// ȸ������ �� ���̵� 
			String id = request.getParameter("ID");
			// ȸ������ �� ��й�ȣ
			String pw = request.getParameter("PW");

			MemberService ms = new MemberService();
			boolean isJoin = ms.join(memberJoinInfo);
			
			if(isJoin) {
				// ȸ������ ������
				response.setStatus(201);
			} else {
				// ȸ�������� ���� �ʾҴٸ�(���̵� �ߺ�)
				response.setStatus(400);
			}
		}catch(EmptyMemberInfoException | OverflowMemberInfoException e) {
			// ���� ����
			response.setStatus(400);
		}catch(SQLException e) {
			response.setStatus(500);
		}
		
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
