package com.hnptech.stocknewscuckoo.events.common;

import com.hnptech.stocknewscuckoo.events.common.Event;

public interface EventPublisher {

	void publish(Event event);
}
