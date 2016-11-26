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
package th.co.cenos.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import th.co.cenos.model.Locator;
import th.co.cenos.model.Stocktaking;
import th.co.cenos.model.StocktakingLine;
import th.co.cenos.model.Warehouse;
import th.co.cenos.services.ProductService;
import th.co.cenos.web.WebSession;

/**
 * @function myStock
 * @package th.co.cenos.controller
 * @classname LoginController
 * @author Pasuwat Wang (CENS ONLINE SERVICES)
 * @created Nov 16, 2016 10:42:48 AM
 */
@Controller
public class StocktakingController {
	
	private Logger logger = LoggerFactory.getLogger(StocktakingController.class);
	
	@RequestMapping(value = "/stocktaking", method = RequestMethod.GET)
	public ModelAndView showStocktakingPage(HttpServletRequest request) {
		Warehouse warehouse = WebSession.getDefaultWarehouse(request);
		List<Locator> locatorL = warehouse.getLocatorL();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("stocktaking");
		model.addObject("locatorL", locatorL);
		
		return model;
	}
	
	@RequestMapping(value = "/stocktaking/detail", method = RequestMethod.GET)
	public ModelAndView showStocktakingDetail(@RequestParam("locator") String locatorId,HttpServletRequest request) {
		ModelAndView model = null;
		Stocktaking stocktaking = WebSession.getOpenedStocktaking(request);
		int i_locator_id = 0;
		
		try{
			if(StringUtils.isEmpty(locatorId)){
				model = new ModelAndView("redirect:/stocktaking");
				model.addObject("error", "err.stocktaking.locatorId");
				return model;
			}
			
			i_locator_id = Integer.valueOf(locatorId);
		}catch(Exception ex){
			// Cannot Parse Locator
			model = new ModelAndView("redirect:/stocktaking");
			model.addObject("error", "err.stocktaking.parsing");
			return model;
		}
		
		Locator locator = getLocator(i_locator_id , WebSession.getDefaultWarehouse(request));
		if(locator == null){
			// Cannot Find Locator
			model = new ModelAndView("redirect:/stocktaking");
			model.addObject("error", "err.stocktaking.locator");
			return model;
		}
		
		model = new ModelAndView();
		model.setViewName("stocktaking-detail");
		
		List<StocktakingLine> detailL = new ArrayList<StocktakingLine>();
		for(StocktakingLine line : stocktaking.getLineL()){
			if(line.getLocator().getLocatorId() == locator.getLocatorId())
				detailL.add(line);
		}
		
		model.addObject("detailL", detailL);
		model.addObject("locator", locator);

		return model;
	}

	/**
	 * @param locatorId
	 * @param defaultWarehouse
	 * @return
	 */
	private Locator getLocator(int locatorId, Warehouse defaultWarehouse) {
		List<Locator> locatorL = defaultWarehouse.getLocatorL();
		Locator ret = null;
		for(Locator locator : locatorL){
			if(locator.getLocatorId() == locatorId){
				ret = locator;
				break;
			}
		}
		
		return ret;
	}
}