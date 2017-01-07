<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Make Order</title>
    <meta name="layout" content="main"/>
</head>

<body>
<h1>Place an order:</h1>
<table>
<g:each in="${items}" var="item">
    <tr><td>${item.name}</td><td><g:link class="btn btn-default" controller="order" action="create" params="['item.id': item.id, amount: 1]">Order</g:link></td></tr>
</g:each>
</table>
</body>
</html>