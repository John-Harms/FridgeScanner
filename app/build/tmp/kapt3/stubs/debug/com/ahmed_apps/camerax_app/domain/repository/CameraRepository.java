package com.ahmed_apps.camerax_app.domain.repository;

import java.lang.System;

/**
 * @author Ahmed Guedmioui
 */
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\b"}, d2 = {"Lcom/ahmed_apps/camerax_app/domain/repository/CameraRepository;", "", "recordVideo", "", "controller", "Landroidx/camera/view/LifecycleCameraController;", "(Landroidx/camera/view/LifecycleCameraController;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "takePhoto", "app_debug"})
public abstract interface CameraRepository {
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object takePhoto(@org.jetbrains.annotations.NotNull
    androidx.camera.view.LifecycleCameraController controller, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object recordVideo(@org.jetbrains.annotations.NotNull
    androidx.camera.view.LifecycleCameraController controller, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}