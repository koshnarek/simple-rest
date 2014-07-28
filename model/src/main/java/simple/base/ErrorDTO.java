package simple.base;

import org.apache.commons.lang3.exception.ExceptionUtils;

import simple.exceptions.AbstractException;
import simple.exceptions.InternalException;
import simple.shared.LogHolder;

public class ErrorDTO extends AbstractDTO<Throwable> {

	private String code;

	private String message;

	private String stacktrace;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(Throwable t) {
		if (LogHolder.getLogger().isDebugEnabled()) {
			this.stacktrace = ExceptionUtils.getStackTrace(t);
		}
	}

	public static ErrorDTO fromException(AbstractException e) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(e.getCode());
		errorDTO.setMessage(e.getMessage());
		errorDTO.setStacktrace(e);
		return errorDTO;
	}

	public static ErrorDTO fromException(java.lang.Error e) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(InternalException.CODE);
		errorDTO.setMessage(e.getMessage());
		errorDTO.setStacktrace(e);
		return errorDTO;
	}

	public static ErrorDTO fromException(RuntimeException e) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setCode(InternalException.CODE);
		errorDTO.setMessage(e.getMessage() != null ? e.getMessage() : e.getClass().toString());
		errorDTO.setStacktrace(e);
		LogHolder.getLogger().error(e.getMessage(), e);
		return errorDTO;
	}

}
