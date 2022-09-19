package com.sgw.api;

import com.sgw.common.feigninterceper.HeaderInterceptor;
import com.sgw.provider.api.UserInfoControllerAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider", path = "userInfo", configuration = HeaderInterceptor.class)
public interface UserInfoControllerClient extends UserInfoControllerAPI {
}
