
<%@ page import="org.eschoppe.Product" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'product.label', default: 'Product')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
    <div class="span14 offset1">
      <div id="list-product" class="content scaffold-list" role="main">
        <h2><g:message code="default.list.label" args="[entityName]" /></h2>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        <table>
          <thead>
            <tr>
            
              <g:sortableColumn property="byname" title="${message(code: 'product.byname.label', default: 'Byname')}" />
            
              <g:sortableColumn property="title" title="${message(code: 'product.title.label', default: 'Title')}" />
            
              <g:sortableColumn property="price" title="${message(code: 'product.price.label', default: 'Price')}" />
            
            </tr>
          </thead>
          <tbody>
          <g:each in="${productInstanceList}" status="i" var="productInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            
              <td><g:link action="show" id="${productInstance.id}">${fieldValue(bean: productInstance, field: "byname")}</g:link></td>
            
              <td>${fieldValue(bean: productInstance, field: "title")}</td>
            
              <td>${fieldValue(bean: productInstance, field: "price")}</td>
            
            </tr>
          </g:each>
          </tbody>
        </table>
        <div class="pagination">
          <g:paginate total="${productInstanceTotal}" />
        </div>
        <div class="add-entry">
          <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
        </div>
      </div>
		</div>
	</body>
</html>