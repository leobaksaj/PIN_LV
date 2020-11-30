package com.example.lv1_baksaj;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseApiService {
    @GET("/v1/courses")
    Call<CourseResponse> getCourses();
}
