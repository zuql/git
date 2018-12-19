package com.zuql.jt.cart.service;

import java.util.Date;
import java.util.List;

import com.zuql.jt.cart.mapper.CartMapper;
import com.zuql.jt.manage.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartByUserId(Long userId) {
		
		Cart cart = new Cart();
		cart.setUserId(userId);
		return cartMapper.select(cart);
	}

	
	//能否使用通用Mapper实现???  不能 因为没有主键
	@Override
	public void updateNum(Cart cart) {
		
		cart.setUpdated(new Date());
		cartMapper.updateNum(cart);
	}


	@Override
	public void deleteCart(Cart cart) {
		
		//删除购物车数据  userId和itemId
		cartMapper.delete(cart);
		
	}

	/**
	 * 业务思路:
	 * 	通过itemId和userId唯一确定一条购物信息.
	 *  所以数据库中不允许出现重复数据
	 *  1.先根据itemId和userId查询数据库.
	 *  2.如果数据为null  则直接入库保存.
	 *  3.如果数据不为null 表示用户已经新增过该商品信息,则
	 *  	将商品数量更新即可
	 */
	@Override
	public void saveCart(Cart cart) {
		
		Cart cartDB = cartMapper.findCartByUI(cart);
		
		if(cartDB == null){
			cart.setCreated(new Date());
			cart.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.updateByPrimaryKeySelective(cartDB);
		}
	}
	
	
	
	
	
	
}
