package com.myplantdiary.blissbites.repository;

import com.myplantdiary.blissbites.dto.ShoppingCartItem;
import org.springframework.data.repository.CrudRepository;

public interface IShoppingCartDAO extends CrudRepository<ShoppingCartItem, Integer> {

}
