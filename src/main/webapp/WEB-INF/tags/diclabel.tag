<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://www.dls.com/tags/dic" %>
<%@ attribute name="groupValue" type="java.lang.String" required="true"%>
<%@ attribute name="itemKey" type="java.lang.Integer" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

${dict:show(groupValue, itemKey)}