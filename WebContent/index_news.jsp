<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.news-list ul li {
	
}
</style>
<div class="newsList">
	<h2>新闻动态</h2>
	<ul>
		<c:forEach items="${requestScope.news }" var="n" end="10">
			<li><a href="doAction?action=readNews&nid=${n.hn_id }">${n.hn_title }&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;</a></li>
		</c:forEach>
	</ul>
</div>
