package br.com.caelum.twittelumappweb.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.caelum.twittelumappweb.modelo.Usuario
import br.com.caelum.twittelumappweb.webservices.UsuarioWebClient

class UsuarioRepository(private val webClient: UsuarioWebClient) {
    private val usuarioDaSessao = MutableLiveData<Usuario>()
    private val errorLiveData = MutableLiveData<Throwable>()

    fun getUsuario(): LiveData<Usuario> = usuarioDaSessao
    fun getErro(): LiveData<Throwable> = errorLiveData

    fun entra(usuario: Usuario) {
        Log.i("loginConta", "$usuario")
    }
    fun cadastra(usuario: Usuario) {
        webClient.registra(usuario, sucesso, erro)
    }
    private val sucesso=fun(usuario: Usuario) {
        usuarioDaSessao.postValue(usuario)
    }
    private val erro= fun(excecao: Throwable) {
        errorLiveData.postValue(excecao)
    }
}