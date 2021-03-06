/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package th.co.cenos.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import th.co.cenos.model.User;
import th.co.cenos.model.Warehouse;

/**
 * @function myStock
 * @package th.co.cenos.aop
 * @classname AuthenticationInterceptor
 * @author Pasuwat Wang (CENS ONLINE SERVICES)
 * @created Nov 16, 2016 9:41:01 AM
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(AuthenticationInterceptor.class);

	// before the actual handler will be executed
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		logger.info(request.getRequestURI());

		User loginData = WebSession.getLoginUser(request);
		if (loginData == null) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return false;
		}
		
		Warehouse warehouse = WebSession.getDefaultWarehouse(request);
		/* 1. Home & Another Warehouse Null
		 *    
		 * 2. default POST Warehouse NULL
		 * 
		 * */
		
		if(warehouse == null &!(request.getContextPath()+"/default").equals(request.getRequestURI()))
		{
			response.sendRedirect(request.getContextPath()+"/default");
			return false;
		}
		
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		response.setDateHeader("Expires", 0); // Proxies.
		
		return true;
	}
}
