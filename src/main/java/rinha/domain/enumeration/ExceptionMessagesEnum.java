package rinha.domain.enumeration;

import org.springframework.http.HttpStatus;

public enum ExceptionMessagesEnum {

	NOT_MODIFIED_DEFAULT(304001, "The modifier is the same of object", HttpStatus.NOT_MODIFIED),
	EXAMPLE_NOT_FOUND(404001, "Example not found for Id informed", HttpStatus.NOT_FOUND),
	PRECONDITION_FAILED_DEFAULT(412001, "Precondition failed, the object already modified", HttpStatus.PRECONDITION_FAILED);

    private final Integer code;
    private final String message;
    private final HttpStatus httpStatus;

    ExceptionMessagesEnum(Integer code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}