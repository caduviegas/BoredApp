package com.innaval.boredapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innaval.boredapp.data.repository.MainRepository
import com.innaval.boredapp.domain.Activity
import com.innaval.boredapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository):ViewModel() {

    private val _activityResult = MutableLiveData<Resource<Activity>>()
    val activityResult:LiveData<Resource<Activity>> = _activityResult

    fun getAnActivity(){
        viewModelScope.launch {
            _activityResult.value = Resource.Loading()
            _activityResult.value = mainRepository.getAnActivity()
        }
    }
}