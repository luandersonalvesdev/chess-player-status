package br.com.buscadorchesscom.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.buscadorchesscom.data.repository.ChessPlayerProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val chessPlayerProfileRepository = ChessPlayerProfileRepository()

    private var _playerAvatar = MutableLiveData("")
    val playerAvatar: LiveData<String>
        get() = _playerAvatar

    private var _playerName = MutableLiveData("")
    val playerName: LiveData<String>
        get() = _playerName

    private var _playerJoined = MutableLiveData<Long>(0)
    val playerJoined: LiveData<Long>
        get() = _playerJoined

    private var _playerStatus = MutableLiveData("")
    val playerStatus: LiveData<String>
        get() = _playerStatus

    private var _playerTitle = MutableLiveData("")
    val playerTitle: LiveData<String>
        get() = _playerTitle

    private var _playerUsername = MutableLiveData("")
    val playerUsername: LiveData<String>
        get() = _playerUsername

    private var _playerFollowers = MutableLiveData(0)
    val playerFollowers: LiveData<Int>
        get() = _playerFollowers

    private var _playerLastOnline = MutableLiveData<Long>(0)
    val playerLastOnline: LiveData<Long>
        get() = _playerLastOnline

    fun getPlayerProfile(username: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val playerProfile = chessPlayerProfileRepository.getPlayerProfile(username)
            if (playerProfile != null) {
                _playerAvatar.postValue(playerProfile.avatar)
                _playerName.postValue(playerProfile.name)
                _playerJoined.postValue(playerProfile.joined)
                _playerStatus.postValue(playerProfile.status)
                _playerTitle.postValue(playerProfile.title)
                _playerUsername.postValue(playerProfile.username)
                _playerFollowers.postValue(playerProfile.followers)
                _playerLastOnline.postValue(playerProfile.lastOnline)
            }
        }
    }
}