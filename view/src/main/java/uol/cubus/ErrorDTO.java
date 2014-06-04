package uol.cubus;

import org.apache.commons.lang3.exception.ExceptionUtils;

import uol.cubus.exceptions.AbstractException;
import uol.cubus.exceptions.InternalException;
import uol.cubus.shared.LogHolder;

public class ErrorDTO extends AbstractDTO<Throwable> {

	private Integer code;

	private String message;

	private String stacktrace;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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

}
