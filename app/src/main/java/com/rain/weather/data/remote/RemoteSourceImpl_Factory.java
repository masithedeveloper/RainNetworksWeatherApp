// Generated by Dagger (https://google.github.io/dagger).
package com.rain.weather.data.remote;

import dagger.internal.Factory;
import com.rain.weather.data.remote.api.ApiService;
import com.rain.weather.provider.LocationProvider;
import javax.inject.Provider;

public final class RemoteSourceImpl_Factory implements Factory<RemoteSourceImpl> {
  private final Provider<ApiService> serviceProvider;

  private final Provider<LocationProvider> locationProvider;

  public RemoteSourceImpl_Factory(
      Provider<ApiService> serviceProvider, Provider<LocationProvider> locationProvider) {
    this.serviceProvider = serviceProvider;
    this.locationProvider = locationProvider;
  }

  @Override
  public RemoteSourceImpl get() {
    return provideInstance(serviceProvider, locationProvider);
  }

  public static RemoteSourceImpl provideInstance(
      Provider<ApiService> serviceProvider, Provider<LocationProvider> locationProvider) {
    return new RemoteSourceImpl(serviceProvider.get(), locationProvider.get());
  }

  public static RemoteSourceImpl_Factory create(
      Provider<ApiService> serviceProvider, Provider<LocationProvider> locationProvider) {
    return new RemoteSourceImpl_Factory(serviceProvider, locationProvider);
  }

  public static RemoteSourceImpl newRemoteSourceImpl(
      ApiService service, LocationProvider locationProvider) {
    return new RemoteSourceImpl(service, locationProvider);
  }
}
