package member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBMng;

/**
 * Servlet implementation class Login
 */
@WebServlet("/member/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 
     * @param param1 id
     * @param param2 pw
     * @return id, pw�� �ǹ� �ִ� �� 
     */
    
    private boolean checkParameter(String param1,String param2) {
		if(param1 == null || param2 == null) {
			return false;
		}
		
		// �Ϲ������� id, pw���� �յ� ������ ������� ����
		param1 = param1.trim();
		param2 = param2.trim();
		if(param1.length() == 0 || param2.length() == 0) {
			// id �Ǵ� pw�� ���� ���ڸ� ���޵Ǽ� trim�� ���� ��
			// ���ڿ�
			return false;
		}
		
		// id ���ڼ����� pw ���ڼ�����
		if(param1.length() > 20 || param2.length() > 16) {
			return false;
		}
		
		return true;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		boolean rightParameter = checkParameter(id,pw);
		if(rightParameter == false) {
			response.getWriter().print("login failure");
			
			return;
		}
		

 		 
		try {
			// DB Ŀ�ؼ� ����
			Connection conn = DBMng.getConnection();
			
			// id, pw�� ����ؼ� select ����
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id =? AND pw = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				// �α��� ����
				response.getWriter().print("login success");
			} else {
				// ���� ����� ���ٸ�
				// �α��� ����
				response.getWriter().print("login failure2");
			}
		
		} catch(SQLException e) {
			
		} catch(NullPointerException e) {
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
