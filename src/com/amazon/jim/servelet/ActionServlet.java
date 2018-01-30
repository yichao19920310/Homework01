package com.amazon.jim.servelet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.jim.entity.Cart;
import com.amazon.jim.entity.Categories;
import com.amazon.jim.entity.News;
import com.amazon.jim.entity.OrderDetails;
import com.amazon.jim.entity.OrderSum;
import com.amazon.jim.entity.Pager;
import com.amazon.jim.entity.Products;
import com.amazon.jim.entity.User;
import com.amazon.jim.service.ICateService;
import com.amazon.jim.service.INewsService;
import com.amazon.jim.service.IProductService;
import com.amazon.jim.service.IShopService;
import com.amazon.jim.service.IUserService;
import com.amazon.jim.serviceImpl.CateServiceImpl;
import com.amazon.jim.serviceImpl.NewsServiceImpl;
import com.amazon.jim.serviceImpl.ProductServiceImpl;
import com.amazon.jim.serviceImpl.ShopServiceImpl;
import com.amazon.jim.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class Action
 */
@WebServlet("/doAction")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService ius = new UserServiceImpl();
	ICateService ics = new CateServiceImpl();
	IProductService ips = new ProductServiceImpl();
	INewsService ins = new NewsServiceImpl();
	IShopService iss = new ShopServiceImpl();
	boolean regRes = false;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private String feedback = "";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.获取对应的请求参数
		String method = request.getParameter("action");

		// 2.根据请求参数去调用对应的方法。

		if ("register".equals(method)) {
			register(request, response);
		} else if ("login".equals(method)) {
			login(request, response);
		} else if ("shoppingCart".equals(method)) {
			shoppingCart(request, response);
		} else if ("checkName".equals(method)) {
			checkName(request, response);
		} else if ("checkEmail".equals(method)) {
			checkEmail(request, response);
		} else if ("checkCode".equals(method)) {
			checkCode(request, response);
		} else if ("checkOut".equals(method)) {
			checkOut(request, response);
		}
		// showView
		else if ("cate".equals(method)) {
			category(request, response);
		} else if ("getPlist".equals(method)) {
			getPList(request, response);
		} else if ("news".equals(method)) {
			showNews(request, response);
		} else if ("hotProducts".equals(method)) {
			getHotProducts(request, response);
		} else if ("cateInfo".equals(method)) {
			getCateInfo(request, response);
		} else if ("historyMap".equals(method)) {
			getHistoryMap(request, response);
		} else if ("productView".equals(method)) {
			productView(request, response);
		}

		// shop
		else if ("showCart".equals(method)) {
			showCart(request, response);
		} else if ("shoppingCart".equals(method)) {
			shoppingCart(request, response);
		} else if ("doBuy".equals(method)) {
			doBuy(request, response);
		} else if ("deleteCart".equals(method)){
			deleteCart(request,response);
		}
		//
		else if ("readNews".equals(method)) {
			readNews(request, response);
		}
		response.getWriter().write(feedback);

	}

	private void deleteCart(HttpServletRequest request, HttpServletResponse response) {
		
				Object cart = request.getAttribute("cart");
				int id=((Cart)cart).getId();
				int result=iss.clearClistById(id);	
				// 3.根据结果进一步操作
				if (result == 1) {
					feedback = "1";
				} else if (result == -1) {
					feedback = "ERROR_FOR_NAME";
				} else {
					feedback = "ERROR";
				}
					
				
		}
	

	private void doBuy(HttpServletRequest request, HttpServletResponse response) {
		String[] costs = request.getParameterValues("sumPrice");
		String[] hod_quantities = request.getParameterValues("number");
		String[] pids = request.getParameterValues("pId");
		List<Cart> carts=(List<Cart>)(request.getAttribute("cart"));
		
		//初始化生成ho_id
		User user=(User)(request.getSession().getAttribute("user"));
		int ho_id= iss.genOrderId(user);
		
		int hp_Id=0;
		int hod_quantity=0;
		double cost=0;
		double totalCost=0;
		List<OrderDetails> orderDList= new ArrayList<OrderDetails>();
		//遍历获取orgerDetail放入list中
		for(int i=0;i<costs.length;i++){			
			hp_Id=Integer.parseInt(pids[i]);
			hod_quantity=Integer.parseInt(hod_quantities[i]);
			cost=Integer.parseInt(costs[i]);
			int hp_stock = ips.getProduct(hp_Id).getHp_stock();
			if(hp_stock>0&&hp_stock>=hod_quantity){
				OrderDetails od= iss.getOrderDetail(ho_id,hp_Id,hod_quantity,cost);
				orderDList.add(od);		
				//计算总价
				totalCost +=cost;
			}else if(hp_stock<hod_quantity){
				
				request.setAttribute("productFail", hp_Id);
			}else{
				System.out.println("as:143: ");
			}
		}
		for (OrderDetails orderDetails : orderDList) {
			System.out.println("as:149: oList= "+orderDetails);
		}
		//完成并提交订单
		int result = iss.finOrderbyId(ho_id,totalCost);

		if(result==1){
		OrderSum order=iss.getOrderbyId(ho_id);
		request.setAttribute("order", order);
		request.setAttribute("olist", orderDList);
		// 3.根据结果进一步操作
		if (result == 1) {
			feedback = "1";
		} else if (result == -1) {
			feedback = "ERROR_FOR_NAME";
		} else {
			feedback = "ERROR";
		}
		//跳转
//			request.getRequestDispatcher("/shopping-result.jsp").forward(request, response);
		System.out.println("as:167");
		
		}else{
			Object pId = request.getAttribute("productFail");
			try {
				System.out.println("as:176");
				response.getWriter().write("Id为"+pId+"的商品没货了");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	// --action=productView
	private void productView(HttpServletRequest request, HttpServletResponse response) {

		//
		String pId1 = request.getParameter("pId");
		int pId = Integer.parseInt(pId1 + "");
		Products pro = ips.getProduct(pId);
		request.setAttribute("pro", pro);
		System.out.println("us:189: " + pro.toString());
		// 更新HistoryMap key userId, value List<products>

		Object map = request.getAttribute("historyMap");
		// System.out.println("us:131 "+map!=null);
		if (map != null) {
			@SuppressWarnings("unchecked")
			Map<Integer, List<Products>> hMap = (Map<Integer, List<Products>>) map;
			Object user = request.getSession().getAttribute("user");
			Integer userId = 0;
			if (user != null && !"".equals(user)) {
				userId = (Integer) ((User) user).getHu_user_id();
				System.out.println("userId: " + userId);

			}
			//
			List<Products> historyPList = (List<Products>) hMap.get(userId);
			historyPList.add(pro);
			hMap.replace(userId, historyPList);
			// 存入request域中
			// 第三种：推荐，尤其是容量大时
			// System.out.println("通过Map.entrySet遍历key和value");

			request.getSession().setAttribute("historyMap", hMap);
		}

		// 跳转
		try {
			request.getRequestDispatcher("product_view.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:151 ");
			e.printStackTrace();
		}
	}

	//--购物车
	private void showCart(HttpServletRequest request, HttpServletResponse response) {
		int userId = 0;
		Object cUser = request.getSession().getAttribute("user");
		if (cUser != null && !"".equals(cUser)) {
			User user = (User) cUser;
			userId = user.getHu_user_id();
		}
		System.out.println("us:169: uid=" + userId);
		// 判断请求头是否带有参数pId和count
		String pid = request.getParameter("pId");
		String count = request.getParameter("count");
		System.out.println("us:238: pid=" + pid + ", count=" + count);
		// 如果有执行更新购物车
		if (pid != null && !"".equals(pid)) {
			int pId = Integer.parseInt(pid);
			int quantity = Integer.parseInt(count);
			// 更新
			int result = iss.updateCart(pId, quantity, userId);
			System.out.println("us: 245:" + result);
		}
		// 获取 购物车
		List<Cart> cartList = iss.showCart(userId);
		// 存入request域中
		request.setAttribute("cart", cartList);
		// 跳转
		try {
			System.out.println("us:253: " + cartList.toString());
			request.getRequestDispatcher("shopping.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:183 ");
			e.printStackTrace();
		}
	}

	//--购物车添加商品
	private void shoppingCart(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String pId1 = request.getParameter("pId");
		int pId = Integer.parseInt(pId1);
		String count = request.getParameter("count");
		int quantity = Integer.parseInt(count);
		Object user = request.getSession().getAttribute("user");
		int userId = ((User) user).getHu_user_id();
		//
		int result = iss.updateCart(pId, quantity, userId);
		System.out.println("us: 203:" + result);
		try {
			if (result == 1) {
				System.out.println("us:432: " + result);
				response.getWriter().write(result + "");
			} else {
				response.getWriter().write("ERROR");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readNews(HttpServletRequest request, HttpServletResponse response) {
		String nid = request.getParameter("nid");
		int nId=0;
		if(nid!=null&&!"".equals(nid)) {
			nId=Integer.parseInt(nid);
			
		}
		News newsInfo=ins.readNews(nId);
		//存入
		request.setAttribute("newsInfo", newsInfo);
		@SuppressWarnings("unchecked")
		List<News> nList =(List<News>)request.getAttribute("news");
		request.setAttribute("news", nList);
		// 跳转
		try {
			request.getRequestDispatcher("/news_view.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:231 ");
			e.printStackTrace();
		}
	}

	/*
	 * showView
	 */
	// --1--action=getPList
	private void getPList(HttpServletRequest request, HttpServletResponse response) {
		// 1.获取当前下标页
		String page = request.getParameter("page");
		Object cate1 = request.getParameter("cate");
		Object hpcId1 = request.getParameter("hpcId");
		Object hpName1 = request.getParameter("qname");
		// System.out.println("cate: "+cate1);
		// System.out.println("hpcId: "+hpcId1);
		// System.out.println("hpName: "+hpName1);

		int pager = 1;// 初始为第一页
		if (page != null && !"".equals(page)) {
			pager = Integer.parseInt(page);
			if (pager == 0) {
				pager = 1;
			}
		}
		Pager pa = new Pager(pager);
		List<Products> pList = null;
		if (cate1 != null && !"".equals(cate1) && hpcId1 != null && !"".equals(hpcId1)) {
			int hpcId = Integer.parseInt(hpcId1 + "");
			String cate = cate1.toString();
			// System.out.println("非空 ");
			pList = ips.getPList(cate, hpcId, pa);

		} else if (hpName1 != null && !"".equals(hpName1)) {
			String hpName = hpName1.toString();
			// System.out.println(hpName);
			pList = ips.getPList(hpName, pa);

		} else {
			pList = ips.getPList(pa);
		}

		// for (Products products : pList) {
		// System.out.println("result2: "+products);
		// }
		// 存入request域中
		request.setAttribute("pList", pList);
		request.setAttribute("pager", pa);
		// 转发
		try {
			request.getRequestDispatcher("/doAction?action=cate").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("us:275 ");
			e.printStackTrace();
		}
	}

	// --2--action=cate
	private void category(HttpServletRequest request, HttpServletResponse response) {
		// 获取分类cMap
		Map<Categories, List<Categories>> cMap = new LinkedHashMap<Categories, List<Categories>>();
		cMap = ics.getCategories();
		// 放入session域中
		request.getSession().setAttribute("cMap", cMap);
		// 跳转
		try {
			request.getRequestDispatcher("/doAction?action=historyMap").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:249 ");
			e.printStackTrace();
		}
	}

	// --3--action=historyMap
	@SuppressWarnings("unchecked")
	private void getHistoryMap(HttpServletRequest request, HttpServletResponse response) {
		// 获取HistoryMap key userId, value List<products>
		Object hMap = request.getSession().getAttribute("historyMap");
		// 初始化 historyMap
		if (hMap == null || "".equals(hMap)) {

			Map<Integer, List<Products>> historyMap = new LinkedHashMap<Integer, List<Products>>();
			// 初始化key
			Integer userId = 0;
			Object u = request.getSession().getAttribute("user");
			if (u != null && !"".equals(u)) {
				userId = (Integer) ((User) u).getHu_user_id();
			}
			// 初始化value
			List<Products> historyList = new ArrayList<Products>();// value
			//
			historyMap.put(userId, historyList);
			hMap = historyMap;
			for (Map.Entry<Integer, List<Products>> entry : ((Map<Integer, List<Products>>) hMap).entrySet()) {
				System.out.println("us: 285: key= " + entry.getKey() + " and value= " + entry.getValue());
			}
			// 存入Session域中
			request.getSession().setAttribute("historyMap", hMap);
		}
		// 转发
		try {
			request.getRequestDispatcher("/doAction?action=hotProducts").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:299 ");
			e.printStackTrace();
		}
	}
	
	// --4--action=hotProducts
	private void getHotProducts(HttpServletRequest request, HttpServletResponse response) {
		// 获取hotProducts
		List<Products> hotProduct = ips.getHotProduct();
		// 存入request域中
		request.setAttribute("hotProducts", hotProduct);
		// 转发至INDEX.jsp
		try {
			request.getRequestDispatcher("/doAction?action=news").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:314 ");
			e.printStackTrace();
		}
	}
	
	// --5--action=news
	private void showNews(HttpServletRequest request, HttpServletResponse response) {

		// 获取news
		List<News> nList = ins.showNews();
		// 存入request域中
		request.setAttribute("news", nList);
		// 转发
		try {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("uServlet:265 ");
			e.printStackTrace();
		}
	}


	// --action=cateInfo
	private void getCateInfo(HttpServletRequest request, HttpServletResponse response) {
		
		List<Categories> categoryInfo = new ArrayList<Categories>();

		// 转发至INDEX.jsp
	}

	/*
	 * user operation
	 */
	// 退出
	private void checkOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			System.out.println("重定向失败");
			e.printStackTrace();
		}

	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// 1.获取请求携带的数据
		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		// 将数据封装成对象
		User user = new User(username, password);
		// 2.业务处理
		IUserService ius = new UserServiceImpl();
		User result = ius.login(user);
		// 3.根据结果进一步操作
		if (result == null) {
			// 登录失败
			feedback = "0";
			/* response.sendRedirect(""); */
		} else {// 登录成功
				// 将用户对象存放在session域中
			System.out.println("result:" + result);
			request.getSession().setAttribute("user", result);
			// 响应
			feedback = "1";

		}

	}
	private void checkName(HttpServletRequest request, HttpServletResponse response) {
		// 1.获取请求数据
		String name = request.getParameter("userName");
		// 2.调用业务逻辑验证用户名是否存在
		User user = ius.checkName(name);
		// 3.根据结果进一步操作
		if (user == null) {
			if (name.length() < 3) {// 用户名不合规
				feedback = "3";
			} else {// 用户名不存在
				feedback = "2";
			}
		} else {// 用户名存在
			feedback = "1";
		}
	}

	private void checkEmail(HttpServletRequest request, HttpServletResponse response) {
		// 1.获取请求数据
		String email = request.getParameter("userEmail");
		// 2.调用业务逻辑验证email是否存在
		User user = ius.checEmail(email);
		// 3.根据结果进一步操作
		if (user == null) {// email不存在
			feedback = "";
		} else {// email存在
			feedback = "1";
		}
	}

	private void checkCode(HttpServletRequest request, HttpServletResponse response) {
		// 1.通过会话拿到后台验证码
		Object vc = request.getSession().getAttribute("validateCode");
		System.out.println("vc:" + vc);
		// 2.获取请求数据
		String inputVc = request.getParameter("veryCode");
		System.out.println("inputVc" + inputVc);
		if (inputVc.equals(vc.toString())) {
			feedback = "1";
		} else {
			feedback = "";
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		// 1.获取请求携带的数据
		String username = request.getParameter("userName");
		String password = request.getParameter("rePassWord");
		String sex = request.getParameter("sex");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strBirthday = request.getParameter("birthday");
		java.util.Date ubirthday = null;

		try {
			ubirthday = sdf.parse(strBirthday);
		} catch (ParseException e) {
			System.out.println("日期转换");
			e.printStackTrace();
		}
		java.sql.Date birthday = new java.sql.Date(ubirthday.getTime());

		String identity = request.getParameter("identity");
		String email = request.getParameter("email");
		String mobil = request.getParameter("mobile");
		String address = request.getParameter("address");
		// 2.将数据封装成对象
		User user = new User(username, password, sex, birthday, identity, email, mobil, address);

		int result = ius.register(user);
		// 3.根据结果进一步操作
		if (result == 1) {
			feedback = "1";
		} else if (result == -1) {
			feedback = "ERROR_FOR_NAME";
		} else {
			feedback = "ERROR";
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("uServlet:464: Post ");
		doGet(request, response);
	}

}
