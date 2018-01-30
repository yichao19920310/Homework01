<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="p_category">
	<h2>商品分类</h2>

	<!-- Map<Category, List<Category>> -->
	<c:set var="map" value="${sessionScope.cMap }"></c:set>

	<c:forEach items="${map }" var="c">
		<dl>
			<dt>
				<a href="doAction?action=getPlist&page=1&cate=parent&hpcId=${c.key.hpc_id  }">${c.key.hpc_name}</a>
			</dt>
			<c:forEach items="${c.value}" var="category">
				<dd>
					<a href="doAction?action=getPlist&page=1&cate=child&hpcId=${category.hpc_id }">${category.hpc_name }</a>
				</dd>
			</c:forEach>
		</dl>
	</c:forEach>


</div>

