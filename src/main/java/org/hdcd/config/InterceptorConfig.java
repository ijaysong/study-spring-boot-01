package org.hdcd.config;

import org.hdcd.common.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
// 자바 설정 파일을 사용해 인터셉터를 설정한다
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		// 원하는 URL에 적절한 패턴을 적용하여 인터셉터를 지정한다
		registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/resources/**");
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	
}
