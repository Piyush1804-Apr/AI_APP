package com.practice.o_ai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.practice.o_ai.adapter.MessageAdapter
import com.practice.o_ai.api.ApiUtilities
import com.practice.o_ai.databinding.ActivityChatBinding
import com.practice.o_ai.databinding.ActivityMainBinding
import com.practice.o_ai.models.MessageModel
import com.practice.o_ai.models.request.ChatRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.RequestBody

class ChatActivity : AppCompatActivity() {
    private lateinit var binding :ActivityChatBinding
    private lateinit var adapter: MessageAdapter


    var list = ArrayList<MessageModel>()
    private lateinit var mLayoutManager : LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.stackFromEnd = true
        adapter = MessageAdapter(list)
        binding.recyclerView.adapter = adapter

        binding.sendbtn.setOnClickListener {
            if(binding.userMsg.text!!.isEmpty()){
                Toast.makeText(this, "Please ask your question?", Toast.LENGTH_SHORT).show()
            }
            else{
              callApi()
            }
        }

    }
    private fun callApi(){
        list.add(MessageModel(true,false,binding.userMsg.text.toString()))

        adapter.notifyItemInserted(list.size-1)
        binding.recyclerView.recycledViewPool.clear()
        binding.recyclerView.smoothScrollToPosition(list.size-1)

        val apiInterface = ApiUtilities.getApiInterface()
        val requestBody = RequestBody.create(MediaType.parse("application/json"),
            Gson().toJson(
                ChatRequest(
                      250,
                      "text-davinci-003",
                      "binding.userMsg.text.toString()",
                     0.7
                )
            )


            )

        val contentType = "application/json"
        val authorization = "Bearer ${Utils.API_KEY}"
        lifecycleScope.launch(Dispatchers.IO){


            try {


                val response = apiInterface.getChat(
                    contentType, authorization, requestBody
                )
                val textResponse = response.choices.first().text
                list.add(MessageModel(false, false, textResponse))

                adapter.notifyItemInserted(list.size - 1)
                binding.recyclerView.recycledViewPool.clear()
                binding.recyclerView.smoothScrollToPosition(list.size - 1)
            }
            catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@ChatActivity, e.message, Toast.LENGTH_SHORT).show()


                }

            }




        }


    }
}