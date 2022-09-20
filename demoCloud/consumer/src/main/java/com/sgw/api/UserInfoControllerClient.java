package com.sgw.api;

import com.sgw.common.configuration.FeignConfiguration;
import com.sgw.provider.api.UserInfoControllerAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider", path = "userInfo", configuration = FeignConfiguration.class)
public interface UserInfoControllerClient extends UserInfoControllerAPI {
}
