import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "CalendarViewModel"

class CalendarViewModel : ViewModel() {
    init {
        Log.d(TAG, "CalendarViewModel instance created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "CalendarViewModel instance about to be destroyed")
    }
}