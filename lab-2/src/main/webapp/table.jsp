<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="list" scope="session" class="stars.lab2.bean.DataList"/>

<table class="history">
  <tr>
    <th>X</th>
    <th>Y</th>
    <th>R</th>
    <th>время выполнения</th>
    <th>дата</th>
    <th>результат</th>
  </tr>

    <c:forEach var="data" items="${list.list}">
     <tr>
        <td>${data.x}</td>
        <td>${data.y}</td>
        <td>${data.r}</td>
        <td>${data.time}</td>
        <td>${data.date}</td>
       <td>
         <c:choose>
           <c:when test="${data.condition}">Попал</c:when>
           <c:otherwise>Промазал</c:otherwise>
         </c:choose>
       </td>
    </c:forEach>

</table>
