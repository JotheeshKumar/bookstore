package com.joe.bookstore.commom;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.joe.bookstore.config.JwtInterceptor;
import com.joe.bookstore.dto.RequestMeta;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		SortHandlerMethodArgumentResolver sResolver = new SortHandlerMethodArgumentResolver();
		PageableHandlerMethodArgumentResolver pResolver = new PageableHandlerMethodArgumentResolver(sResolver);

		pResolver.setSizeParameterName("pgSize");
		pResolver.setPageParameterName("pgNum");
		sResolver.setSortParameter("orderBy");

		argumentResolvers.add(pResolver);
		// argumentResolvers.add(sResolver);
		super.addArgumentResolvers(argumentResolvers);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor);
	}

    @Bean
    @RequestScope
    RequestMeta getRequestMeta() {
        return new RequestMeta();

    }

	@Bean
	@RequestScope
	 JwtInterceptor getJwtInterceptor() {
		return new JwtInterceptor(getRequestMeta());

	}

}