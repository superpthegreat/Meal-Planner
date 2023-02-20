import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "MainViewModel"
class MainViewModel : ViewModel() {
    var currentIndex = 0
    init {
        Log.d(TAG, "MainViewModel instance created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "MainViewModel instance about to be destroyed")
    }
}