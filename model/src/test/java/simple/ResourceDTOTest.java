package simple;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import simple.base.ErrorDTO;
import simple.base.LinkDTO;
import simple.base.ResourceDTO;
import simple.shared.LogHolder;
import simple.users.UserDTO;
import simple.users.UserURI;

public class ResourceDTOTest {

	@Test
	public void shouldBuildResourceDTOFromDTO() {
		UserDTO userDTO = new UserDTO();

		ResourceDTO<UserDTO> resourceDTO = new ResourceDTO<UserDTO>(userDTO);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(userDTO));
		assertThat(resourceDTO.getItems(), is(nullValue()));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(nullValue()));
	}

	@Test
	public void shouldBuildResourceDTOFromErrorDTO() {
		ErrorDTO errorDTO = new ErrorDTO();

		ResourceDTO<ErrorDTO> resourceDTO = new ResourceDTO<ErrorDTO>(errorDTO);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(nullValue()));
		assertThat(resourceDTO.getError(), is(errorDTO));
		assertThat(resourceDTO.getLinks(), is(nullValue()));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOs() {
		Collection<UserDTO> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		ResourceDTO<Collection<UserDTO>> resourceDTO = new ResourceDTO<Collection<UserDTO>>(userDTOs);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(nullValue()));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOsWithPageableLinks() {
		String link = StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(1));
		LinkDTO previous = new LinkDTO("previous", String.format(LinkDTO.PAGEABLE_QUERY, UserURI.USERS, 1));
		LinkDTO next = new LinkDTO("next", String.format(LinkDTO.PAGEABLE_QUERY, UserURI.USERS, 3));
		Collection<UserDTO> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 1;

		ResourceDTO<Collection<UserDTO>> resourceDTO = new ResourceDTO<Collection<UserDTO>>(userDTOs, page);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(notNullValue()));
		assertThat(resourceDTO.getLinks(), containsInAnyOrder(next, previous));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOsWithPageableLinksWithoutPrevious() {
		String link = StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(1));
		LinkDTO previous = new LinkDTO("previous", String.format(LinkDTO.PAGEABLE_QUERY, UserURI.USERS, 0));
		LinkDTO next = new LinkDTO("next", String.format(LinkDTO.PAGEABLE_QUERY, UserURI.USERS, 2));
		Collection<UserDTO> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 0;

		ResourceDTO<Collection<UserDTO>> resourceDTO = new ResourceDTO<Collection<UserDTO>>(userDTOs, page);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(notNullValue()));
		assertThat(resourceDTO.getLinks(), hasItem(next));
		assertThat(resourceDTO.getLinks(), not(hasItem(previous)));
	}
}
