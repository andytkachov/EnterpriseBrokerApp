
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="broker.informatorInterfaceRemote"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    informatorInterfaceRemote inf;
    inf = (informatorInterfaceRemote)(new javax.naming.InitialContext()).lookup("broker.informatorInterfaceRemote");
    Object sales = inf.getSalesListDateID(null,null);
    pageContext.setAttribute("sales", sales);
%>  

<!-- jsp:useBean id="sales" class="broker.informator" scope="session" / -->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='styles/stl.css' rel='stylesheet' type='text/css'/>
        <title>Stock Exchange</title>
    </head>
   <body>
        <div id='page-head'>
            
        <h1>Stock Exchange</h1>
        
        </div>  


        
        <div id='page-content'>
        <table id="myTable">
            <tr><th>Stock</th><th>Amount</th><th>Price</th><th>Broker</th><th></th></tr>
            
            <c:forEach var="sl" items="${sales}">
                    <tr>
                        <td><c:out value="${sl.stock.code} ${sl.stock.name}" /></td>
                        <td><c:out value="${sl.amount}" /></td>
                        <td><c:out value="${sl.price}" /></td> 
                        <td><c:out value="${sl.broker.lastname}" /></td> 
                        <td>
                            <form method='get' action="more.jsp">
                                <input type="hidden" name="hmore" value="${sl.stock.id}"/>
                                <input type="submit" name="more" value="more... " />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id='page-footer'>
            Stock Exchange  &copy; 2015
        </div>
    </body>
</html>
