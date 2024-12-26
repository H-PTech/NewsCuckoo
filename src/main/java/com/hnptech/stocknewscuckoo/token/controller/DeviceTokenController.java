package com.hnptech.stocknewscuckoo.token.controller;

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


	//TODO : response 그리고 저장
	@PostMapping
	public void saveToken(DeviceTokenRequest request) {
		deviceTokenService.saveDeviceToken(request);
	}

}
