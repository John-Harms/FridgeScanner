package com.johnharms.fridgescanner.presentation;

import java.lang.System;

/**
 * @author Ahmed Guedmioui
 */
@dagger.hilt.android.lifecycle.HiltViewModel
@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\n\u00a8\u0006\u0010"}, d2 = {"Lcom/johnharms/fridgescanner/presentation/CameraViewModel;", "Landroidx/lifecycle/ViewModel;", "cameraRepository", "Lcom/johnharms/fridgescanner/repository/CameraRepository;", "(Lcom/johnharms/fridgescanner/repository/CameraRepository;)V", "_isRecording", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "isRecording", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "onRecordVideo", "", "controller", "Landroidx/camera/view/LifecycleCameraController;", "onTakePhoto", "app_debug"})
public final class CameraViewModel extends androidx.lifecycle.ViewModel {
    private final com.johnharms.fridgescanner.repository.CameraRepository cameraRepository = null;
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isRecording = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRecording = null;
    
    @javax.inject.Inject
    public CameraViewModel(@org.jetbrains.annotations.NotNull
    com.johnharms.fridgescanner.repository.CameraRepository cameraRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isRecording() {
        return null;
    }
    
    public final void onTakePhoto(@org.jetbrains.annotations.NotNull
    androidx.camera.view.LifecycleCameraController controller) {
    }
    
    public final void onRecordVideo(@org.jetbrains.annotations.NotNull
    androidx.camera.view.LifecycleCameraController controller) {
    }
}