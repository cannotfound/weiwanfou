<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false"%>
<!-- 
由于Jsp2.0向后兼容的特性， 当遇到使用Jsp 1.2（Servlet v2.3）的网站时会默认的禁用JSP2.0 EL，所以导致c:out不能正确输出。
（这里注意Jsp 1.2禁用JSP2.0的EL，而是去使用JSTL 1.0 taglib去解析EL。所以使用JSP 1.2+JSTL 1.0不会出问题，而使用了JSP 1.2+JSTL 1.1就会导致c:out不能正确输出）
page指令的isELIgnored屬性是控制EL的使用權的,它的兩個值,true和false 
如果 isELIgnored='true'表示禁用EL,isELIgnored='false' 表示可以使用EL， 默認是false
 -->
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    
    <div class="listTitle">
      <h1>Spittle Show</h1>
      <ul class="spittleList">

          <li id="spittle_<c:out value="${spittle.id}"/>">
            <div class="spittleMessage"><c:out value="${spittle.message}" /></div>
            <div>
              <span class="spittleTime"><c:out value="${spittle.time}" /></span>
              <span class="spittleLocation">(<c:out value="${spittle.latitude}" />, <c:out value="${spittle.longitude}" />)</span>
            </div>
          </li>
      </ul>
    </div>
  </body>
</html>