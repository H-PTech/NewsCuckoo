package com.hnptech.stocknewscuckoo.common.result;

import com.hnptech.stocknewscuckoo.common.response.ErrorResponse;

/**
 * 작업 결과를 안전하게 처리하기 위한 래퍼 클래스
 * 성공, 실패 , 알수 없음 세가지 상태 처리
 *
 * @param <T>
 **/
public class ApiResult<T> {

	private final ResultType resultType;
	private final T data;
	private final ErrorResponse errorResponse;

	/**
	 * 성공 케이스 생성자
	 * @param data
	 */
	private ApiResult(T data) {
		this.resultType = ResultType.SUCCESS;
		this.data = data;
		this.errorResponse = null;
	}

	/**
	 * 실패 케이스 생성자
	 * @param resultType    실패의 종류 (FAILURE 또는 UNKNOWN)
	 * @param errorResponse 에러 정보
	 */
	private ApiResult(ResultType resultType, ErrorResponse errorResponse) {
		this.resultType = resultType;
		this.data = null;
		this.errorResponse = errorResponse;
	}

	public static <T> ApiResult<T> success(T data) {
		return new ApiResult<>(data);
	}

	public static <T> ApiResult<T> failure(ErrorResponse error) {
		return new ApiResult<>(ResultType.FAILURE, error);
	}

	public static <T> ApiResult<T> unknown(ErrorResponse error) {
		return new ApiResult<>(ResultType.UNKNOWN, error);
	}


	public enum ResultType{
		SUCCESS,FAILURE,UNKNOWN
	}

}
