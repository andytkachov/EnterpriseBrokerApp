<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="broker.informatorInterfaceRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
            
            if(request.getParameter("hmore") != null){
                Integer id = Integer.parseInt(request.getParameter("hmore"));
                informatorInterfaceRemote inf;
                inf = (informatorInterfaceRemote)(new javax.naming.InitialContext()).lookup("broker.informatorInterfaceRemote");
                Object sales = inf.getSalesListDateID(new Date(), id);
                pageContext.setAttribute("sales", sales);
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='styles/stl.css' rel='stylesheet' type='text/css'/>
        <title>More Information</title>
    </head>
    <body><div id='page-head'>
            
        <h1>Stock Exchange</h1>
        
        </div>  


        
        <div id='page-content'>

        <table id="myTable">
            <tr><th>Stock</th><th>Amount</th><th>Price</th><th>Broker</th></tr>
            
            <c:forEach var="sl" items="${sales}">
                    <tr>
                        <td><c:out value="${sl.stock.code} ${sl.stock.name}" /></td>
                        <td><c:out value="${sl.amount}" /></td>
                        <td><c:out value="${sl.price}" /></td> 
                        <td><c:out value="${sl.broker.lastname}" /></td>                         
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id='left'>
            <a href="index.jsp"><<-Back</a>
        </div>
        <div id='page-footer'>
            Stock Exchange  &copy; 2015
        </div>
    </body>
</html>
