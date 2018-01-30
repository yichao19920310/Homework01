<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
			<ul class="product2">
				<c:if test="${requestScope.pageModel.totalPage==0}">
					<h4>找不到您搜索的商品！</h4>
				</c:if>
				<c:set value="${requestScope.pList}" var="products"></c:set>
				<c:forEach items="${products}" var="p">
					<li>
						<dl>
							<dt>
								<a href="doAction?action=productView&pId=${p.hp_id }" target="_self"><img
									src="${p.hp_file_name }" /></a>
							</dt>
							<dd class="title">
								<a href="doAction?action=productView&pId=${p.hp_id}" target="_self">${p.hp_name }</a>
							</dd>
							<dd class="price">￥${p.hp_price}</dd>
						</dl>
					</li>

				</c:forEach>


			</ul>

			<!--分页-->
			<div class="pager">
				
					<ul>
						<li><a href="doAction?action=getPlist&page=${pager.prevPage }&cate=${param.cate}&hpcId=${param.hpc_id}&qname=${param.qname}" id="lastPage">上一页</a></li>
						<c:forEach items="${requestScope.pager.groupList}" var="pl">
							<!-- 当前页不需要超链接，其他需要做成超链接 -->
							<c:if test="${pl==pager.currentPage }">
								<li>${pl}</li>
							</c:if>
							<c:if test="${pl!=pager.currentPage }">
								<li><a href="doAction?action=getPlist&page=${pl}&cate=${param.cate}&hpcId=${param.hpcId}">${pl}</a></li> 
							</c:if>
						</c:forEach>
						<li><a href="doAction?action=getPlist&page=${pager.nextPage}&cate=${param.cate}&hpcId=${param.hpcId}&qname=${param.qname}" id="nextPage">下一页</a></li>
					</ul>
		
				<input type="hidden" id="source"
					value="${requestScope.pageModel.source}" /><input type="hidden"
					id="hpcId" value="${requestScope.pageModel.hpcId}" /> <input
					type="hidden" id="totalPage"
					value="${requestScope.pageModel.totalPage}" /> <input
					type="hidden" id="currentPage"
					value="${requestScope.pageModel.currentPage}" /> <input
					type="hidden" id="queryName"
					value="${requestScope.pageModel.qname}" />
			</div>