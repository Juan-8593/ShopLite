<%--
  Created by IntelliJ IDEA.
  User: juani
  Date: 8/20/2025
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Producto</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container my-5">
    <h2>Editar Producto</h2>
    <form action="admin" method="post">
        <input type="hidden" name="id" value="${product.id}">
        <div class="mb-3">
            <label>Nombre</label>
            <input type="text" name="name" value="${product.name}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Precio</label>
            <input type="number" step="0.01" name="price" value="${product.price}" class="form-control" required>
        </div>
        <div class="mb-3">
            <label>Stock</label>
            <input type="number" name="stock" value="${product.stock}" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Guardar Cambios</button>
        <a href="admin" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
