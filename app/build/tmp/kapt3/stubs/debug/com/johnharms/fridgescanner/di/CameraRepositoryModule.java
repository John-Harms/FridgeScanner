package com.johnharms.fridgescanner.di;

import java.lang.System;

/**
 * @author Ahmed Guedmioui
 */
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/johnharms/fridgescanner/di/CameraRepositoryModule;", "", "()V", "bindCameraRepository", "Lcom/johnharms/fridgescanner/repository/CameraRepository;", "cameraRepositoryImpl", "Lcom/johnharms/fridgescanner/repository/CameraRepositoryImpl;", "app_debug"})
@dagger.Module
public abstract class CameraRepositoryModule {
    
    public CameraRepositoryModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    @javax.inject.Singleton
    @dagger.Binds
    public abstract com.johnharms.fridgescanner.repository.CameraRepository bindCameraRepository(@org.jetbrains.annotations.NotNull
    com.johnharms.fridgescanner.repository.CameraRepositoryImpl cameraRepositoryImpl);
}