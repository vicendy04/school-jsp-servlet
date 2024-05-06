<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        
    </head>
    <body>
        <!--begin of menu-->
       <jsp:include page="Menu.jsp"></jsp:include>
        <!--end of menu-->
        <div class="container">
            <div class="row">
                <div class="col">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="Home.jsp">Home</a></li>
                            <li class="breadcrumb-item"><a href="#">Loại sách</a></li>
                            <li class="breadcrumb-item active" aria-current="#">Danh mục phụ</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> LOẠI SÁCH </div>
                        <ul class="list-group category_block">
                         <c:forEach items="${listLS}" var="loaiSach">
    <li class="list-group-item text-white ${tag == loaiSach.getMaLoaiSach() ? 'active' : ''}">
        <a href="CategoryControl?MaLoaiSach=${loaiSach.getMaLoaiSach()}">${loaiSach.getTenLoaiSach()}</a>
    </li>
</c:forEach>
                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Sách mới</div>
                        <div class="card-body">
                            <img class="img-fluid" src="images\sach\sach910.jpg" />
                            <h5 class="card-title">Tiêu đề : ${p.tieuDe}</h5>
                            <p class="card-text">Nhà xuất bản : ${p.nhaXuatBan}</p>
                            <p class="bloc_left_price">Tác giả : ${p.tacGia}</p>
                        </div>
                    </div>
                </div>

            <div class="col-sm-9">
                        <div id="content" class="row">
                        <c:forEach items="${listS}" var="o" varStatus="loop">
                            <div class="product col-12 col-md-6 col-lg-4">
                                <div class="card">
                                       <c:if test="${not empty listAnh}">
                                            <img class="card-img-top" src="<c:url value='/images/sach/${listAnh[loop.index % listAnh.size()]}'/>" alt="Ảnh" />
</c:if>
                                    <div class="card-body">
                                        <h4 class="card-title show_txt"><a href="detail?pid=${o.maSach}" title="View Product">${o.tieuDe}</a></h4>
                                        <p class="card-text show_txt">${o.nhaXuatBan}</p>
                                        <p class="card-text show_txt">Tác giả : ${o.tacGia}</p>
                                        <div class="row">
                        
                                            <div class="col">
                                                 <form action="AddToCartServlet" method="post">
                                                <button type="submit" class="btn btn-success btn-block">Thêm vào giỏ hàng</button>
                                                <input type="hidden" name="productId" value="${o.maSach}">
                                            </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <button onclick="loadMore()" class="btn btn-primary">Load more</button>
                </div>                    

            </div>
        </div>

        <!-- Footer -->
        <footer class="text-light">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-lg-4 col-xl-3">
                        <h5>About</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <p class="mb-0">
                            <li>Trần Hoàng</li>
                            <li> Phan Minh Tài</li>
                            <li>Nguyễn Trường Vũ</li>
                            <li>Nguyễn Xuân Hoàng</li>
                            <li>Đỗ Thành Bảo</li>
                        </p>
                    </div>

                    <div class="col-md-2 col-lg-2 col-xl-2 mx-auto">
                        <h5>Informations</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-3 col-lg-2 col-xl-2 mx-auto">
                        <h5>Others links</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><a href="">Link 1</a></li>
                            <li><a href="">Link 2</a></li>
                            <li><a href="">Link 3</a></li>
                            <li><a href="">Link 4</a></li>
                        </ul>
                    </div>

                    <div class="col-md-4 col-lg-3 col-xl-3">
                        <h5>Contact</h5>
                        <hr class="bg-white mb-2 mt-0 d-inline-block mx-auto w-25">
                        <ul class="list-unstyled">
                            <li><i class="fa fa-home mr-2"></i> My company</li>
                            <li><i class="fa fa-envelope mr-2"></i> email@example.com</li>
                            <li><i class="fa fa-phone mr-2"></i> + 33 12 14 15 16</li>
                            <li><i class="fa fa-print mr-2"></i> + 33 12 14 15 16</li>
                        </ul>
                    </div>
                    <div class="col-12 copyright mt-3">
                        <p class="float-left">
                            <a href="#">Back to top</a>
                        </p>
                        <p class="text-right text-muted">created with <i class="fa fa-heart"></i> by <a href="https://t-php.fr/43-theme-ecommerce-bootstrap-4.html"><i>t-php</i></a> | <span>v. 1.0</span></p>
                    </div>
                </div>
            </div>
        </footer>
        
    </body>
</html>

