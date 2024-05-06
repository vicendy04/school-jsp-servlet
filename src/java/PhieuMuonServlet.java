import dao.Sachdao;
import dto.PhieuMuonDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PhieuMuonServlet", urlPatterns = {"/PhieuMuonServlet"})
public class PhieuMuonServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String userId = request.getParameter("userId");

            // Lấy danh sách phiếu mượn của người dùng từ cơ sở dữ liệu
            Sachdao sachdao = new Sachdao();
            List<PhieuMuonDTO> listPhieuMuonDTO = sachdao.LSPhieuMuon(userId); 

            // Chuyển danh sách phiếu mượn sang dạng JSON và gửi về cho trang JSP
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        } catch (SQLException ex) {
            request.setAttribute("message", "Lỗi: Không thể truy vấn lịch sử phiếu mượn từ cơ sở dữ liệu");
            request.getRequestDispatcher("Cart.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
