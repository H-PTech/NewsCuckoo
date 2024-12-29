package com.hnptech.stocknewscuckoo.token.controller;

import com.hnptech.stocknewscuckoo.common.result.ApiResult;
import com.hnptech.stocknewscuckoo.token.dto.DeviceTokenRequest;
import com.hnptech.stocknewscuckoo.token.service.DeviceTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class DeviceTokenController {

	private final DeviceTokenService deviceTokenService;

	@PostMapping
	public ApiResult<Void> saveToken(DeviceTokenRequest request) {
		deviceTokenService.saveDeviceToken(request);
		return ApiResult.success(null);
	}

}
