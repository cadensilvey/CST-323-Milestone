package com.gcu.data;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gcu.model.OrderEntity;
import com.gcu.model.OrderModel;

@Primary
@Repository
public class OrdersDataService implements OrdersDataAccessInterface<OrderModel> {
	
	@Autowired
	private OrdersRepositoryInterface ordersRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	ModelMapper modelMapper = new ModelMapper();
	

	public OrdersDataService(DataSource dataSource) {
		super();
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	
	@Override
	public OrderModel getById(long id) {
		// TODO Auto-generated method stub
		OrderEntity entity = ordersRepository.findById(id).orElse(null);
		
		OrderModel model = modelMapper.map(entity, OrderModel.class);
		return model;
	}

	@Override
	public List<OrderModel> getOrders() {
		// TODO Auto-generated method stub
		Iterable<OrderEntity> entities = ordersRepository.findAll();
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		for(OrderEntity entity: entities) {
			orders.add(modelMapper.map(entity,  OrderModel.class));
		}
		
		return orders;
	}

	@Override
	public List<OrderModel> searchOrders(String searchTerm) {
		// TODO Auto-generated method stub
		Iterable<OrderEntity> entities = ordersRepository.findByProductNameContainingIgnoreCase(searchTerm);
		
		List<OrderModel> orders = new ArrayList<OrderModel>();
		for(OrderEntity entity: entities) {
			orders.add(modelMapper.map(entity,  OrderModel.class));
		}
		return orders;
	}

	@Override
	public long addOne(OrderModel newOrder) {
		// TODO Auto-generated method stub
		OrderEntity entity = modelMapper.map(newOrder, OrderEntity.class);
		entity.setId(null);
		OrderEntity result = ordersRepository.save(entity);
		if(result == null) {
			return  0;
		}
		else {
			return result.getId();
		}
	}

	@Override
	public boolean deleteOne(long id) {
		// TODO Auto-generated method stub
		ordersRepository.deleteById(id);
		return true;
	}

	@Override
	public OrderModel updateOne(long idToUpdate, OrderModel updateOrder) {
		// TODO Auto-generated method stub
		OrderEntity entity = modelMapper.map(updateOrder, OrderEntity.class);
		OrderEntity result = ordersRepository.save(entity);
		OrderModel order = modelMapper.map(result, OrderModel.class);
		return order;
	}

}
