<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lịch sử phiếu mượn</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
    <!-- Thêm thư viện jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        
        function fetchUserData() {
        var userId = document.getElementById("userId").value;
        var url = "PhieuMuonServlet";
        var xhr = new XMLHttpRequest();

        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var data = JSON.parse(xhr.responseText);
                var tableBody = document.getElementById("phieuMuonTable");
                tableBody.innerHTML = "";

                for (var i = 0; i < data.length; i++) {
                    var phieuMuonDTO = data[i];
                    var row = document.createElement("tr");
                    var cell1 = document.createElement("td");
                    cell1.textContent = phieuMuonDTO.phieuMuon.maPhieuMuon;
                    row.appendChild(cell1);
                    var cell2 = document.createElement("td");
                    cell2.textContent = phieuMuonDTO.phieuMuon.ngayMuon;
                    row.appendChild(cell2);
                    var cell3 = document.createElement("td");
                    cell3.textContent = phieuMuonDTO.phieuMuon.ngayHenTra;
                    row.appendChild(cell3);
                    var cell4 = document.createElement("td");
                    cell4.textContent = phieuMuonDTO.phieutra.ngayTra;
                    row.appendChild(cell4);
                    var cell5 = document.createElement("td");
                    cell5.textContent = phieuMuonDTO.phieuMuonChiTiet.slSachMuonMoiLoai;
                    row.appendChild(cell5);
                    var cell6 = document.createElement("td");
                    cell6.textContent = phieuMuonDTO.sach.tieuDe;
                    row.appendChild(cell6);
                    tableBody.appendChild(row);
                }
            }
        };

        xhr.open("GET", url + "?userId=" + encodeURIComponent(userId), true);
        xhr.send();
    }
    </script>
</head>
<body>
<jsp:include page="Menu.jsp"></jsp:include>
<div class="shopping-cart">
    <div class="px-4 px-lg-0">
        <div class="pb-5">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">
                        <div class="table-responsive">
                            <form action="LibraryCardSearchControl" method="post" class ="form">
                                <div class="form-group">
                                    <label for="userId">Nhập mã người dùng:</label>
                                    <input type="text" class="form-control" id="userId" name="userId">
                                </div>
                                <!--<button type="submit" class="btn btn-primary" onclick="fetchUserData()">Xác nhận</button>-->
                                <button type="submit" class="btn btn-primary">Xác nhận</button>
                                <br><br>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th scope="col">Mã phiếu mượn</th>
                                        <th scope="col">Ngày mượn</th>
                                        <th scope="col">Ngày hẹn trả</th>
                                        <!--<th scope="col">Ngày trả</th>-->
                                        <th scope="col">Số lượng sách mượn</th>
                                        <th scope="col">Ghi chú</th>
                                        <!--<th scope="col">Tiêu đề sách</th>-->
                                    </tr>
                                    </thead>
                                    <tbody id="phieuMuonTable">
                                        <c:if test="${not empty list}">
                                        <c:forEach var="phieuMuon" items="${list}">
                                            <tr>
                                                <td>${phieuMuon.maPhieuMuon}</td>
                                                <td>${phieuMuon.ngayMuon}</td>
                                                <td>${phieuMuon.ngayHenTra}</td>
                                                <td>${phieuMuon.tongSoLuongSachMuon}</td>
                                                <td>${phieuMuon.ghiChu}</td>
                                            </tr>
                                        </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
</body>
</html>
