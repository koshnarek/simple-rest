package simple;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class ApplicationConfig extends GuiceServletContextListener {

	public static Injector injector;

	@Override
	protected Injector getInjector() {
		injector = Guice.createInjector(new GuiceInjectorModule());
		return injector;
	}

	public static Injector getInjectorHolder() {
		return injector;
	}
}
