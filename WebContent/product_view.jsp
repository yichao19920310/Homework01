<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>亚马逊 - 产品显示</title>
<link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/adv.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/scripts/jquery-2.1.0.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/scripts/adv.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/function.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/index.js"></script>
<script type="text/javascript" src="scripts/product_view.js"></script>


</head>
<body>
	<%@ include file="index_top.jsp"%>
	<div id="position" class="wrap">
		您现在的位置：<a href="doAction">亚马逊</a> &gt; 
		<%-- <a href="doAction?cate=parent&hpcId=${pro.hpc_id }">
		<c:if test="${pro.parent_category!=null}">
		${pro.parent_category.hpc_name }
		</c:if>
		</a> 
		&gt;
		 <a	href="doAction?cate=child&hpcId=${pro.hpc_child_id }">
		 <c:if test="${pro.child_category!=null}">
		 ${pro.child_category.hpc_name }
		 </c:if>
		 </a> --%>
	</div>
	<div id="main" class="wrap">
		<div class="lefter">
			<%@ include file="index_product_sort.jsp"%>
		</div>
		<div id="product" class="main">
			<c:set var="p" value="${requestScope.pro }"></c:set>
			<h1>商品名称:${p.hp_name }</h1>
			<div class="infos">
				<div class="thumb">
					<img style="width: 100px; height: 100px;" src="${p.hp_file_name }" />
				</div>
				<div class="buy">
					<p>
						商城价：<span class="price">￥${p.hp_price }</span>
					</p>
					<c:choose>
						<c:when test="${p.hp_stock>0 }">
							<p>
								库 存：<span id="stock">${p.hp_stock }</span>(有货)
							</p>
						</c:when>
						<c:otherwise>
							<p>库 存：无货
						</c:otherwise>
					</c:choose>
					<input type="button" id="minus" value=" - " width="3px"
						onclick="minus();"> <input type="text" id="count"
						name="count" onblur="checkStock()" value="1" maxlength="5"
						size="1" style="text-align: center; vertical-align: middle">
					<input type="button" id="add" value=" + " width="2px"
						onclick="add();">
					<c:choose>
						<c:when test="${sessionScope.user==null }">
							<div class="button">
								<input type="button" name="button" value="" onclick="remaind();"
									style="background: url(images/buyNow.png)" /> <input type="image"
									name="imageField" src="images/cartbutton.png" onclick="remaind()" />
							</div>

						</c:when>
						<c:otherwise>
							<div class="button">
								<input type="button" name="button" value=""
									onclick="goingToBuy(${p.hp_id });"
									style="background: url(images/buyNow.png)" />
								 <input type="image"
									name="imageField" src="images/cartbutton.png"
									onclick="addToCart(${p.hp_id });" />
							</div>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="clear"></div>
			</div>
			<div class="introduce">
				<h2>
					<strong>商品详情</strong>
				</h2>
				<div class="text">
					商品名字：${p.hp_name }<br /> 商品描述：${p.hp_description }<br />
					商品价格：￥${p.hp_price }<br /> 商品库存：${p.hp_stock }<br />
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div id="footer">Copyright &copy; 2016 上海海文 All Rights Reserved.
	</div>
</body>
</html>

