import dao.Sachdao;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Sach;

@WebServlet(name = "DeleteFromCartServlet", urlPatterns = {"/DeleteFromCartServlet"})
public class DeleteFromCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy maSach từ request
        String maSachString = request.getParameter("maSach");
        
        // Chuyển maSach từ chuỗi sang số nguyên
        int maSach = Integer.parseInt(maSachString);

        // Kiểm tra giỏ hàng
        List<Sach> cart = (List<Sach>) request.getSession().getAttribute("cart");
        if (cart != null) {
            // Tìm sách trong giỏ hàng và xóa
            Iterator<Sach> iterator = cart.iterator();
            while (iterator.hasNext()) {
                Sach sach = iterator.next();
                if (sach.getMaSach() == maSach) {
                    iterator.remove();
                    break;
                }
            }
        }

        // Chuyển hướng đến trang giỏ hàng
        response.sendRedirect("Cart.jsp");
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