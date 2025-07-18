/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.cloud.ai.application.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yuluo
 * @author <a href="mailto:yuluo08290126@gmail.com">yuluo</a>
 */

//@Configuration
//public class FaviconConfiguration implements WebMvcConfigurer {
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//
//		registry.addInterceptor(new HandlerInterceptor() {
//			@Override
//			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//				if (!"GET".equalsIgnoreCase(request.getMethod()) || !request.getRequestURI().toString()
//						.equals("/favicon.ico")) {
//					return true;
//				}
//				response.setStatus(HttpStatus.NO_CONTENT.value());
//				return false;
//			}
//
//		}).addPathPatterns("/**");
//	}
//
//}
