package br.com.caelum.twittelumappweb.webservices

import br.com.caelum.twittelumappweb.modelo.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

class UsuarioWebClient (retrofit: Retrofit){
    private val service: UsuarioService by lazy {
        retrofit.create(UsuarioService::class.java)
    }

    fun registra(usuario: Usuario,
                 sucesso: (Usuario) -> Unit,
                 erro: (Throwable) -> Unit){
        val chamadaPraCriar = service.cria(usuario)
        chamadaPraCriar.enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable){
                erro(t)
            }
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>){
                response.body()?.let(sucesso)
            }
        })

    }
    private interface  UsuarioService{
        @POST ("/usuario")
        fun cria(@Body usuario: Usuario): Call<Usuario>

    }

}