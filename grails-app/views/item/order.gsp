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
    <tr><td>${item.name}</td><td><i:orderItem item="${item}"/></td></tr>
</g:each>
</table>
</body>
</html>