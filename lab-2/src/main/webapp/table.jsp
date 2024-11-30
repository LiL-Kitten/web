<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="stars.lab2.bean.Data" %>
<%@ page import="java.util.List" %>
<%@ page import="stars.lab2.bean.DataList" %>

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
    DataList dataItems = (DataList) request.getAttribute("list");
    List<Data> dataList = dataItems != null ? dataItems.getList() : null;

    if (dataList != null && !dataList.isEmpty()) {
      for (Data data : dataList) {
  %>
  <tr>
    <td><%= data.getX() %></td>
    <td><%= data.getY() %></td>
    <td><%= data.getR() %></td>
    <td><%= data.getTime() %></td>
    <td><%= data.getDate() %></td>
    <td><%= data.isCondition() ? "Попал" : "Промазал" %></td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="6">Нет данных для отображения</td>
  </tr>
  <%
    }
  %>
</table>
