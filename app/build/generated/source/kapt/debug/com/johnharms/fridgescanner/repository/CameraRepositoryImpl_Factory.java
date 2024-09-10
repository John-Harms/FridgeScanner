package com.johnharms.fridgescanner.repository;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CameraRepositoryImpl_Factory implements Factory<CameraRepositoryImpl> {
  private final Provider<Application> applicationProvider;

  public CameraRepositoryImpl_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public CameraRepositoryImpl get() {
    return newInstance(applicationProvider.get());
  }

  public static CameraRepositoryImpl_Factory create(Provider<Application> applicationProvider) {
    return new CameraRepositoryImpl_Factory(applicationProvider);
  }

  public static CameraRepositoryImpl newInstance(Application application) {
    return new CameraRepositoryImpl(application);
  }
}
