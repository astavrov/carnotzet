package com.github.swissquote.carnotzet.core;

import java.util.Map;
import java.util.Set;

import org.jboss.shrinkwrap.resolver.api.maven.coordinate.MavenCoordinate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(toBuilder = true)
/**
 * Represents and application with it's configuration inside a Carnotzet environment
 */
public class CarnotzetModule {

	private final MavenCoordinate id;
	private final String name;
	private final String topLevelModuleName;
	private final Map<String, String> properties;
	private final String imageName;
	private final Set<String> dockerVolumes;
	private final String dockerEntrypoint;
	private final Set<String> dockerEnvFiles;

	public Boolean isDataModule() {
		return "true".equals(properties.get("data"));
	}

	public String getDataModuleType() {
		return properties.get("data.type");
	}

	public String getContainerName() {
		return topLevelModuleName + "_" + name;
	}

	public String getShortImageName() {
		String withoutHost = imageName.replaceFirst(".*/", "");
		return withoutHost.replaceFirst(":.*", "");
	}
}