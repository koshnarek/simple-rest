package simple;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import simple.AbstractDTO;
import simple.ErrorDTO;
import simple.LinkDTO;
import simple.ResourceDTO;
import simple.shared.LogHolder;
import simple.users.UserDTO;
import simple.users.UserURI;

public class ResourceCreatorTest {

	@Test
	public void shouldBuildResourceDTOFromDTO() {
		UserDTO userDTO = new UserDTO();

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTO);

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

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(errorDTO);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(nullValue()));
		assertThat(resourceDTO.getError(), is(errorDTO));
		assertThat(resourceDTO.getLinks(), is(nullValue()));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOs() {
		Collection<AbstractDTO<?>> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTOs);

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
		LinkDTO previous = new LinkDTO("previous", String.format(LinkDTO.PAGEABLE_QUERY, link, 1, 3));
		LinkDTO next = new LinkDTO("next", String.format(LinkDTO.PAGEABLE_QUERY, link, 3, 3));
		Collection<AbstractDTO<?>> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 2;

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTOs, page, true, true);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(notNullValue()));
		assertThat(resourceDTO.getLinks(), contains(next, previous));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOsWithPageableLinksWithoutNext() {
		String link = StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(1));
		LinkDTO previous = new LinkDTO("previous", String.format(LinkDTO.PAGEABLE_QUERY, link, 1, 3));
		LinkDTO next = new LinkDTO("next", String.format(LinkDTO.PAGEABLE_QUERY, link, 3, 3));
		Collection<AbstractDTO<?>> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 2;

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTOs, page, false, true);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(notNullValue()));
		assertThat(resourceDTO.getLinks(), contains(previous));
		assertThat(resourceDTO.getLinks(), not(contains(next)));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOsWithPageableLinksWithoutPrevious() {
		String link = StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(1));
		LinkDTO previous = new LinkDTO("previous", String.format(LinkDTO.PAGEABLE_QUERY, link, 1, 3));
		LinkDTO next = new LinkDTO("next", String.format(LinkDTO.PAGEABLE_QUERY, link, 3, 3));
		Collection<AbstractDTO<?>> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 2;

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTOs, page, true, false);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(notNullValue()));
		assertThat(resourceDTO.getLinks(), contains(next));
		assertThat(resourceDTO.getLinks(), not(contains(previous)));
	}

	@Test
	public void shouldBuildResourceDTOFromCollectionOfDTOsWithPageableLinksWithoutLinks() {
		String link = StringUtils.replace(UserURI.USER, "{" + UserURI.USER_ID + "}", String.valueOf(1));
		Collection<AbstractDTO<?>> userDTOs = Arrays.asList(new UserDTO(), new UserDTO(), new UserDTO());
		userDTOs.stream().forEach(
				userDTO -> userDTO.setLink(link));
		Integer page = 2;

		ResourceDTO resourceDTO = ResourceDTO.getInstanceFrom(userDTOs, page, false, false);

		LogHolder.getLogger().info("{}\n\t{}", new Object() {
		}.getClass().getEnclosingMethod().getName(), resourceDTO.toString());

		assertThat(resourceDTO.getItem(), is(nullValue()));
		assertThat(resourceDTO.getItems(), is(userDTOs));
		assertThat(resourceDTO.getError(), is(nullValue()));
		assertThat(resourceDTO.getLinks(), is(nullValue()));
	}
}
