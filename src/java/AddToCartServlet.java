import dao.Sachdao;
import entity.Sach;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");

        // Lấy thông tin sản phẩm từ ID
        Sachdao sachdao = new Sachdao();
        Sach product = sachdao.getProductById(productId);

        // Kiểm tra giỏ hàng
        List<Sach> cart = (List<Sach>) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
            request.getSession().setAttribute("cart", cart);
        }

        // Thêm sản phẩm vào giỏ hàng
        cart.add(product);
        
        // Redirect về trang giỏ hàng
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
