package com.hnptech.stocknewscuckoo.translation.service;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
    통합 테스트
    - OpenAiService와 관련된 실제 빈들이 정상적으로 동작하는지 검증
    - Spring Boot 애플리케이션 컨텍스트 로딩 여부 확인
    - 외부 OpenAI API 호출과 응답이 올바른지 테스트
 */
@SpringBootTest
@Slf4j
public class OpenAiServiceIntegrationTest {

	@Autowired
	private OpenAiService openAiService;

	@Test
	@DisplayName("번역 기능 테스트")
	void translate() {
		/*
            테스트 대상: OpenAiService.translate(String title)
            입력: "Test Title for translate"
            기대 동작:
            - 서비스 계층이 정상적으로 실행된다.
            - 결과 값이 null이 아니며 올바른 번역 결과를 반환한다.
         */

		//given
		String title = "Test Title for translate";

		//when
		String result = openAiService.translate(title);

		//then
		assertThat(result).isNotNull();
		log.info(result);
	}
}
