<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<table class="history">
    <tr>
        <th>X</th>
        <th>Y</th>
        <th>R</th>
        <th>время выполнения</th>
        <th>дата</th>
        <th>результат</th>
    </tr>

    <%--  <jsp:useBean id="list" scope="session" class="stars.lab2.bean.DataList">--%>
    <%--    <c:forEach var="data" items="${list.list}">--%>
    <%--     <tr>--%>
    <%--        <td>${data.x}</td>--%>
    <%--        <td>${data.y}</td>--%>
    <%--        <td>${data.r}</td>--%>
    <%--        <td>${data.time}</td>--%>
    <%--        <td>${data.date}</td>--%>
    <%--       <td></td>--%>
    <%--    </c:forEach>--%>
    <%--  </jsp:useBean>--%>


    <c:set var="dataItems" value="#{list}" />
    <c:choose>
        <c:when test="${not empty dataItems.list}">
            <c:forEach var="data" items="${dataItems.list}">
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
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="6">Нет данных для отображения</td>
            </tr>
        </c:otherwise>
    </c:choose>
</table>
