package com.cc.behaviordetectionbackend;

import com.cc.behaviordetectionbackend.dto.conditionDTO.UserConditionDTO;
import com.cc.behaviordetectionbackend.service.UserService;
import com.cc.behaviordetectionbackend.utils.AesUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BehaviorDetectionBackendApplicationTests {

	@Resource
	private UserService userService;

	@Test
	void contextLoads() throws Exception {
		System.out.println(AesUtil.Encrypt("123456")); //yXVUkR45PFz0UfpbDB8/ew==
		UserConditionDTO userConditionDTO = new UserConditionDTO();
		userConditionDTO.setPassword("123456");
		userConditionDTO.setUsername("cc");
		userConditionDTO.setAccount("admin");
		userConditionDTO.setRole(0);
		userService.editUser(userConditionDTO);
	}

}
