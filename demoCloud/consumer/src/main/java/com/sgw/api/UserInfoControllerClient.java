package com.sgw.api;

import com.sgw.provider.api.UserInfoControllerAPI;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "provider", path = "userInfo")
public interface UserInfoControllerClient extends UserInfoControllerAPI {
}
