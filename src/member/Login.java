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
     * @return id, pw가 의미 있는 값 
     */
    
    private boolean checkParameter(String param1,String param2) {
		if(param1 == null || param2 == null) {
			return false;
		}
		
		// 일반적으로 id, pw에는 앞뒤 공백을 허용하지 않음
		param1 = param1.trim();
		param2 = param2.trim();
		if(param1.length() == 0 || param2.length() == 0) {
			// id 또는 pw로 공백 문자만 전달되서 trim을 했을 떄
			// 빈문자열
			return false;
		}
		
		// id 글자수제한 pw 글자수제한
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
			// DB 커넥션 연동
			Connection conn = DBMng.getConnection();
			
			// id, pw를 사용해서 select 쿼리
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM user WHERE id =? AND pw = ?");
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				// 로그인 성공
				response.getWriter().print("login success");
			} else {
				// 쿼리 결과가 없다면
				// 로그인 실패
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
