package th.co.mfec.car.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException {

	public BusinessException(String code, String message) {
		super(HttpStatus.OK, code, message);
	}

}
