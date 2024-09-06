package com.ahmed_apps.john.aisetup;

import java.lang.System;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/ahmed_apps/john/aisetup/OpenAIApiService;", "", "postChatCompletion", "Lretrofit2/Call;", "Lokhttp3/ResponseBody;", "request", "Lcom/ahmed_apps/john/aisetup/OpenAIRequest;", "app_debug"})
public abstract interface OpenAIApiService {
    
    @org.jetbrains.annotations.NotNull
    @retrofit2.http.POST(value = "v1/chat/completions")
    @retrofit2.http.Headers(value = {"Content-Type: application/json"})
    public abstract retrofit2.Call<okhttp3.ResponseBody> postChatCompletion(@org.jetbrains.annotations.NotNull
    @retrofit2.http.Body
    com.ahmed_apps.john.aisetup.OpenAIRequest request);
}