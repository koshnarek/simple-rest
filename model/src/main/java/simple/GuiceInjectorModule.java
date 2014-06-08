package simple;

import com.google.code.guice.repository.configuration.ScanningJpaRepositoryModule;
import com.google.inject.AbstractModule;

public class GuiceInjectorModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new ScanningJpaRepositoryModule("simple", "simple.persistence-unit"));
	}
	
}
