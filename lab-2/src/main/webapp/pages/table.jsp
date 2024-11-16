<%@ page import="stars.lab2.bean.Data" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="list" scope="session" class="stars.lab2.bean.DataList"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="history">
  <tr>
    <th>X</th>
    <th>Y</th>
    <th>R</th>
    <th>время выполнения</th>
    <th>дата</th>
    <th>результат</th>
  </tr>

  <%
    List<Data> arr = list.getList();
    for (int i = arr.size() - 1; i >= 0; i--) {
  %>
  <tr>
    <td><%=arr.get(i).getX()%>
    </td>
    <td><%=arr.get(i).getY()%>
    </td>
    <td><%=arr.get(i).getR()%>
    </td>
    <td><%=arr.get(i).getTime()%>
    </td>
    <td><%=arr.get(i).getDate()%>
    </td>
    <td><%=arr.get(i).isCondition() ? "Попал" : "Промазал"%>
    </td>
  </tr>
  <%}%>

</table>
