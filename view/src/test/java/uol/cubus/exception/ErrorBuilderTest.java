package uol.cubus.exception;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import uol.cubus.ErrorDTO;
import uol.cubus.exceptions.InternalException;
import uol.cubus.exceptions.NotFoundException;
import uol.cubus.shared.LogHolder;
import uol.cubus.users.UserError;

public class ErrorBuilderTest {

	@Test
	public void shouldBuildErrorDTOFromException() {
		Long id = 1L;
		ErrorDTO errorDTO = ErrorDTO.fromException(new NotFoundException(UserError.NOT_FOUND.withId(id)));

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), errorDTO.toString());

		assertThat(errorDTO.getCode(), is(UserError.NOT_FOUND.getCode()));
		assertThat(errorDTO.getMessage(), is(String.format(UserError.NOT_FOUND.getMessage(), id)));
	}

	@Test
	public void shouldBuildErrorDTOFromError() {
		String message = "unknow error";
		ErrorDTO errorDTO = ErrorDTO.fromException(new UnknownError(message));

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), errorDTO.toString());

		assertThat(errorDTO.getCode(), is(InternalException.CODE));
		assertThat(errorDTO.getMessage(), is(message));
	}
}
