package com.tykon.notification.entity.request;

public enum APIClientSource {

	android("android"), ios("ios"), web("web");

	private final String value;

	private APIClientSource(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	public static APIClientSource fromString(String source) {
		if (source == null || source.isEmpty()) {
			return null;
		}
		for (APIClientSource apiClientSource : values()) {
			if (source.equalsIgnoreCase(apiClientSource.toString())) {
				return apiClientSource;
			}
		}
		return null;
	}
}
