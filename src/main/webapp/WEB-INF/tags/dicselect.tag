<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://www.dls.com/tags/dic" %>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="key" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String tagId = id;
    String tagName = name;

    if (id == null){
		tagId = tagName;
    }

    request.setAttribute("tagId", tagId);
    request.setAttribute("tagName", tagName);
%>

<select class="form-control" id="${tagId}" name="${name}">
	<c:if test="${value < 0}">
		<option selected="selected" value="-1">请选择</option>
	</c:if>
	<c:forEach items="${dict:list(key)}" var="dict" varStatus="status">
		<c:choose>
		 	<c:when test="${status.first eq true}">
				<c:choose>
			       	<c:when test="${dict.itemKey eq value}">
						<option selected="selected" value="${dict.itemKey}" >${dict.itemValue}</option>
			       	</c:when>
			       	<c:otherwise>
			       		<option value="${dict.itemKey}">${dict.itemValue}</option>
			       	</c:otherwise>
	       		</c:choose>
			</c:when>
	       	<c:otherwise>
		       	<c:choose>
			       	<c:when test="${dict.itemKey eq value}">
						<option selected="selected" value="${dict.itemKey}" >${dict.itemValue}</option>
			       	</c:when>
			       	<c:otherwise>
			       		<option value="${dict.itemKey}" >${dict.itemValue}</option>
			       	</c:otherwise>
	       		</c:choose>
	       	</c:otherwise>
		</c:choose>
	</c:forEach>
</select>