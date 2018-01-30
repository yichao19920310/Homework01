package com.amazon.jim.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Pager implements Serializable{
	
	public static final int PAGE_RECORD = 8;//每页展示的商品的数量*
	public static final int GROUP_RECORD = 5;//下边导航栏的数量*
	private int currentPage;	// 当前页码*
	private int pageCount;		// 总页数*
	private int recordCount;	// 总记录数*
	private int currentGroup;	// 当前组数*
	private List<Integer> groupList;//页面中需要的分组页索引
	// 构造方法
	public Pager(int page){
		currentPage = page;
	}
	
	//获取当前页页码
	public int getCurrentPage(){
		return currentPage;
	}
	
	// 获取下一页页码
	public int getNextPage(){
		if(currentPage < pageCount)
			return currentPage+1;
		else
			return pageCount;
	}
	// 获取上一页页码
	public int getPrevPage(){
		if(currentPage <= 1)
			return 1;
		else
			return currentPage - 1;
	}
	
	//  获取当前组数
	public int getCurrentGroup(){
		return currentGroup;
	}
	
	public List<Integer> getGroupList(){
		// 计算当前组数
		currentGroup = (int)Math.ceil((float)currentPage / GROUP_RECORD);
		groupList = new ArrayList<Integer>();//123 456 7
		int start = (currentGroup-1) * GROUP_RECORD;//1-1 *3=0
		int end = (start + GROUP_RECORD) < pageCount ? start + GROUP_RECORD : pageCount;
		//填充
		for (int i = start + 1; i <= end; i++) {
			groupList.add(i);
		}
		return groupList;
	}
/*//	select t.* from(select rownum as r, table_goods.* from table_goods)t where t.r > start and t.r <=end
	1
	2
	3
	4
	5
	
	上  1*5 2*5 3*5 4*5 下一页*/
	
	
	
	
	
	
	// 获取总页数
	public int getPageCount(){
		return pageCount;
	}
	
	// 设置总记录数
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
		pageCount = (int)Math.ceil(((float)recordCount / PAGE_RECORD));
	}
	
	// 获取总记录数
	public int getRecordCount() {
		return recordCount;
	}
}
