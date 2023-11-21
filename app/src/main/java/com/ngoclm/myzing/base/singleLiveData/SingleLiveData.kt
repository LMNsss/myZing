package com.ngoclm.myzing.base.singleLiveData

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class SingleLiveData<T> : MutableLiveData<T>() {
    private var isPending = false

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, Observer<T> { t ->
            if (isPending) {
                isPending = false
                observer.onChanged(t)
            }
        })
    }
    @MainThread
    override fun setValue(value: T) {
        isPending = true
        super.setValue(value)
    }

}