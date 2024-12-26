package com.hnptech.stocknewscuckoo.token.service;

import com.hnptech.stocknewscuckoo.token.dto.DeviceTokenItem;
import com.hnptech.stocknewscuckoo.token.dto.DeviceTokenRequest;
import java.util.List;

public interface DeviceTokenService {

	void saveDeviceToken(DeviceTokenRequest request);

	List<DeviceTokenItem> getAllTokens();

}
