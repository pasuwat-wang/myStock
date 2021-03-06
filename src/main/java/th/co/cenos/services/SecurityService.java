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
package th.co.cenos.services;

import java.util.List;

import th.co.cenos.model.Locator;
import th.co.cenos.model.Menu;
import th.co.cenos.model.User;
import th.co.cenos.model.Warehouse;

/**
 * @function myStock
 * @package th.co.cenos.services
 * @classname AuthenticationService
 * @author Pasuwat Wang (CENS ONLINE SERVICES)
 * @created Nov 20, 2016 12:23:11 AM
 */
public interface SecurityService {
	
	public User authentication(int adClientId,String username , String password);
	
	public List<Warehouse> getUserWarehouse(User user);
	
	public List<String> getAuthorizeMenu(int roleId);
	
	public Warehouse getWarehouse(int mWarehouseId);
	
	public Locator getLocator(int mLocatorId);
	
	public List<Menu> getMenu(User user);
	

}
