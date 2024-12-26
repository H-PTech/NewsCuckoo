package com.hnptech.stocknewscuckoo.token.service;

import com.hnptech.stocknewscuckoo.token.dto.DeviceTokenRequest;
import com.hnptech.stocknewscuckoo.token.model.DeviceToken;
import com.hnptech.stocknewscuckoo.token.repository.DeviceTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceTokenServiceImpl implements DeviceTokenService {

	private final DeviceTokenRepository repository;

	@Override
	public void saveDeviceToken(DeviceTokenRequest request) {
		repository.save(DeviceToken.builder().deviceToken(request.getDeviceToken()).
				build());
	}
}
