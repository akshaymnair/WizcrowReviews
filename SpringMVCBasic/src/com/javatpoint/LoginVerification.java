package com.javatpoint;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;

public class LoginVerification implements Filter {

	String cookieName = "userId";
	String cookieValue = null;
	
	private ArrayList<String> urlList;

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {


		System.out.println("Executing Filter");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		
		HttpSession session = request.getSession(true);
		
		String serverName = request.getServerName();
		int port = request.getServerPort();
		System.out.println(serverName+"::"+port);

		String url = request.getRequestURI();
		System.out.println(url);
		boolean allowedRequest = false;

		if (urlList.contains(url)) {
			allowedRequest = true;
		}

		else if (!allowedRequest) {
			boolean redirect = true;
			 Cookie[] cookies = request.getCookies();
			    if (cookies != null) {
			    	System.out.println("Cookie Found");
			        for (Cookie cookie : cookies) {
			            
						if (cookieName.equals(cookie.getName())) {
							System.out.println("cookie name==========="+cookieName);
			                cookieValue = cookie.getValue();
			                try {
								cookieValue = URLDecoder.decode(cookieValue, "UTF-8");
								System.out.println("cookie value========"+cookieValue);
								if(!StringUtils.isEmpty(cookieValue)){
									redirect = false;
								}
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			                catch (Exception e) {
			        			// TODO Auto-generated catch block
			        			e.printStackTrace();
			        		}
			                break;
			            }
			        }
			        if(redirect) {
			        	response.sendRedirect("http://"
								+ serverName+":"+String.valueOf(port)
								+ "/SpringMVCBasic/login.html");
					
					
						return;
			        }
			    }
		}
		
		chain.doFilter(req, res);
			    
}


	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String urls = config.getInitParameter("avoid-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());

		}
	}
	}