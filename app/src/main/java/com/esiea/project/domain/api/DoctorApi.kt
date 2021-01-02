package com.esiea.project.domain.api

import com.esiea.project.data.local.models.Doctor
import retrofit2.Call
import retrofit2.http.GET

interface DoctorApi {
    @get:GET("v1/doctors")
    val doctorResponse: Call<List<Doctor>>
}