package com.samsam.travel.travelcommerce;

import com.samsam.travel.travelcommerce.utils.Common;
import com.samsam.travel.travelcommerce.utils.ReturnMessage;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class TravelUtilsTests {

	@Resource
	private Common common;

	@Test
	@DisplayName("uuid 생성 테스트")
	void commonTest() {
		System.out.println("-----확인-----");
		System.out.println(common.getTargetUuid("ticket"));
	}

	@Test
	@DisplayName("ReturnMessage 테스트")
	void returnMessageTest() {
		List<Map<String, String>> items = new ArrayList<>();
		Map<String, String> item  = new HashMap<>();
		item.put("name", "colton");
		item.put("age", "27");
		items.add(item);

		item.put("name", "gil-dong");
		item.put("age", "127");
		items.add(item);

		ReturnMessage returnMessage = new ReturnMessage("성공하셨습니다");
		System.out.println("--------------------확인--------------------");
		System.out.println(returnMessage.getMessage());
		System.out.println(returnMessage.getResult());
		System.out.println(returnMessage.getValue());

		System.out.println("--------------------확인--------------------");
		returnMessage = new ReturnMessage(items);
		System.out.println(returnMessage.getMessage());
		System.out.println(returnMessage.getResult());
		System.out.println(returnMessage.getValue());
	}

}
