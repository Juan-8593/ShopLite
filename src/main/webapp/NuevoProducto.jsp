<%--
  Created by IntelliJ IDEA.
  User: juani
  Date: 8/20/2025
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nuevo Producto - Admin</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg bg-white border-bottom">
    <div class="container">
        <a class="navbar-brand text-danger" href="${pageContext.request.contextPath}/admin">ShopLite • Admin</a>
    </div>
</nav>

<section class="container my-5" style="max-width:600px;">
    <c:if test="${param.err=='1'}">
        <div class="alert alert-danger">Error: Datos inválidos</div>
    </c:if>

    <div class="card shadow-sm">
        <div class="card-body p-4">
            <h3 class="mb-4">Nuevo Producto</h3>

            <form method="post" action="${pageContext.request.contextPath}/admin">
                <div class="mb-3">
                    <label for="name" class="form-label">Nombre del producto</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>

                <div class="mb-3">
                    <label for="price" class="form-label">Precio</label>
                    <input type="number" step="0.01" class="form-control" id="price" name="price" required>
                </div>

                <div class="mb-3">
                    <label for="stock" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="stock" name="stock" >
                </div>

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/admin" class="btn btn-outline-secondary">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Guardar Producto</button>
                </div>
            </form>
        </div>
    </div>
</section>

</body>
</html>

