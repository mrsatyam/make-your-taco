package com.taco.properties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

/**
 * This is a Configuration property holder class. They’re beans that have their
 * properties injected from the Spring environment. They can be injected into
 * any other bean that needs those properties
 **/
@Component
@ConfigurationProperties(prefix = "taco.orders") // now you can set a property like taco.orders.pageSize = 20 in the
													// application.properties file or add Env variable:
													// $ export TACO_ORDERS_PAGESIZE=20 and that property will get set
													// here.
@Validated
@Data
public class OrderProps {
	@Min(value = 5, message = "must be between 5 and 25")
	@Max(value = 25, message = "must be between 5 and 25")
	private int pageSize = 20;
}
