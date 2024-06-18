package androidx.lifecycle

import androidx.annotation.MainThread
import com.example.myselfcustom.arch.livedatabus.EventCenterCore

internal class ExternalNewLiveData<T>(val key: String) : MutableLiveData<T>() {

    var mObservers = mutableMapOf<Observer<in T>, ExternalObserverWrapper<T>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val exist = mObservers.getOrPut(observer) {
            LifecycleExternalObserver(observer, this, owner).apply {
                mObservers[observer] = this
                owner.lifecycle.addObserver(this)
            }
        }
        super.observe(owner, exist)
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        val exist = mObservers.getOrPut(observer) {
            AlwaysExternalObserver(observer, this).apply {
                mObservers[observer] = this
            }
        }
        super.observeForever(exist)
    }

    @MainThread
    fun observeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
    }

    @MainThread
    fun observeStickyForever(observer: Observer<in T>) {
        super.observeForever(observer)
    }

    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        mObservers.remove(observer)?.let {
            super.removeObserver(it)
        }
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        mObservers.values.forEach {
            if (it.isAttachedTo(owner)) {
                super.removeObserver(it)
            }
        }
        super.removeObservers(owner)
    }

    override fun onInactive() {
        super.onInactive()
        if (!hasObservers()) {
            EventCenterCore.liveDatas.remove(key)
        }
    }


}

internal open class ExternalObserverWrapper<T>(
    val observer: Observer<in T>,
    val liveData: ExternalNewLiveData<T>
) : Observer<T> {
    private var mLastVersion = liveData.version

    override fun onChanged(value: T) {
        if (mLastVersion >= liveData.version) {
            return
        }
        mLastVersion = liveData.version
        observer.onChanged(value)
    }

    open fun isAttachedTo(owner: LifecycleOwner): Boolean = false

}

internal class AlwaysExternalObserver<T>(
    observer: Observer<in T>,
    liveData: ExternalNewLiveData<T>
) : ExternalObserverWrapper<T>(observer, liveData)

internal class LifecycleExternalObserver<T>(
    observer: Observer<in T>,
    liveData: ExternalNewLiveData<T>,
    val owner: LifecycleOwner
) : ExternalObserverWrapper<T>(observer, liveData), LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        liveData.mObservers.remove(observer)
        owner.lifecycle.removeObserver(this)
    }

    override fun isAttachedTo(owner: LifecycleOwner): Boolean = owner == this.owner

}



